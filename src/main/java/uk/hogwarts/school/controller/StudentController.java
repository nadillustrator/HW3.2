package uk.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.hogwarts.school.model.Student;
import uk.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping(path = "student")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping  // POST http:localhost:8080/student
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student newStudent = studentService.createStudent(student);
        if (newStudent == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newStudent);
    }

    @GetMapping("{id}")  // GET http:localhost:8080/student/1
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping   // PUT http:localhost:8080/student
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student newStudent = studentService.updateStudent(student);
        if (newStudent == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newStudent);
    }

    @DeleteMapping(path = "{id}")   // DELETE http:localhost:8080/student/1
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/getAll")   // GET http:localhost:8080/student/getAll
    public ResponseEntity<Collection<Student>> getAll() {
        Collection<Student> students = studentService.getAll();
        if (students == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping(path = "/age{age}")   // GET http:localhost:8080/student/age/18
    public ResponseEntity<Collection<Student>> sortStudentsByAge(@PathVariable int age) {
        Collection<Student> students = studentService.sortStudentsByAge(age);
        if (students == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }
}
