create table ordered_dataset (
    id			bigserial primary key,
    username	varchar not null,
    timestamp 	timestamp not null,
	dataset_id	bigint not null,

	foreign key (dataset_id) references dataset_catalog (id),
	unique (dataset_id, username)
)
