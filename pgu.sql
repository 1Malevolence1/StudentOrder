CREATE TABLE code_staff(
	code_employee integer not null,
	surname_ varchar(20) not null,
	name_employee varchar(10) not null,

	post text not null,
	telephone varchar(11) not null,
	code_institution integer not null
	salary money not null;
	FOREIGN KEY(code_institution) REFERENCES institution(code_institution) ON DELETE RESTRICT

);

CREATE TABLE institution(
	code_institution integer not null,
	name_institution varchar(30) not null,
	contacts varchar(30) not null,
	description text not null;
);

CREATE TABLE client

CREATE TABLE menu_section(
    code_section integer not null;
    title varchar(20) not null
);

CREATE TABLE menu(
    dish_code integer not null,
    title_dish varchar(20),
    money_title money not null,
    section_code integer not null,
    code_order integer not null
    FOREIGN KEY(section_code) REFERENCES menu_section(code_section) ON DELETE RESTRICT
    FOREIGN KEY(code_order) REFERENCES orders(code_order) ON DELETE RESTRICT
);

CREATE TABLE orders(
    code_order integer not null,
    sum_order money not null,
    date_order date
);
