package com.karpen.servlet.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.karpen.servlet.controller.SkillController;
import com.karpen.servlet.model.Skill;
import com.karpen.servlet.util.HibernateProxyTypeAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import java.util.List;

@WebServlet("/skill")
public class SkillServlet extends HttpServlet {
    SkillController skillController = new SkillController();
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
        List<Skill> skills = null;
        try {
            skills = skillController.getAll();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = b.create();
        String json = gson.toJson(skills);
        writer.println(json);
        log(OPERATIONSUCCEFSSUL);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        PrintWriter writer = null;
        assert false;
        assert writer != null;
        writer = response.getWriter();
        try {
            skillController.create(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        writer.print(OPERATIONSUCCEFSSUL);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idSkillString = request.getParameter("id");
        Long id = Long.valueOf(idSkillString);
        List<Skill> skills = null;
        try {
            skills = skillController.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PrintWriter writer = null;
        writer = response.getWriter();
        assert false;
        assert writer != null;
        boolean checkId = skills.stream().anyMatch(p -> p.getId().equals(id));
        if (checkId) {
            try {
                skillController.deleteById(id);
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
        String idString = request.getParameter("id");
        Long id = Long.valueOf(idString);
        String name = request.getParameter("name");
        List<Skill> skills = null;
        try {
            skills = skillController.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PrintWriter writer = null;
        writer = response.getWriter();
        assert false;
        assert writer != null;
        boolean checkId = skills.stream().anyMatch(p -> p.getId().equals(id));
        if (checkId) {
            try {
                skillController.update(id, name);
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
