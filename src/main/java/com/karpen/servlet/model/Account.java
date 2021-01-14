package com.karpen.servlet.model;

import javax.persistence.*;

@Entity
@Table(name = "account", schema = "servlet")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique = true, nullable = false)
    private Long id;

    @Column (name="content")
    private String content;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", content='" + content + '\'' + ", accountStatus=" + accountStatus;
    }
}
