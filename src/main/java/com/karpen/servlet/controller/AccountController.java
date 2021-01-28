package com.karpen.servlet.controller;

import com.karpen.servlet.model.Account;
import com.karpen.servlet.model.AccountStatus;
import com.karpen.servlet.repository.impl.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AccountController {
    private final HibernateAccountRepoImpl hibernateAccountRepo = new HibernateAccountRepoImpl();

    public Account create(String content, AccountStatus accountStatus) throws IOException, SQLException {
        Account account = new Account();
        account.setContent(content);
        account.setAccountStatus(accountStatus);
        account.setId(0L);
        return hibernateAccountRepo.create(account);
    }

    public Account update(Long id, String content, String accountStatus) throws IOException, SQLException {
        Account account = new Account();
        account.setContent(content);
        account.setAccountStatus(AccountStatus.valueOf(accountStatus));
        account.setId(id);
        return hibernateAccountRepo.update(account);
    }


    public Account getById(Long id) throws IOException, SQLException {
        return hibernateAccountRepo.getById(id);
    }

    public void deleteById(Long id) throws IOException, SQLException {
        hibernateAccountRepo.deleteById(id);
    }

    public List<Account> getAll() throws IOException, SQLException {
        return hibernateAccountRepo.getAll();
    }
}

