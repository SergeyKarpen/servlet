package com.karpen.servlet.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateSessionFactory {
    private static  SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Ошибка метода SessionFactory " + ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
