CREATE SEQUENCE IF NOT EXISTS SEQ_BOOKING_ID;
create table if not exists t_booking
(
    id           bigint not null
        primary key,
    rack_id      bigint
        references T_RACK,
    requester_id bigint
        references T_TEAM_MEMBER,
    book_from    date,
    book_to      date,
    created_at   date default now(),
    modified_at  date default now()
);

