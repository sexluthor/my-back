create table dataset_catalog (
    id 				bigserial primary key,
    name			varchar not null,
    description		varchar not null,
	price			integer not null,
	last_updated 	timestamp not null
)
