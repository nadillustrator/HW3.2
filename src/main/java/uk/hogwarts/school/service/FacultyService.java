package uk.hogwarts.school.service;


import org.springframework.stereotype.Service;
import uk.hogwarts.school.model.Faculty;
import uk.hogwarts.school.repositories.FacultyRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }


    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();
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
