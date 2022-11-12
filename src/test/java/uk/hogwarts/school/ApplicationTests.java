package uk.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import uk.hogwarts.school.controller.AvatarController;
import uk.hogwarts.school.model.Student;

import java.net.URI;
import java.util.Set;

import static org.springframework.test.annotation.DirtiesContext.*;

@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    AvatarController avatarController;


    @Test
    void testCreateStudent() {
        Student student = givenStudentWith("Vasia", 17);
        ResponseEntity<Student> response = whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student);
        thenStudentHasBeenCreated(response);
    }

    @Test
    void testGetStudentById() {
        Student student = givenStudentWith("Pavel Romanov", 19);

        ResponseEntity<Student> response = whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student);

        thenStudentHasBeenCreated(response);

        Student createdStudent = response.getBody();
        thenStudentWithIdHasBeenFound(createdStudent.getId(), createdStudent);
    }

    @Test
    void testSortStudentByAge() {
        Student student16 = givenStudentWith("Ivan Ivanov", 16);
        Student student17 = givenStudentWith("Ivan Popov", 17);
        Student student18 = givenStudentWith("Ivan Smirnov", 18);
        Student student19 = givenStudentWith("Ivan Sidorov", 19);
        Student student20 = givenStudentWith("Ivan Nikiforov", 20);


        whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student16);
        whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student17);
        whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student18);
        whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student19);
        whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student20);

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("min", "17");
        queryParams.add("max", "19");

        thenStudentsAreFoundByCriteria(queryParams, student17, student18, student19);
    }

    @Test
    void testUpdateStudent() {
        Student student = givenStudentWith("Ivan Petrov", 16);

        ResponseEntity<Student> responseEntity = whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student);
        thenStudentHasBeenCreated(responseEntity);
        Student createdStudent = responseEntity.getBody();

        whenUpdateStudent(createdStudent, 32, "Petr Sidorov");
        thenStudentHasBeenUpdated(createdStudent, 32, "Petr Sidorov");

    }

    @Test
    void testDeleteStudent() {
        Student student = givenStudentWith("Ivan Petrov", 16);

        ResponseEntity<Student> responseEntity = whenSendingCreateStudentRequest(getUriBuilder().build().toUri(), student);
        thenStudentHasBeenCreated(responseEntity);
        Student createdStudent = responseEntity.getBody();

        whenDeleteStudent(createdStudent, 32, "Petr Sidorov");
        thenStudentNotFound(createdStudent, 32, "Petr Sidorov");

    }

    private void thenStudentNotFound(Student student, int age, String name) {
        URI getUri = getUriBuilder().path("/{id}").buildAndExpand(student.getId()).toUri();
        ResponseEntity<Student> deletedStudentRs = restTemplate.getForEntity(getUri, Student.class);

        Assertions.assertThat(deletedStudentRs.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private void whenDeleteStudent(Student student, int age, String name) {
        restTemplate.delete(getUriBuilder().path("/{id}").buildAndExpand(student.getId()).toUri());

    }

    private void thenStudentHasBeenUpdated(Student student, int age, String name) {
        URI getUri = getUriBuilder().path("/{id}").buildAndExpand(student.getId()).toUri();
        ResponseEntity<Student> updatedStudentRs = restTemplate.getForEntity(getUri, Student.class);

        Assertions.assertThat(updatedStudentRs.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(updatedStudentRs.getBody()).isNotNull();
        Assertions.assertThat(updatedStudentRs.getBody().getAge()).isEqualTo(age);
        Assertions.assertThat(updatedStudentRs.getBody().getName()).isEqualTo(name);
    }

    private void whenUpdateStudent(Student student, int age, String name) {
        student.setName(name);
        student.setAge(age);

        restTemplate.put(getUriBuilder().build().toUri(), student);
    }

    private void thenStudentsAreFoundByCriteria(MultiValueMap<String, String> queryParams, Student... students) {
        URI uri = getUriBuilder().queryParams(queryParams).build().toUri();

        ResponseEntity<Set<Student>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Set<Student>>() {
                });

        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Set<Student> actualResult = response.getBody();
        resetIds(actualResult);
        Assertions.assertThat(actualResult).containsExactlyInAnyOrder(students);
    }

    private void resetIds(Set<Student> students) {
        students.forEach(student -> student.setId(null));
    }

    private void thenStudentWithIdHasBeenFound(Long id, Student student) {
        URI uri = getUriBuilder().path("/{id}").buildAndExpand(id).toUri();
        ResponseEntity<Student> response = restTemplate.getForEntity(uri, Student.class);
        Assertions.assertThat(response.getBody()).isEqualTo(student);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private void thenStudentHasBeenCreated(ResponseEntity<Student> response) {
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getId()).isNotNull();
    }

    private UriComponentsBuilder getUriBuilder() {
        return UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(port)
                .path("/student");
    }

    private Student givenStudentWith(String name, int age) {
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        return student;
    }

    private ResponseEntity<Student> whenSendingCreateStudentRequest(URI uri, Student student) {
        return restTemplate.postForEntity(uri, student, Student.class);
    }


}
