package uk.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.hogwarts.school.model.Student;


public interface StudentRepository extends JpaRepository<Student, Long> {
}
