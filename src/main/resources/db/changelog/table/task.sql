create table task (
    id				bigserial primary key,
    created			timestamp,
    request			text not null,
    task_status		varchar	not null,
    username		varchar not null
)
