package uk.hogwarts.school.service;

import org.springframework.stereotype.Service;
import uk.hogwarts.school.model.Faculty;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FacultyService {
    private final Map<Long, Faculty> faculties = new HashMap<>();

    public Faculty createFaculty(Faculty faculty) {
        if (faculties.containsValue(faculty)) {
            return null;
        }
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty getFaculty(Long id) {
        if (faculties.containsKey(id)) {
            return faculties.get(id);
        }
        return null;
    }


    public Faculty updateFaculty(Long id, Faculty faculty) {
        if (faculties.containsKey(id)) {
            faculties.put(id, faculty);
            return faculty;
        }
        return null;
    }

    public Faculty deleteFaculty(Long id) {
        if (faculties.containsKey(id)) {
            Faculty faculty = faculties.get(id);
            faculties.remove(id);
            return faculty;
        }
        return null;
    }

    public Collection<Faculty> getAll() {
        return new ArrayList<>(faculties.values());
    }

    public Collection<Faculty> sortFacultiesByColor(String color) {
        List<Faculty> sortedFaculties = getAll().stream()
                .filter(faculty -> color.equals(faculty.getColor()))
                .collect(Collectors.toList());
        if (sortedFaculties.isEmpty()) {
            return null;
        }
        return sortedFaculties;
    }
}
