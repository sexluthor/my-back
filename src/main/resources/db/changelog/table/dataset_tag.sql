create table dataset_tag (
    id			bigserial primary key,
    name 		varchar not null,
	dataset_id	bigint not null,

	foreign key (dataset_id) references dataset_catalog (id),
	unique (dataset_id, name)
)
