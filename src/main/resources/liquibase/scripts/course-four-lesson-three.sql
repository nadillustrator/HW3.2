 -- liquibase formatted sql

 -- changeSet nadillustrator:1
create index student_name_index on student (name);

 -- changeSet nadillustrator:2
create index faculty_name_color_index on faculty (name, color);



