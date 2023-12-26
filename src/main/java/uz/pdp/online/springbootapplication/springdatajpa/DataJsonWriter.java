package uz.pdp.online.springbootapplication.springdatajpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import uz.pdp.online.springbootapplication.entity.Gender;
import uz.pdp.online.springbootapplication.entity.Group;
import uz.pdp.online.springbootapplication.entity.Student;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class DataJsonWriter {

    private static final String GROUPS_JSON_FILE_PATH = "data/groups.json";
    private static final String STUDENTS_JSON_FILE_PATH = "data/students.json";

    public static void writeToJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        File groupsFile = new File(GROUPS_JSON_FILE_PATH);
        Group[] groups = {
                new Group(1L, "Group 1"),
                new Group(2L, "Group 2"),
                new Group(3L, "Group 3")};

        mapper.writeValue(groupsFile, groups);


        File studentsFile = new File(STUDENTS_JSON_FILE_PATH);
        Student[] students = {
                new Student(1L, "Laziz", 20, new Date(2003 - 1900, Calendar.OCTOBER, 11), Gender.MEN, groups[0]),
                new Student(2L, "Fariza", 16, new Date(2002 - 1900, Calendar.MAY, 2), Gender.WOMEN, groups[1]),
                new Student(3L, "Sa'dulla", 33, new Date(2001 - 1900, Calendar.DECEMBER, 29), Gender.MEN, groups[2]),
                new Student(4L, "Surayyo", 25, new Date(2003 - 1900, Calendar.JULY, 24), Gender.WOMEN, groups[0]),
                new Student(5L, "Sohib", 13, new Date(2006 - 1900, Calendar.DECEMBER, 4), Gender.MEN, groups[0]),
                new Student(6L, "Bahora", 46, new Date(2005 - 1900, Calendar.MAY, 29), Gender.WOMEN, groups[1]),
                new Student(7L, "Valijon", 24, new Date(2008 - 1900, Calendar.JULY, 15), Gender.MEN, groups[2])};

        mapper.writeValue(studentsFile, students);
    }

    public static void main(String[] args) throws Exception {
        writeToJson();
    }
}
