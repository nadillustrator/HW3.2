package uk.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.hogwarts.school.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
