package com.karpen.servlet.repository.impl;

import com.karpen.servlet.model.Skill;
import com.karpen.servlet.repository.HibernateSkillRepo;
import com.karpen.servlet.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateSkillRepoImpl implements HibernateSkillRepo {
    private static Session session = null;

    @Override
    public Skill create(Skill skill) {
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(skill);
            session.getTransaction().commit();
        } catch (Throwable ex) {
            System.err.println("Ошибка при создании навыка(skill). Метод create - ERROR " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(skill);
            session.getTransaction().commit();
        } catch (Throwable ex) {
            System.err.println("Ошибка при обновлении навыка(skill). Метод update - ERROR " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return skill;
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> skills = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            skills = session.createQuery("FROM Skill").list();
            transaction.commit();
        } catch (Throwable ex) {
            System.err.println("Ошибка при выводе всех навыков(skills). Метод update - ERROR " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return skills;
    }

    @Override
    public void deleteById(Long id_skill) {
        Skill skill = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            skill = (Skill) session.get(Skill.class, id_skill);
            session.delete(skill);
            session.getTransaction().commit();
        } catch (Throwable ex) {
            System.err.println("Ошибка при удалении навыка(skill). Метод deleteById - ERROR " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Skill getById(Long id_skill) {
        Skill skill = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            skill = (Skill) session.load(Skill.class, id_skill);
        } catch (Throwable ex) {
            System.err.println("Ошибка при нахождении навыка(skill). Метод getById - ERROR " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return skill;
    }

}
