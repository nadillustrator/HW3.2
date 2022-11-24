package uk.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import uk.hogwarts.school.model.AvatarsByPage;
import uk.hogwarts.school.model.Faculty;
import uk.hogwarts.school.model.Student;
import uk.hogwarts.school.repositories.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AvatarService.class);
    private final FacultyService facultyService;
    private final StudentRepository studentRepository;

    public StudentService(FacultyService facultyService, StudentRepository studentRepository) {
        this.facultyService = facultyService;
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        LOGGER.debug("Method createStudent was invoked");
        return studentRepository.save(student);
    }


    public Student getStudent(Long id) {
        LOGGER.debug("Method getStudent was invoked");
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateStudent(Student student) {
        LOGGER.debug("Method updateStudent was invoked");
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        LOGGER.debug("Method deleteStudent was invoked");
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAll() {
        LOGGER.debug("Method getAll was invoked");
        return studentRepository.findAll();
    }

    public Collection<Student> findStudentsByAgeDiapason(int min, int max) {
        LOGGER.debug("Method findStudentsByAgeDiapason was invoked");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Collection<Student> findStudentsByFaculty(long id) {
        LOGGER.debug("Method findStudentsByFaculty was invoked");
        return studentRepository.findStudentByFaculty_Id(id);
    }

    public Faculty getFacultyByStudent(long id) {
        LOGGER.debug("Method getFacultyByStudent was invoked");
        Student student = getStudent(id);
        Collection<Faculty> faculties = facultyService.getAll();
        return faculties.stream().filter(faculty -> faculty.getStudents().contains(student))
                .findFirst().orElse(null);
    }

    public Integer getAmountOfStudents() {
        LOGGER.debug("Method getAmountOfStudents was invoked");
        return studentRepository.getAmountOfStudents();
    }

    public Double getAverageAgeOfStudents() {
        LOGGER.debug("Method getAverageAgeOfStudents was invoked");
        return studentRepository.getAverageAgeOfStudents();
    }

    ;

    public List<Student> getLastFiveOfStudentsById() {
        LOGGER.debug("Method getLastFiveOfStudentsById was invoked");
        return studentRepository.getLastOfStudentsById();
    }

    public List<String> getAllStudentsStartingWithLetter(String letter) {
        LOGGER.debug("Method getAllStudentsStartingWithLetter was invoked");
        return studentRepository.findAll()
                .parallelStream()
                .filter(s -> s.getName().startsWith(letter.toUpperCase()))
                .map(s -> s.getName().toUpperCase())
                .collect(Collectors.toList());
    }


    public Double getAverageAgeOfAllStudents() {
        LOGGER.debug("Method getAverageAgeOfAllStudents was invoked");
        return studentRepository.findAll()
                .stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0);
    }

}
