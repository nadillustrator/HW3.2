package uk.hogwarts.school.service;

import org.springframework.stereotype.Service;
import uk.hogwarts.school.model.Student;
import uk.hogwarts.school.repositories.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
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

    public Collection<Student> sortStudentsByAge(int age) {
        List<Student> sortedStudents = getAll().stream()
                .filter(student -> (age == student.getAge()))
                .collect(Collectors.toList());
        if (sortedStudents.isEmpty()) {
            return null;
        }
        return sortedStudents;
    }
}
