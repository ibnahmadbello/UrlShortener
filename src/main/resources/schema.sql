create table if not exists Url(
id varchar(4) not null,
orginal_url clob(25) not null,
short_url varchar(10) not null,
created_at timestamp not null,
expires_at timestamp not null
);
