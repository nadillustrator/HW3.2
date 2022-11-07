select * from student;

select * from student where age >= 17 and age <= 19;

select name from student;

select * from student where name like '%o%' or name like 'O%';

select * from student where age < 17;

select * from student order by age;

select * from student order by age desc;

select s.* from faculty as f, student as s
where s.faculty_id = f.id
  and f.name = 'Gryffindor';

select f.* from faculty as f, student as s
where s.faculty_id = f.id
  and s.name = 'Harry Potter';