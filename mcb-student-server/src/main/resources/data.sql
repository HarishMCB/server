--Initial Data Setup
--Groups
insert into groups (group_id, group_name) values ('1','group1');
insert into groups (group_id, group_name) values ('2','group2');
insert into groups (group_id, group_name) values ('3','group3');
insert into groups (group_id, group_name) values ('4','group4');

--Subjects

insert into subjects (subject_id,title) values(1, 'Maths');

insert into subjects (subject_id,title) values(2, 'English');

insert into subjects (subject_id,title) values(3, 'Computer Science');

insert into subjects (subject_id,title) values(4, 'DSA');

insert into subjects (subject_id,title) values(5, 'Digital tech');
--Students
insert into Students(id,last_name,first_name,group_id) values (1, 'MCB','Harish',1);
insert into Students(id,last_name,first_name,group_id) values (2, 'doe','john',1);
insert into Students(id,last_name,first_name,group_id) values (3, 'Richie','dennis',2);
insert into Students(id,last_name,first_name,group_id) values (4, 'MCB1','Harish1',1);
insert into Students(id,last_name,first_name,group_id) values (5, 'MCB2','Harish2',2);
--marks

insert into Marks (Mark,Subject_id,student_id) values (100,1,3);
insert into Marks (Mark,Subject_id,student_id) values (100,2,3);
insert into Marks (Mark,Subject_id,student_id) values (100,3,3);
insert into Marks (Mark,Subject_id,student_id) values (100,4,3);
insert into Marks (Mark,Subject_id,student_id) values (100,5,3);

insert into Marks (Mark,Subject_id,student_id) values (10,1,1);
insert into Marks (Mark,Subject_id,student_id) values (10,2,1);
insert into Marks (Mark,Subject_id,student_id) values (10,3,1);
insert into Marks (Mark,Subject_id,student_id) values (10,4,1);
insert into Marks (Mark,Subject_id,student_id) values (10,5,1);

insert into Marks (Mark,Subject_id,student_id) values (200,1,2);
insert into Marks (Mark,Subject_id,student_id) values (300,2,2);
insert into Marks (Mark,Subject_id,student_id) values (400,3,2);
insert into Marks (Mark,Subject_id,student_id) values (500,4,2);
insert into Marks (Mark,Subject_id,student_id) values (600,5,2);

insert into Marks (Mark,Subject_id,student_id) values (100,1,4);
insert into Marks (Mark,Subject_id,student_id) values (100,2,4);
insert into Marks (Mark,Subject_id,student_id) values (100,3,4);
insert into Marks (Mark,Subject_id,student_id) values (100,4,4);
insert into Marks (Mark,Subject_id,student_id) values (100,5,4);

insert into Marks (Mark,Subject_id,student_id) values (100,1,3);
insert into Marks (Mark,Subject_id,student_id) values (100,1,3);
insert into Marks (Mark,Subject_id,student_id) values (100,1,3);
insert into Marks (Mark,Subject_id,student_id) values (100,1,3);
insert into Marks (Mark,Subject_id,student_id) values (100,1,3);

--Teachers

insert into teachers (teacher_id,group_id,subject_id) values (1,1,1);
insert into teachers (teacher_id,group_id,subject_id) values (2,1,2);
insert into teachers (teacher_id,group_id,subject_id) values (3,2,3);
insert into teachers (teacher_id,group_id,subject_id) values (4,2,4);
insert into teachers (teacher_id,group_id,subject_id) values (5,1,5);

INSERT INTO role (id, description, name) VALUES (4, 'Admin role', 'ADMIN');
INSERT INTO role (id, description, name) VALUES (5, 'User role', 'USER');

insert into users(username,password,email,phone,account_Non_Locked,failed_attempt) values ('Harish','$2a$10$6QvHtxN7RCpliXAoPrbg4ulDDKaFL3PeV.wLvmE4l.uCVTLRGRu3C','Harish.MCB@gmail.com','1234567891','1',0);
insert into user_roles(user_id,role_id) values (1,5);


