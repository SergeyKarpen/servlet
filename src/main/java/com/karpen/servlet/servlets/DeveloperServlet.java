package com.karpen.servlet.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.karpen.servlet.controller.DeveloperController;
import com.karpen.servlet.model.Developer;
import com.karpen.servlet.model.Skill;
import com.karpen.servlet.repository.HibernateDeveloperRepo;
import com.karpen.servlet.repository.impl.HibernateDeveloperRepoImpl;
import com.karpen.servlet.util.HibernateProxyTypeAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/developer")
public class DeveloperServlet extends HttpServlet {
    DeveloperController developerController = new DeveloperController();
    private final String OPERATIONSUCCEFSSUL = "The operation was successful!";
    private final String ERRORINPUT = "not correct input";

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        PrintWriter writer = null;
        try {
            writer = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Developer> developers = null;
        try {
            developers = developerController.getAll();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = b.create();
        String json = gson.toJson(developers);
        assert writer != null;
        writer.println(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        Long accountId = Long.valueOf(request.getParameter("accountId"));
        String [] idSkillInString = request.getParameterValues("idSkill");
        Set<Long> setIdSkill = Arrays.stream(request.getParameterValues("idSkill")).map(Long::valueOf).collect(Collectors.toSet());
        List<Developer> developers = null;
        PrintWriter writer = null;
        assert false;
        assert writer != null;
        writer = response.getWriter();
        try {
            developerController.create(firstName, lastName, accountId, setIdSkill);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        writer.print(OPERATIONSUCCEFSSUL);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idString = request.getParameter("id");
        Long id = Long.valueOf(idString);
        List<Developer> developers = null;
        try {
            developers = developerController.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PrintWriter writer = null;
        writer = response.getWriter();
        assert false;
        assert writer != null;
        boolean checkId = developers.stream().anyMatch(p -> p.getId().equals(id));
        if (checkId) {
            try {
                developerController.deleteById(id);
                writer.print(OPERATIONSUCCEFSSUL);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        } else {
            writer.print(ERRORINPUT);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        Long accountId = Long.valueOf(request.getParameter("accountId"));
        Set<Long> idSkill = Arrays.stream(request.getParameterValues("idSkill")).map(Long::valueOf).collect(Collectors.toSet());
        List<Developer> developers = null;
        PrintWriter writer = null;
        try {
            developers = developerController.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        writer = response.getWriter();
        assert false;
        assert writer != null;
        boolean checkId = developers.stream().anyMatch(p -> p.getId().equals(id));
        if (checkId) {
            try {
                developerController.update(firstName, lastName, id, accountId, idSkill);
                writer.print(OPERATIONSUCCEFSSUL);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        } else {
            writer.print(ERRORINPUT);
        }
    }

    @Override
    public void destroy() {
    }
}
