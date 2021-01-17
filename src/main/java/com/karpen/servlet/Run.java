package com.karpen.servlet;

import com.karpen.servlet.util.HibernateSessionFactory;
import com.karpen.servlet.view.MainMenu;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import java.sql.SQLException;

class Run {
//    private static SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    static Logger log = Logger.getLogger(Run.class);

    public static void main(String[] args) throws SQLException {
        BasicConfigurator.configure();
        log.info("This is Logger Info");
        MainMenu runner = new MainMenu();
        try {
            runner.showMainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
