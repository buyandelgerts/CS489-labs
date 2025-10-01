package edu.miu.cs.cs489appsd.lab1.productmgmtapp.lab2B;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

public class PAMSApp {
    public static void main(String[] args) throws IOException {
        List<Patient> patients = new ArrayList<>();
        Patient patient1 = new Patient(1, "Daniel", "Agar", "(641)123-0009", "dagar@m.as", "1 N Street", LocalDate.of(1987,1,19));
        Patient patient2 = new Patient(2, "Ana", "Smith", null, "amsith@te.edu", null, LocalDate.of(1948,12,5));
        Patient patient3 = new Patient(3, "Marcus", "Garvey", "(123)292-0018", null, "4 East Ave", LocalDate.of(2001,9,18));
        Patient patient4 = new Patient(4, "Jeff", "Goldbloom", "(999)165-1192","jgold@es.co.za", null, LocalDate.of( 1995,2,28));
        Patient patient5 = new Patient(5, "Mary", "Washington", null, null, "30 W Burlington", LocalDate.of(1932,5,31));
        patients.add(patient1);
        patients.add(patient2);
        patients.add(patient3);
        patients.add(patient4);
        patients.add(patient5);
        saveToJson(patients);
    }

    public static void saveToJson(List<Patient> patients) throws IOException {
        Path outputPath = Paths.get("src/main/java/edu/miu/cs/cs489appsd/lab1/productmgmtapp/lab2B/data", "patients.json");
        Files.createDirectories(outputPath.getParent());
        try{
            List<Patient> sortedList = patients.stream().sorted(Comparator.comparing(Patient::getDateOfBirth)).toList();
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            mapper.writeValue(outputPath.toFile(), sortedList);
        }catch (Exception ex){
            System.err.println("Err: " + ex.getMessage());
        }
    }
}
