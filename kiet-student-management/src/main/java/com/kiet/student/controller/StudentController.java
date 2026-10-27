package com.kiet.student.controller;

import com.kiet.student.model.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/students")
public class StudentController {

    private final List<Student> students = new ArrayList<>();


    @PostConstruct
    public void initStudentData() {
        students.add(new Student(101, "Rahul Sharma", "rahul@kiet.edu", "CSE(AI & ML)", 2, 8.65));
        students.add(new Student(102, "Priya Singh", "priya@kiet.edu", "CSE", 2, 9.10));
        students.add(new Student(103, "Aman Verma", "aman@kiet.edu", "IT", 3, 7.80));
        students.add(new Student(104, "Sneha Patel", "sneha@kiet.edu", "CSE", 2, 8.90));
        students.add(new Student(105, "Vikas Gupta", "vikas@kiet.edu", "ECE", 4, 9.25));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(students);
    }


    @GetMapping("/{studentId:[0-9]+}")
    public ResponseEntity<?> getStudentById(@PathVariable int studentId) {
        for (Student s : students) {
            if (s.getStudentId() == studentId) {
                return ResponseEntity.ok(s);
            }
        }
        Map<String, String> response = new HashMap<>();
        response.put("message", "Student not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }


    @PutMapping("/{studentId:[0-9]+}")
    public ResponseEntity<?> updateStudent(@PathVariable int studentId, @RequestBody Student updatedStudent) {
        for (Student s : students) {
            if (s.getStudentId() == studentId) {
                s.setName(updatedStudent.getName());
                s.setEmail(updatedStudent.getEmail());
                s.setBranch(updatedStudent.getBranch());
                s.setYear(updatedStudent.getYear());
                s.setCgpa(updatedStudent.getCgpa());
                return ResponseEntity.ok(s);
            }
        }
        Map<String, String> response = new HashMap<>();
        response.put("message", "Student not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


    @DeleteMapping("/{studentId:[0-9]+}")
    public ResponseEntity<?> deleteStudent(@PathVariable int studentId) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId() == studentId) {
                students.remove(i);
                Map<String, String> response = new HashMap<>();
                response.put("message", "Student deleted successfully");
                return ResponseEntity.ok(response);
            }
        }
        Map<String, String> response = new HashMap<>();
        response.put("message", "Student not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchByBranchAndYear(
            @RequestParam String branch,
            @RequestParam int year) {
        List<Student> matchedStudents = students.stream()
                .filter(s -> s.getYear() == year &&
                        (s.getBranch().equalsIgnoreCase(branch) || s.getBranch().equalsIgnoreCase(branch.trim())))
                .collect(Collectors.toList());
        return ResponseEntity.ok(matchedStudents);
    }

    @GetMapping("/topper")
    public ResponseEntity<List<Student>> getToppersByCgpa(@RequestParam double cgpa) {
        List<Student> toppers = students.stream()
                .filter(s -> s.getCgpa() >= cgpa)
                .collect(Collectors.toList());
        return ResponseEntity.ok(toppers);
    }
}
