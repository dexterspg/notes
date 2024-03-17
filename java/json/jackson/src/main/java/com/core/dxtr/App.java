package com.core.dxtr;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {


        ObjectMapper mapper = new ObjectMapper();
        try{
        Student student = mapper.readValue(new File("data/sample.json"), Student.class); //serialization
        System.out.println(student);

        mapper.enable(SerializationFeature.INDENT_OUTPUT); //use to pretty print
        mapper.writeValue(new File("data/output.json"), new Student("Bob", "Andrew")); //deserialization

        }catch(Exception e){
            e.printStackTrace();
        }

    };

}
