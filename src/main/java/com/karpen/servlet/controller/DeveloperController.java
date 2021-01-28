package com.karpen.servlet.controller;

import com.karpen.servlet.model.Developer;
import com.karpen.servlet.model.Skill;
import com.karpen.servlet.repository.impl.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeveloperController {

    private final HibernateAccountRepoImpl hibernateAccountRepo = new HibernateAccountRepoImpl();
    private final HibernateSkillRepoImpl hibernateSkillRepo = new HibernateSkillRepoImpl();
    private final HibernateDeveloperRepoImpl hibernateDeveloperRepo = new HibernateDeveloperRepoImpl();

    public Developer create(String firstName, String lastName, Long id_account, Set <Long> skillId) throws IOException, SQLException {
        Developer developer = new Developer();
        Set<Skill> skills = new HashSet<>();
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setAccount(hibernateAccountRepo.getById(id_account));
        for (Long aLong : skillId) {
            skills.add(hibernateSkillRepo.getById(aLong));
        }
        developer.setSkills(skills);
        return hibernateDeveloperRepo.create(developer);
    }

    public Developer update(String firstname, String lastName, Long id, Long id_account, Set<Long> skillId) throws IOException, SQLException {
        Developer developer = new Developer();
        Set<Skill> skills = new HashSet<>();
        developer.setFirstName(firstname);
        developer.setLastName(lastName);
        developer.setId(id);
        developer.setAccount(hibernateAccountRepo.getById(id_account));
        for (Long aLong : skillId) {
            skills.add(hibernateSkillRepo.getById(aLong));
        }
        developer.setSkills(skills);
        return hibernateDeveloperRepo.update(developer);
    }

    public Developer getById(Long id) throws IOException, SQLException {
        return hibernateDeveloperRepo.getById(id);
    }

    public void deleteById(Long id) throws IOException, SQLException {
        hibernateDeveloperRepo.deleteById(id);
    }

    public List<Developer> getAll() throws IOException, SQLException {
        return hibernateDeveloperRepo.getAll();
    }
}
