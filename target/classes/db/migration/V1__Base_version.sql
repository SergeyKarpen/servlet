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
    first_name    varchar(255) null,
    last_name     varchar(255) null,
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

create table skill
(
    id   bigint auto_increment,
    name varchar(255) null,
    constraint skill_id_uindex
        unique (id)
);

alter table skill
    add primary key (id);

create table developer_skill
(
    developer bigint not null,
    skill     bigint not null,
    primary key (developer, skill),
    constraint developer_skill_developer_id_developer_fk
        foreign key (developer) references developer (id_developer),
    constraint developer_skill_skill_id_fk
        foreign key (skill) references skill (id)
);

