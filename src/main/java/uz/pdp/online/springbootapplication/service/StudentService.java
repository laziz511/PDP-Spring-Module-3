package uz.pdp.online.springbootapplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.online.springbootapplication.entity.Gender;
import uz.pdp.online.springbootapplication.entity.Student;
import uz.pdp.online.springbootapplication.exception.StudentNotFoundException;
import uz.pdp.online.springbootapplication.repository.StudentRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with the id: " + id));
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        return studentRepository.findById(id).map(existingStudent -> {
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setAge(updatedStudent.getAge());
            existingStudent.setBirthDate(updatedStudent.getBirthDate());
            existingStudent.setGender(updatedStudent.getGender());
            return studentRepository.save(existingStudent);
        }).orElseThrow(() -> new StudentNotFoundException("Student not found with the id: " + id));
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }


    public List<Student> findByBirthDateBetween(Integer from, Integer to) {
        Date fromDate = new Date(from, Calendar.JANUARY, 1);
        Date toDate = new Date(to, Calendar.DECEMBER, 31);
        return studentRepository.findByBirthDateBetweenCustom(fromDate, toDate);
    }

    public List<Student> findByGender(Gender gender) {
        return studentRepository.findByGenderNamedQuery(gender);
    }

    public List<Student> findByGenderNative(Gender gender) {
        return studentRepository.findByGenderNamedNativeQuery(gender);
    }

    public Page<Student> getAllStudentsPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.getAllStudentPaged(pageable);
    }

    public List<Student> getStudentsByGroupId(Long groupId) {
        return studentRepository.findByGroupId(groupId);
    }

}
