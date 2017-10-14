DROP TABLE program_offered;
create table program_offered(
Program_Name varchar2(5) Constraint program_offered_pk primary key, 
description varchar2(20), 
applicant_eligibility varchar2(40) , 
duration number, 
degree_certificate_offered varchar2(10)
);

insert into PROGRAM_OFFERED values('Btech','Computer Science','ssc',4,'true');
DROP TABLE program_Scheduled;
create table program_Scheduled ( 
		Scheduled_program_id varchar2(5) Constraint program_Scheduled_pk primary key, 
		Program_Name Constraint program_Scheduled_fk references program_offered(Program_Name) ON DELETE CASCADE, 
		Location varchar2(10), 
		start_date date, 
		end_date date, 
		sessions_per_week number
);

select * from program_Scheduled;
insert into PROGRAM_SCHEDULED values('1001','Btech','mumbai','02-FEB-2017','01-FEB-2021',5);
DROP TABLE PROGRAM_SCHEDULED;
DROP TABLE applicants;

create table applicants( 
	Application_id number  Constraint applicants_pk primary key,
	full_name varchar2(20),
	date_of_birth date,
	highest_qualification varchar2(10),
	marks_obtained number,
	goals varchar2(20), 
	email_id varchar2(20) unique, 
	Scheduled_program_id  Constraint applicants_Fk references program_Scheduled(Scheduled_program_id) ON DELETE CASCADE,
	status varchar2(10) default 'applied' CONSTRAINT check_const_status check(status IN('applied','accepted', 'rejected','confirmed' )),
	Date_Of_Interview date 
);

select * from applicants;
DROP TABLE participants;
create table participants(
Roll_no varchar2(5) Constraint participants_Pk primary key, 
email_id varchar2(20) Constraint participants_check unique, 
Application_id Constraint participants_Fk references applicants(Application_id) ON DELETE CASCADE, 
Scheduled_program_id varchar2(5)
);

drop table users;
create table users (
login_id varchar2(5)  Constraint users_Pk primary key, 
password varchar2(10),
role varchar2(5) Constraint users_check check (role IN ('mac','admin'))
);

create sequence application_id_seq
start with 10000
increment by 1;

create sequence program_id_seq
start with 1000
increment by 1;

create sequence roll_no_seq
start with 13000
increment by 1;


insert into users values('akp11','akp11','mac');
insert into users values('akh12','akh12','mac');
insert into users values('sat13','sat13','mac');
insert into users values('abh11','abh11','admin');
insert into users values('rah12','rah12','admin');
insert into users values('ros13','ros13','admin');
insert into users values('vip14','vip14','admin');