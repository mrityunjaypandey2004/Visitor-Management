CREATE TABLE EMPLOYEE (
emp_id INT NOT NULL AUTO_INCREMENT,
emp_name varchar(200) NOT NULL,
emp_email varchar(200) NOT NULL,
emp_mobile_on varchar(20) NOT NULL,
dept_id varchar(20) NOT NULL,
PRIMARY KEY ( emp_id ),
CONSTRAINT uc_employee UNIQUE (emp_email,emp_mobile_on)
);

CREATE TABLE VISITOR (
visitor_id INT NOT NULL AUTO_INCREMENT,
visitor_name varchar(200) NOT NULL,
visitor_email varchar(200) NOT NULL,
visitor_mobile_on varchar(20) NOT NULL,
visitor_location varchar(200) NOT NULL,
visitor_identification_no varchar(200) NOT NULL,
visitor_identification_type varchar(200) NOT NULL,
PRIMARY KEY ( visitor_id ),
CONSTRAINT uc_visitor UNIQUE (visitor_email,visitor_mobile_on,visitor_identification_no)
);

CREATE TABLE MEETING (
meeting_id INT NOT NULL AUTO_INCREMENT,
meeting_purpose varchar(200) NOT NULL,
meeting_location varchar(200) NOT NULL,
meeting_start_time timestamp NOT NULL,
meeting_end_time timestamp NOT NULL,
PRIMARY KEY ( meeting_id )
);

CREATE TABLE MEETING_EMPLOYEE (
meeting_id INT NOT NULL,
emp_id INT NOT NULL
);

CREATE TABLE MEETING_VISITOR (
meeting_id INT NOT NULL,
visitor_id INT NOT NULL
);


CREATE TABLE VISITOR_MEETING_TRACKER (
visitor_meeting_tracking_id INT NOT NULL AUTO_INCREMENT,
visitor_meeting_code varchar(20) NOT NULL,
meeting_id INT NOT NULL,
visitor_id INT NOT NULL,
checkin_time timestamp NULL,
checkout_time timestamp NULL,
meeting_start_time timestamp NULL,
meeting_end_time timestamp NULL,
visitor_identification_no varchar(200) NULL,
visitor_identification_type varchar(200) NULL,
visitor_photo blob NULL,
visitor_identification_photo blob NULL,
PRIMARY KEY ( visitor_meeting_tracking_id ),
CONSTRAINT uc_visitor_tracking UNIQUE (meeting_id,visitor_id)
);

INSERT INTO EMPLOYEE (emp_name, emp_email, emp_mobile_on, dept_id) VALUES ('Mrityunjay', 'mrityunjay.pandey.2004@gmail.com', '9619002944', 1);
INSERT INTO EMPLOYEE (emp_name, emp_email, emp_mobile_on, dept_id) VALUES ('Rachit', 'rachitshukla888@gmail.com', '8085440064', 1);
INSERT INTO EMPLOYEE (emp_name, emp_email, emp_mobile_on, dept_id) VALUES ('Madhavi', 'madhavi.pandey.1989@gmail.com', '8108034040', 1);

visitor_id | visitor_name | visitor_email                      | visitor_mobile_on | visitor_location | visitor_identification_no | visitor_identification_type |
+------------+--------------+------------------------------------+-------------------+------------------+---------------------------+-----------------------------+
|          1 | 'Jay'        | 'mrityunjay.pandey.2004@gmail.com' | '9619002944'      | 'navi mumbai'    | 'ANCPP1073H'              | 'PANCARD'                   |

INSERT INTO EMPLOYEE (emp_name, emp_email, emp_mobile_on, dept_id) VALUES ('Madhavi', 'madhavi.pandey.1989@gmail.com', '8108034040', 1);


CREATE TABLE LOGIN (
user_id varchar(200) NOT NULL,
user_password varchar(200) NOT NULL,
first_name varchar(200) NOT NULL,
last_name varchar(200) NOT NULL,
email varchar(200) NOT NULL,
phone_number varchar(200) NOT NULL,
PRIMARY KEY ( user_id )
);