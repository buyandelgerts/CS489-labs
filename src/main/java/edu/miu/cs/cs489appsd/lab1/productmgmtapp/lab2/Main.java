package edu.miu.cs.cs489appsd.lab1.productmgmtapp.lab2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Main {

    public static void main(String[] args) {

        EmployeeService service = new EmployeeService();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try{
            System.out.println("--- All Employee Report ---");
            String allEmployeesJson = mapper.writeValueAsString(service.getAllEmployeesSorted());
            System.out.println(allEmployeesJson);

            System.out.println("--- Upcoming Enrollees Report ---");
            String enrolleesJson = mapper.writeValueAsString(service.getUpcomingEnrollees());
            System.out.println(enrolleesJson);
        }
        catch (JsonProcessingException e) {
            System.err.println("Ex: " + e.getMessage());
        }

    }
}
