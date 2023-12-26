package uz.pdp.online.springbootapplication.entity;

import lombok.*;

import jakarta.persistence.*;
import uz.pdp.online.springbootapplication.listener.EntityModificationListener;

import java.util.List;

@Entity
@Table(name = "GROUPS")
@EntityListeners(EntityModificationListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "GROUP_NAME", length = 50, nullable = false)
    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Student> students;

}
