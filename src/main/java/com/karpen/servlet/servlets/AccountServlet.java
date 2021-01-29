package com.karpen.servlet.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.karpen.servlet.controller.AccountController;
import com.karpen.servlet.model.Account;
import com.karpen.servlet.model.AccountStatus;
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


@WebServlet(urlPatterns = "/account")
public class AccountServlet extends HttpServlet {
    AccountController accountController = new AccountController();
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
        List<Account> accounts = null;
        try {
            accounts = accountController.getAll();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = b.create();
        String json = gson.toJson(accounts);
        assert writer != null;
        writer.println(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String content = request.getParameter("content");
        String status = request.getParameter("status");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert false;
        assert writer != null;
        AccountStatus accountStatus = null;
        switch (status) {
            case ("active"):
                accountStatus = AccountStatus.ACTIVE;
                break;
            case ("deleted"):
                accountStatus = AccountStatus.DELETED;
                break;
            case ("banned"):
                accountStatus = AccountStatus.BANNED;
                break;
            default:
                assert false;
                writer.print(ERRORINPUT);
        }
        try {
            accountController.create(content, accountStatus);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        assert false;
        writer.print(OPERATIONSUCCEFSSUL);

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idString = request.getParameter("id");
        Long id = Long.valueOf(idString);
        List<Account> accounts = null;
        try {
            accounts = accountController.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PrintWriter writer = null;
        writer = response.getWriter();
        assert false;
        assert writer != null;
        boolean checkId = accounts.stream().anyMatch(p -> p.getId().equals(id));
        if (checkId) {
            try {
                accountController.deleteById(id);
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
        String status = request.getParameter("status");
        String content = request.getParameter("content");
        List<Account> accounts = null;
        try {
            accounts = accountController.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PrintWriter writer = null;
        writer = response.getWriter();
        assert false;
        assert writer != null;
        AccountStatus accountStatus = null;
        switch (status) {
            case ("active"):
                status = "ACTIVE";
                break;
            case ("deleted"):
                status = "DELETED";
                break;
            case ("banned"):
                status = "BANNED";
                break;
            default:
                assert false;
                writer.print(ERRORINPUT);
        }
        boolean checkId = accounts.stream().anyMatch(p -> p.getId().equals(id));
        if (checkId) {
            try {
                accountController.update(id, content, status);
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

