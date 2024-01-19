package uz.pdp.online.springbootapplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import uz.pdp.online.springbootapplication.listener.EntityModificationListener;

import java.util.Date;

@Entity
@Table(name = "STUDENTS")
@EntityListeners(EntityModificationListener.class)
@NamedQuery(
        name = "Student.findByGender",
        query = "SELECT s FROM Student s WHERE s.gender = :gender")
@NamedNativeQuery(
        name = "Student.findByGenderNative",
        query = "SELECT * FROM students WHERE gender = CAST(:gender AS VARCHAR)",
        resultClass = Student.class
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "STUDENT_NAME", length = 50, nullable = false, unique = false)
    private String name;

    @Transient
    @JsonIgnore
    private Integer age;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID", nullable = false)
    private Group group;
}
