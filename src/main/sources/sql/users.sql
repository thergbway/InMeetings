create user proj_user with password '123';

grant select on manager to proj_user;
grant select on meeting to proj_user;
grant select on participant to proj_user;
grant select on role to proj_user;
grant select on user_total to proj_user;

grant update on manager to proj_user;
grant update on meeting to proj_user;
grant update on participant to proj_user;
grant update on user_total to proj_user;
grant update on role to proj_user;

grant insert on manager to proj_user;
grant insert on meeting to proj_user;
grant insert on participant to proj_user;
grant insert on user_total to proj_user;
grant insert on role to proj_user;
grant insert on log to proj_user;

grant delete on participant to proj_user;
grant delete on manager to proj_user;

grant usage, select on sequence manager_id_seq to proj_user;
grant usage, select on sequence meeting_id_seq to proj_user;
grant usage, select on sequence participant_id_seq to proj_user;
grant usage, select on sequence role_id_seq to proj_user;
grant usage, select on sequence user_total_id_seq to proj_user;

drop OWNED BY proj_user;
drop user proj_user;