create table dataset_field (
    id					bigserial primary key,
    data_type			varchar not null,
    name 				varchar not null,
    dataset_id			bigint not null,
	dataset_field_type 	varchar not null,

    foreign key (dataset_id) references dataset_catalog (id),
    unique (dataset_id, name)
)
