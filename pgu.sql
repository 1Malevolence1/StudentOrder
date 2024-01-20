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


SELECT so.*, ro.r_office_area_id, ro.r_office_name,
                    po_h.p_office_area_id as h_p_office_area_id,
                    po_h.p_office_name as h_p_office_name,
                    po_w.p_office_area_id as w_p_office_area_id,
                    po_w.p_office_name as w_p_office_name,
                    soc.*, ro_c.r_office_area_id, ro_c.r_office_name
                    FROM jc_student_order so
                    INNER JOIN jc_register_office ro ON ro.r_office_id = so.register_office_id
                    INNER JOIN jc_passport_office po_h ON po_h.p_office_id = so.h_passport_office_id
                    INNER JOIN jc_passport_office po_w ON po_w.p_office_id = so.w_passport_office_id
                    INNER JOIN jc_student_child soc ON soc.student_order_id = so.student_order_id
                    INNER JOIN jc_register_office ro_c ON ro_c.r_office_id = soc.c_register_office_id
                    WHERE student_order_status = 0 ORDER BY so.student_order_id LIMIT 3