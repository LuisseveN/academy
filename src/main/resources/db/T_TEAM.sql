CREATE SEQUENCE IF NOT EXISTS SEQ_TEAM_ID;
CREATE TABLE IF NOT EXISTS T_TEAM
(ID bigint primary key not null ,
NAME varchar(255),
PRODUCT varchar(255),
CREATED_AT date default now(),
MODIFIED_AT date default now(),
DEFAULT_LOCATION varchar(255));
