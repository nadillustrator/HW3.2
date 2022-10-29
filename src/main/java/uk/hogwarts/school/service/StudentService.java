package uk.hogwarts.school.service;

import org.springframework.stereotype.Service;
import uk.hogwarts.school.model.Student;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final Map<Long, Student> students = new HashMap<>();


    public Student createStudent(Student student) {
        if (students.containsValue(student)) {
            return null;
        }
        students.put(student.getId(), student);
        return student;
    }


    public Student getStudent(Long id) {
        if (students.containsKey(id)) {
            return students.get(id);
        }
        return null;
    }

    public Student updateStudent(Long id, Student student) {
        if (students.containsKey(id)) {
            students.put(id, student);
            return student;
        }
        return null;
    }

    public Student deleteStudent(Long id) {
        if (students.containsKey(id)) {
            Student student = students.get(id);
            students.remove(id);
            return student;
        }
        return null;
    }

    public Collection<Student> getAll() {
        return new ArrayList<>(students.values());
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
