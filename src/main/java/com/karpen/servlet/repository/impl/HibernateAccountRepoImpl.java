package com.karpen.servlet.repository.impl;

import com.karpen.servlet.model.Account;
import com.karpen.servlet.repository.HibernateAccountRepo;
import com.karpen.servlet.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateAccountRepoImpl implements HibernateAccountRepo {
    private static Session session = null;

    @Override
    public Account create(Account account) {
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(account);
            session.getTransaction().commit();
        } catch (Throwable ex) {
            System.err.println("Ошибка при создании учётки(account). Метод create - ERROR " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return account;
    }

    @Override
    public Account update(Account account) {
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(account);
            session.getTransaction().commit();
        } catch (Throwable ex) {
            System.err.println("Ошибка при обновлении учётки(account). Метод update - ERROR " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return account;
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            accounts = session.createQuery("FROM Account").list();
            transaction.commit();
        } catch (Throwable ex) {
            System.err.println("Ошибка при выводе всех учёток(accounts). Метод getAll - ERROR " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return accounts;
    }

    @Override
    public void deleteById(Long id_account) {
        Account account = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            account = session.get(Account.class, id_account);
            session.delete(account);
            session.getTransaction().commit();
        } catch (Throwable ex) {
            System.err.println("Ошибка при удалении учётки(account). Метод deleteById - ERROR " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Account getById(Long id_account) {
        Account account = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            account = session.load(Account.class, id_account);
        } catch (Throwable ex) {
            System.err.println("Ошибка при нахождении учетки(account). Метод getById - ERROR " + ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return account;
    }
}
