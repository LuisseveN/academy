CREATE SEQUENCE IF NOT EXISTS SEQ_RACK_ID;
create table if not exists T_RACK
(
    ID               bigint        not null
        primary key,
    SERIAL_NUMBER    varchar(16)                           not null
        unique,
    TEAM_ID          bigint
        references T_TEAM,
    CREATED_AT       date   default now(),
    DEFAULT_LOCATION varchar(255),
    STATUS           varchar(20) default 'Available'::character varying
        constraint status_constraint
            check ((STATUS)::text = ANY
                   (ARRAY [('Available'::character varying)::text, ('Booked'::character varying)::text, 'Unavailable'::text])),
    ASSEMBLED_AT     date,
    MODIFIED_AT      date   default now()
);

