select student.name, student.age, faculty.name
from student
left join faculty on student.faculty_id = faculty.id
order by faculty.name;

select student.name, student.age
from student
inner join avatar on student.id = avatar.student_id;

-- 2 способ
select student.name, student.age
from student
where student.id in (select student_id from avatar);