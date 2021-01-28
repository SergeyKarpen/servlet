package com.karpen.servlet.repository.impl;

import com.karpen.servlet.model.Developer;
import com.karpen.servlet.repository.HibernateDeveloperRepo;
import com.karpen.servlet.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class HibernateDeveloperRepoImpl implements HibernateDeveloperRepo {
    private static Session session = null;

    @Override
    public Developer create(Developer developer) {
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(developer);
            session.getTransaction().commit();
        } catch (Throwable ex) {
            System.err.println("Ошибка при создании разработчика(developer). Метод create - ERROR " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return developer;
    }

    @Override
    public Developer update(Developer developer) {
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(developer);
            session.getTransaction().commit();
        } catch (Throwable ex) {
            System.err.println("Ошибка при обновлении разработчика(developer). Метод update - ERROR " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return developer;
    }

    @Override
    public List<Developer> getAll() {
        List<Developer> developers = new ArrayList<>();
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            developers = session.createQuery("FROM Developer ").list();
            transaction.commit();
        } catch (Throwable ex) {
            System.err.println("Ошибка при выводе всех разработчиков(developers). Метод getAll - ERROR " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return developers;
    }

    public Developer getById(Long id_developer) {
        Developer developer = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            developer = session.load(Developer.class, id_developer);
        } catch (Throwable ex) {
            System.err.println("Ошибка при нахождении навыка(skill). Метод getById - ERROR " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return developer;
    }

    @Override
    public void deleteById(Long aLong) {
        Developer developer = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            developer = session.get(Developer.class, developer.getId());
            session.delete(developer);
            session.getTransaction().commit();
        } catch (Throwable ex) {
            System.err.println("Ошибка при удалении разработчика(developer). Метод deleteById - ERROR " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
