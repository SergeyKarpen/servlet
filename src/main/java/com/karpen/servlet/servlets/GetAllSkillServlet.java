package com.karpen.servlet.servlets;

import com.karpen.servlet.model.Skill;
import com.karpen.servlet.repository.HibernateSkillRepo;
import com.karpen.servlet.repository.impl.HibernateSkillRepoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class GetAllSkillServlet   {
    HibernateSkillRepo hibernateSkillRepo = new HibernateSkillRepoImpl();
/*
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println(hibernateSkillRepo.getAll());
        super.doGet(req, resp);
    }
    
 */
}

