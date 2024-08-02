CREATE SEQUENCE IF NOT EXISTS SEQ_TEAM_MEMBER_ID;
create table T_TEAM_MEMBER
(
    id          bigint not null
        primary key,
    team_id     bigint
        references T_TEAM,
    ctw_id      varchar(255),
    name        varchar(255),
    created_at  date default now(),
    modified_at date default now()
);