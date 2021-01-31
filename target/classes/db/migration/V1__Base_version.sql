create table account
(
    id      bigint auto_increment,
    status  varchar(255) null,
    content varchar(255) null,
    constraint account_id_uindex
        unique (id)
);

alter table account
    add primary key (id);

create table developer
(
    id_developer bigint auto_increment,
    first_name   varchar(255) null,
    last_name    varchar(255) null,
    account_id   bigint       null,
    constraint developer_id_developer_uindex
        unique (id_developer),
    constraint developer_account_id_fk
        foreign key (account_id) references account (id),
    constraint FK797nvx3iu2b2h8h56stx30gxy
        foreign key (account_id) references account (id)
);

alter table developer
    add primary key (id_developer);

create table developer_skill
(
    Developer_id_developer bigint not null,
    skills_id              bigint not null,
    primary key (Developer_id_developer, skills_id)
)
    engine = MyISAM;

create index FKmaa0ahcf8xy71swm3176rye0l
    on developer_skill (skills_id);

create table skill
(
    id   bigint auto_increment,
    name varchar(255) null,
    constraint skill_id_uindex
        unique (id)
);

alter table skill
    add primary key (id);

