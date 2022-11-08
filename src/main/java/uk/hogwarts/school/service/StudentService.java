package uk.hogwarts.school.service;

import org.springframework.stereotype.Service;
import uk.hogwarts.school.model.Faculty;
import uk.hogwarts.school.model.Student;
import uk.hogwarts.school.repositories.StudentRepository;

import java.util.*;

@Service
public class StudentService {

    private final FacultyService facultyService;
    private final StudentRepository studentRepository;

    public StudentService(FacultyService facultyService, StudentRepository studentRepository) {
        this.facultyService = facultyService;
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }


    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }

    public Collection<Student> findStudentsByAgeDiapason(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public Collection<Student> findStudentsByFaculty(long id) {
        return studentRepository.findStudentByFaculty_Id(id);
    }

    public Faculty getFacultyByStudent(long id) {
        Student student = getStudent(id);
        Collection<Faculty> faculties = facultyService.getAll();
        return faculties.stream().filter(faculty -> faculty.getStudents().contains(student))
                .findFirst().orElse(null);
    }
}
