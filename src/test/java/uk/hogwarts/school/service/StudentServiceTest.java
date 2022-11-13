package uk.hogwarts.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.hogwarts.school.model.Student;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static uk.hogwarts.school.service.ConstantTestValues.*;

class StudentServiceTest {

    private StudentService studentService;

////    @BeforeEach
////    public void beforeEach() {
////        studentService = new StudentService();
////    }
//
//
//    @Test
//    void positiveCreateStudentTest() {
//        Student expected = studentService.createStudent(student1);
//        Assertions.assertEquals(expected, student1);
//    }
//
//    @Test
//    void positiveCreateStudentTest2() {
//        studentService.createStudent(student1);
//        studentService.createStudent(student2);
//        studentService.createStudent(student3);
//        studentService.createStudent(student4);
//        studentService.createStudent(student5);
//        assertThat(studentService.getAll().toArray())
//                .containsExactly(student1, student2, student3, student4, student5);
//    }
//
//    @Test
//    void negativeCreateStudentTest() {
//        studentService.createStudent(student1);
//        Assertions.assertNull(studentService.createStudent(student1));
//    }
//
//    @Test
//    void positiveGetStudentTest() {
//        studentService.createStudent(student1);
//        studentService.createStudent(student2);
//        studentService.createStudent(student3);
//        assertEquals(studentService.getStudent(2L), student2);
//    }
//
//    @Test
//    void negativeGetStudentTest() {
//        studentService.createStudent(student1);
//        studentService.createStudent(student2);
//        studentService.createStudent(student3);
//        assertNull(studentService.getStudent(6L));
//    }
//
//
//    @Test
//    void positiveUpdateStudentTest() {
//        studentService.createStudent(student1);
//        assertEquals(studentService.updateStudent(student3), student3);
//    }
//
//    @Test
//    void negativeUpdateStudentTest() {
//        studentService.createStudent(student1);
//        assertNull(studentService.updateStudent(student3));
//    }
//
//    @Test
//    void positiveDeleteStudentTest() {
//        studentService.createStudent(student1);
//        studentService.createStudent(student2);
//        studentService.createStudent(student3);
//        studentService.deleteStudent(2L);
//        assertThat(studentService.getAll().toArray())
//                .containsExactly(student1, student3);
//    }
//
////    @Test
////    void negativeDeleteStudentTest() {
////        studentService.createStudent(student1);
////        studentService.createStudent(student2);
////        studentService.createStudent(student3);
////        assertNull(studentService.deleteStudent(5));
////    }
//
//    @Test
//    void positiveSortStudentsByAgeTest() {
//        studentService.createStudent(student1);
//        studentService.createStudent(student2);
//        studentService.createStudent(student3);
//        studentService.createStudent(student4);
//        studentService.createStudent(student5);
//        studentService.createStudent(student6);
//        studentService.createStudent(student7);
//        assertThat(studentService.sortStudentsByAge(18).toArray())
//                .containsExactly(student3, student5);
//    }
//
//    @Test
//    void negativeSortStudentsByAgeTest() {
//        studentService.createStudent(student1);
//        studentService.createStudent(student2);
//        studentService.createStudent(student3);
//        studentService.createStudent(student4);
//        studentService.createStudent(student5);
//        studentService.createStudent(student6);
//        studentService.createStudent(student7);
//        assertNull(studentService.sortStudentsByAge(30));
//    }
}