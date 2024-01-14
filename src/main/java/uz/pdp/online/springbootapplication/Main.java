package uz.pdp.online.springbootapplication;

import uz.pdp.online.springbootapplication.annotation.TransactionImpl;
import uz.pdp.online.springbootapplication.immutable.PersonInJsonFormatImpl;
import uz.pdp.online.springbootapplication.immutable.PersonInXmlFormatImpl;
import uz.pdp.online.springbootapplication.jsongenerator.JsonGeneratorImpl;
import uz.pdp.online.springbootapplication.jsongenerator.YamlGeneratorImpl;
import uz.pdp.online.springbootapplication.jsonnode.JsonNodeImpl;
import uz.pdp.online.springbootapplication.objectmapping.CarMapperImpl;

public class Main {
    public static void main(String[] args) throws Exception {

        TransactionImpl.transactionToJsonFile();
        TransactionImpl.jsonToTransactionObject();

        PersonInJsonFormatImpl.personToJson();
        PersonInJsonFormatImpl.jsonToPerson();

        PersonInXmlFormatImpl.personToXml();
        PersonInXmlFormatImpl.xmlToPerson();

        JsonGeneratorImpl.jsonManualGenerateTest();

        YamlGeneratorImpl.transactionObjectToYamlFile();
        YamlGeneratorImpl.yamlToTransactionObject();

        JsonNodeImpl.stringToJsonNode();

        CarMapperImpl.carToJson();
        CarMapperImpl.jsonToCar();

        CarMapperImpl.carArrayToJson();
        CarMapperImpl.carArrayToJson();
    }
}
