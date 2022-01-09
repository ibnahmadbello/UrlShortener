create table if not exists Url(
id long not null,
orginal_url clob not null,
short_link varchar(50) not null,
created_at timestamp not null,
expires_at timestamp not null
);
