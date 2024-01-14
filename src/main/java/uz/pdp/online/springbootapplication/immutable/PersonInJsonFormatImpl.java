package uz.pdp.online.springbootapplication.immutable;

import java.io.File;
import java.io.IOException;

public class PersonInJsonFormatImpl {

    public static void personToJson() throws IOException {
        File file = new File("data/person.json");
        Person person = new Person("Laziz", "Djuraev", 20);

        person.writeToJSONFile(file.getPath());
    }

    public static void jsonToPerson() throws IOException {
        File file = new File("data/person.json");
        Person person = Person.readFromJSONFile(file.getPath());

        System.out.println(person);
    }
}
