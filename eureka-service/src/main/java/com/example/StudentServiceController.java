package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.Student.newStudent;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@RestController
@RequestMapping("{schoolName}/students")
public class StudentServiceController {

    private static Map<String, List<Student>> SCHOOL_DB = new HashMap<String, List<Student>>() {{
        put(
                "abc-school",
                asList(
                        newStudent("Sajal", "Class IV"),
                        newStudent("Lokesh", "Class V")
                )
        );

        put(
                "xsz-school",
                asList(
                        newStudent("Kajal", "Class III"),
                        newStudent("Sukesh", "Class VI")
                )
        );
    }};

    @GetMapping
    public List<Student> getStudents(@PathVariable String schoolName) {
        return SCHOOL_DB.getOrDefault(
                schoolName,
                singletonList(
                        newStudent("Not Found", "N/A")
                )
        );
    }

}

class Student {
    private final String name;
    private final String className;

    Student(String name, String className) {
        this.name = name;
        this.className = className;
    }

    static Student newStudent(String name, String className) {
        return new Student(name, className);
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }
}