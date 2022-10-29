package uk.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.hogwarts.school.model.Faculty;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static uk.hogwarts.school.service.ConstantTestValues.*;

class FacultyServiceTest {

    private FacultyService facultyService;


    @BeforeEach
    public void beforeEach() {
        facultyService = new FacultyService();
    }

    @Test
    void createFacultyPositiveTest() {
        Faculty expected = facultyService.createFaculty(faculty1);
        assertEquals(expected, faculty1);
    }

    @Test
    void createFacultyPositiveTest2() {
        facultyService.createFaculty(faculty1);
        facultyService.createFaculty(faculty2);
        facultyService.createFaculty(faculty3);
        facultyService.createFaculty(faculty4);
        facultyService.createFaculty(faculty5);
        assertThat(facultyService.getAll().toArray())
                .containsExactly(faculty1, faculty2, faculty3, faculty4, faculty5);
    }

    @Test
    void createFacultyNegativeTest() {
        facultyService.createFaculty(faculty1);
        assertNull(facultyService.createFaculty(faculty1));
    }

    @Test
    void getFacultyPositiveTest() {
        facultyService.createFaculty(faculty1);
        facultyService.createFaculty(faculty2);
        facultyService.createFaculty(faculty3);
        assertEquals(facultyService.getFaculty(2L), faculty2);
    }

    @Test
    void getFacultyNegativeTest() {
        facultyService.createFaculty(faculty1);
        facultyService.createFaculty(faculty2);
        facultyService.createFaculty(faculty3);
        assertNull(facultyService.getFaculty(6L));
    }

    @Test
    void updateFacultyPositiveTest() {
        facultyService.createFaculty(faculty1);
        assertEquals(facultyService.updateFaculty(1L, faculty3), faculty3);
    }

    @Test
    void updateFacultyNegativeTest() {
        facultyService.createFaculty(faculty1);
        assertNull(facultyService.updateFaculty(2L, faculty3));
    }

    @Test
    void deleteFacultyPositiveTest() {
        facultyService.createFaculty(faculty1);
        facultyService.createFaculty(faculty2);
        facultyService.createFaculty(faculty3);
        facultyService.deleteFaculty(2L);
        assertThat(facultyService.getAll().toArray())
                .containsExactly(faculty1, faculty3);
    }

    @Test
    void deleteFacultyNegativeTest() {
        facultyService.createFaculty(faculty1);
        facultyService.createFaculty(faculty2);
        facultyService.createFaculty(faculty3);
        assertNull(facultyService.deleteFaculty(4L));
    }

    @Test
    void sortFacultiesByColorPositiveTest() {
        facultyService.createFaculty(faculty1);
        facultyService.createFaculty(faculty2);
        facultyService.createFaculty(faculty3);
        facultyService.createFaculty(faculty4);
        facultyService.createFaculty(faculty5);
        assertThat(facultyService.sortFacultiesByColor("red").toArray())
                .containsExactly(faculty1, faculty5);
    }


    @Test
    void sortFacultiesByColorNegativeTest() {
        facultyService.createFaculty(faculty1);
        facultyService.createFaculty(faculty2);
        facultyService.createFaculty(faculty3);
        facultyService.createFaculty(faculty4);
        facultyService.createFaculty(faculty5);
        assertNull(facultyService.sortFacultiesByColor("gray"));
    }
}