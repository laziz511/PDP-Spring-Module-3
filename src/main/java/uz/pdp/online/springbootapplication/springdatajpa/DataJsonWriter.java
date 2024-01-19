package uz.pdp.online.springbootapplication.springdatajpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class DataJsonWriter {

    private static final String GROUPS_JSON_FILE_PATH = "data/groups.json";
    private static final String STUDENTS_JSON_FILE_PATH = "data/students.json";

    public static void writeToJson() {
        ObjectMapper mapper = new ObjectMapper();

        writeGroupsToJson(mapper);
        writeStudentsToJson(mapper);
    }

    private static void writeGroupsToJson(ObjectMapper mapper) {
        File groupsFile = new File(GROUPS_JSON_FILE_PATH);
        GroupInit[] groups = {
                new GroupInit(1L, "Group 1"),
                new GroupInit(2L, "Group 2"),
                new GroupInit(3L, "Group 3")};

        writeToFile(mapper, groupsFile, groups);
    }

    private static void writeStudentsToJson(ObjectMapper mapper) {
        File studentsFile = new File(STUDENTS_JSON_FILE_PATH);
        StudentInit[] students = {
                new StudentInit(1L, "Laziz", 20, createDate(2003, Calendar.OCTOBER, 11), Gender.MEN),
                new StudentInit(2L, "Fariza", 16, createDate(2002, Calendar.MAY, 2), Gender.WOMEN),
                new StudentInit(3L, "Sa'dulla", 33, createDate(2001, Calendar.DECEMBER, 29), Gender.MEN),
                new StudentInit(4L, "Surayyo", 25, createDate(2003, Calendar.JULY, 24), Gender.WOMEN),
                new StudentInit(5L, "Sohib", 13, createDate(2006, Calendar.DECEMBER, 4), Gender.MEN),
                new StudentInit(6L, "Bahora", 46, createDate(2005, Calendar.MAY, 29), Gender.WOMEN),
                new StudentInit(7L, "Valijon", 24, createDate(2008, Calendar.JULY, 15), Gender.MEN)};

        writeToFile(mapper, studentsFile, students);
    }

    private static void writeToFile(ObjectMapper mapper, File file, Object[] data) {
        try {
            mapper.writeValue(file, data);
        } catch (IOException e) {
            log.warn("Exception occurred while saving data to json: " + e.getMessage());
        }
    }

    private static Date createDate(int year, int month, int day) {
        return new Date(year - 1900, month, day);
    }

    public static void main(String[] args) {
        writeToJson();
    }
}
