package uz.pdp.online.springbootapplication.immutable;


import java.io.File;
import java.io.IOException;

public class PersonInXmlFormatImpl {

    public static void personToXml() throws IOException {
        File file = new File("data/person.xml");
        Person person = new Person("Laziz", "Djuraev", 20);

        person.writeToXMLFile(file.getPath());
    }


    public static void xmlToPerson() throws IOException {
        File file = new File("data/person.xml");
        Person person = Person.readFromXMLFile(file.getPath());

        System.out.println(person);
    }
}
