CREATE SEQUENCE IF NOT EXISTS SEQ_RACK_ASSET_ID;
create table if not exists t_rack_asset
(
    id        bigint not null
        primary key,
    asset_tag varchar(32)                    not null,
    rack_id   bigint                           not null
        references T_RACK
);

