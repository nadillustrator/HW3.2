package uk.hogwarts.school.model;

import java.util.Objects;

public class Student {
    private Long id;
    private String name;
    private int age;
    private Faculty faculty;

    private static Long generateStudentId = 1L;

    public Student(String name, int age, Faculty faculty) {
        this.id = generateStudentId++;
        this.name = name;
        this.age = age;
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "Student:" + name +
                ", id: " + id +
                ", age: " + age +
                " years old, faculty: "
                + faculty.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return age == student.age && id.equals(student.id) && name.equals(student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
