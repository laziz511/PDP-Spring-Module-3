package uz.pdp.online.springbootapplication.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.online.springbootapplication.entity.Gender;
import uz.pdp.online.springbootapplication.entity.Student;

import java.util.Date;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.birthDate BETWEEN :from AND :to")
    List<Student> findByBirthDateBetweenCustom(Date from, Date to);

    @Query(name = "Student.findByGender")
    List<Student> findByGenderNamedQuery(Gender gender);

    @Query(nativeQuery = true, name = "Student.findByGenderNative")
    List<Student> findByGenderNamedNativeQuery(Gender gender);

    @Query("SELECT s FROM Student s")
    Page<Student> getAllStudentPaged(Pageable pageable);

    @Query("SELECT s FROM Student s WHERE s.group.id = :groupId")
    List<Student> findByGroupId(Long groupId);
}
