-- Vtb clients
insert into dataset_field (id, data_type, name, dataset_id, dataset_field_type, is_primary) values
	(1, 'BIGINT', 'user_id', 1, 'user_id', true),
	(2, 'VARCHAR', 'first_name', 1, 'first_name', false),
	(3, 'VARCHAR', 'last_name', 1, 'last_name', false),
	(4, 'DATE', 'birthday', 1, 'birthday', false),
	(5, 'VARCHAR', 'email', 1, 'email', false),
	(6, 'VARCHAR', 'phone', 1, 'phone', false);
