package uz.pdp.online.springbootapplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
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
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/birthDate")
    public ResponseEntity<List<Student>> getStudentsByBirthDateRange(
            @RequestParam Integer from,
            @RequestParam Integer to) {
        List<Student> students = studentService.findByBirthDateBetween(from, to);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<Student>> getStudentsByGender(@PathVariable Gender gender) {
        List<Student> students = studentService.findByGender(gender);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/gender/native/{gender}")
    public ResponseEntity<List<Student>> getStudentsByGenderNative(@PathVariable Gender gender) {
        List<Student> students = studentService.findByGenderNative(gender);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<Student>> getAllStudentsPaged(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<Student> students = studentService.getAllStudentsPaged(page, size);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/byGroup/{groupId}")
    public ResponseEntity<List<Student>> getStudentsByGroupId(@PathVariable Long groupId) {
        List<Student> students = studentService.getStudentsByGroupId(groupId);
        return ResponseEntity.ok(students);
    }
}
