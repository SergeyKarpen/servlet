package com.karpen.servlet;

import com.karpen.servlet.util.HibernateSessionFactory;
import com.karpen.servlet.view.MainMenu;
import org.hibernate.SessionFactory;

import java.sql.SQLException;

class Run {
//    private static SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();


    public static void main(String[] args) throws SQLException {

        MainMenu runner = new MainMenu();
        try {
            runner.showMainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
