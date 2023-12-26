package uz.pdp.online.springbootapplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.springbootapplication.entity.Gender;
import uz.pdp.online.springbootapplication.entity.Student;
import uz.pdp.online.springbootapplication.service.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{from}/{to}")
    public List<Student> getAllStudentsByBirthDaysBetween(@PathVariable Integer from, @PathVariable Integer to) {
        return studentService.findByBirthDateBetween(from, to);
    }

    @GetMapping("/gender/{gender}")
    public List<Student> getAllStudentByGender(@PathVariable Gender gender) {
        return studentService.findByGender(gender);
    }

    @GetMapping("/gender/native/{gender}")
    public List<Student> getAllStudentByGenderNative(@PathVariable Gender gender) {
        return studentService.findByGenderNative(gender);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
