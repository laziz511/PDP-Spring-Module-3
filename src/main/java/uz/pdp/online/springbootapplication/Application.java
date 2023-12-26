package uz.pdp.online.springbootapplication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uz.pdp.online.springbootapplication.entity.Group;
import uz.pdp.online.springbootapplication.entity.Student;
import uz.pdp.online.springbootapplication.repository.GroupRepository;
import uz.pdp.online.springbootapplication.repository.StudentRepository;

import java.io.File;
import java.util.List;

@SpringBootApplication
public class Application {

    private static final String GROUPS_JSON_FILE_PATH = "data/groups.json";
    private static final String STUDENTS_JSON_FILE_PATH = "data/students.json";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ApplicationRunner importGroups(GroupRepository groupRepository, ObjectMapper objectMapper) {
        return args -> {
            File groupsFile = new File(GROUPS_JSON_FILE_PATH);
            List<Group> groups = objectMapper.readValue(groupsFile, new TypeReference<List<Group>>() {});
            groupRepository.saveAll(groups);
        };
    }

    @Bean
    ApplicationRunner importStudents(StudentRepository studentRepository, ObjectMapper objectMapper) {
        return args -> {
            File studentsFile = new File(STUDENTS_JSON_FILE_PATH);
            List<Student> students = objectMapper.readValue(studentsFile, new TypeReference<List<Student>>() {});
            studentRepository.saveAll(students);
        };
    }
}
