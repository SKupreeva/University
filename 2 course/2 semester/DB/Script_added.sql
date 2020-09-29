create schema Kupreeva;

--создание таблиц и их заполнение

create table kupreeva.Bank
(
	Id_Bank serial primary key,
	Bank_Name character varying(30)
);

insert into kupreeva.bank (Bank_Name) values
('IdeaBank'),
('BelVEB'),
('Bank Reshenie'),
('Bank Dabrabyt'),
('AbsolutBank'),
('AlphaBank'),
('BPS-Sberbank'),
('BSBBank'),
('BTABank'),
('VTBBank'),
('BelGazPromBank'),
('BelAgroPromBank'),
('BelarusBank'),
('BelInvestBank'),
('RRBBank'),
('PRIORBank'),
('MTBBank'),
('StatusBank'),
('FransaBank'),
('TKBank');

create table kupreeva.Department
(
	Id_Department serial primary key,
	Address character varying(50),
	Id_Bank integer references Bank (Id_Bank)
);

insert into kupreeva.department (address, Id_Bank) values 
('Address 1', 3),
('Address 2', 3),
('Address 3', 1),
('Address 4', 2),
('Address 5', 2),
('Address 6', 4),
('Address 7', 13),
('Address 8', 17),
('Address 9', 4),
('Address 10', 7);

create table kupreeva.ATM
(
	Id_ATM serial primary key,
	Address character varying(50),
	Id_Bank integer references Bank (Id_Bank)
);

insert into kupreeva.atm (address, Id_Bank ) values
('Address 1', 5),
('Address 2', 2),
('Address 3', 19),
('Address 4', 3),
('Address 5', 7),
('Address 6', 12),
('Address 7', 12),
('Address 8', 1),
('Address 9', 9),
('Address 10', 3);

create table kupreeva.Customer
(
	Id_Customer serial primary key,
	First_Name character varying(30),
	Second_Name character varying(30),
	Phone_Number integer,
	Id_Bank integer references Bank(Id_Bank)
);

insert into kupreeva.customer (first_name, second_name, phone_number, Id_Bank) values
('Client1', 'Client1', 234523375, 4),
('Client2', 'Client2', 341243598, 17),
('Client3', 'Client3', 675324467, 20),
('Client4', 'Client4', 124603576, 3),
('Client5', 'Client5', 457900652, 4);

create table kupreeva.Employee
(
	Id_Employee serial primary key,
	First_Name character varying(30),
	Second_Name character varying(30),
	Phone_Number integer,
	Id_Bank integer references Bank (Id_Bank)
);

insert into kupreeva.employee (first_name, second_name, phone_number, Id_Bank) values
('Employee1', 'Employee1', 658237458, 4),
('Employee2', 'Employee2', 895209457, 2),
('Employee3', 'Employee3', 904558237, 13),
('Employee4', 'Employee4', 246453490, 11),
('Employee5', 'Employee5', 456255504, 13);

--просмотр содержимого таблицы bank

select * from kupreeva.bank;

-- создание хранимых процедур и их просмотр

create or replace function counting (in a text, out c int) 
as $$
begin
	case a
		when 'bank' then
			select count(*) into c from kupreeva.bank;
		when 'employee' then
			select count(*) into c from kupreeva.employee;
		when 'atm' then
			select count(*) into c from kupreeva.atm;
		when 'customer' then
			select count(*) into c from kupreeva.customer;
		when 'department' then
			select count(*) into c from kupreeva.department;
end case;
end;
$$ language plpgsql;

select * from counting('bank');

--добавление нового столбца 

alter table kupreeva.customer add column date_of_request date;

update kupreeva.customer set date_of_request = '2020-03-24'
where date_of_request  is null;

create or replace procedure add_day (inout op text, inout n integer)
language plpgsql
as $$
begin
	case op
	when 'plus' then 
		update kupreeva.customer  
		set date_of_request = date_of_request + n;
	when 'minus' then
		update kupreeva.customer 
		set date_of_request = date_of_request - n;
end case;
end
$$

call add_day ('plus', 7);
select * from kupreeva.customer;


create or replace function curr_numbs (inout n text, out mi bigint, out ma bigint, out av bigint, out s bigint)
as $$
begin
	if n = 'phone_number' then
	select min(phone_number) into mi from kupreeva.employee;
	select max(phone_number) into ma from kupreeva.employee;
	select avg(phone_number) into av from kupreeva.employee;
	select sum(phone_number) into s from kupreeva.employee;
	else 
	raise exception 'Error. Other columns of this table are const.';
	end if;
end;
$$ language plpgsql;

select * from curr_numbs('phone_number');

create or replace function two_columns (in a text, in b text) returns setof kupreeva.employee 
as $$
begin
	case
	when a is null and b is null then
		return query select * from kupreeva.employee;
	when a is null then 
		return query select * from kupreeva.employee where second_name = b;
	when b is null then 
		return query select * from kupreeva.employee where first_name = a;
	else
		return query select * from kupreeva.employee where first_name = a and second_name = b;
	end case;
end;
$$ language plpgsql;

select * from two_columns('Employee3', null);


create or replace function str_form (in a text, out b text)
as $$
declare c text;
begin 
	select first_name into c from kupreeva.customer c where second_name = a;
	c = left(c, 1);
	select concat_ws (
		' ',
		lower (a),
		lower (c)
	) into b;
end;
$$ language plpgsql;

select * from str_form('Client4');

--создание таблицы protocol

create table kupreeva.protocol
(
	N serial primary key,
	date_time timestamp  not null,
	users text           not null,
	activity text        not null,
	str_count int        not null
);

select * from kupreeva.protocol p ;

create or replace function dep_trig() returns trigger
as $$
declare c int;
begin 
	c := 0;
		if (TG_OP = 'DELETE') then
			c := c + 1;
            insert into kupreeva.protocol (date_time, users, activity, str_count) 
           	values (now(), user, 'delete', c);
           return old;
        elsif (TG_OP = 'UPDATE') then
        	c := c + 1;
            insert into kupreeva.protocol (date_time, users, activity, str_count) 
           	values (now(), user, 'update', c);
           return new;
        elsif (TG_OP = 'INSERT') then
        	c := c + 1;
            insert into kupreeva.protocol (date_time, users, activity, str_count) 
           	values (now(), user, 'insert', c);
           return new;
        end if;
end;
$$ language plpgsql;

create trigger protocol
after insert or update or delete on kupreeva.atm
	for each row execute procedure dep_trig();

insert into kupreeva.atm (address, Id_Bank) values 
('Address 11', 12);

select * from kupreeva.protocol;

--удаление схемы

drop schema kupreeva cascade;
