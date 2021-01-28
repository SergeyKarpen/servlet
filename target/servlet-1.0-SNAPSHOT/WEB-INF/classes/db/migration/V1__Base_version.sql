create table account
(
    id      bigint       not null,
    status  varchar(255) null,
    content varchar(255) null,
    constraint account_id_uindex
        unique (id)
);

alter table account
    add primary key (id);

create table developer
(
    id_developer bigint       not null,
    firstName    varchar(255) null,
    lastName     varchar(255) null,
    account_id   bigint       null,
    constraint developer_id_developer_uindex
        unique (id_developer),
    constraint developer_account_id_fk
        foreign key (id_developer) references account (id)
);

alter table developer
    add primary key (id_developer);


create table skill
(
    id   bigint       not null,
    name varchar(255) null,
    constraint skill_id_uindex
        unique (id)
);

alter table skill
    add primary key (id);

create table developer_skill
(
    developer_id_developer bigint not null,
    skill_id_skill         bigint not null,
    constraint developer_skill_developer_id_developer_fk
        foreign key (developer_id_developer) references developer (id_developer),
    constraint developer_skill_skill_id_fk
        foreign key (skill_id_skill) references skill (id)
);

