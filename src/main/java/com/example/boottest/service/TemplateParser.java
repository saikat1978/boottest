package com.example.boottest.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

/**
 * TemplateParser
 */
@Service
public class TemplateParser {

    public void parse(File file) {
        try {
            CSVParser parser = CSVFormat.DEFAULT.parse(new InputStreamReader(new FileInputStream(file)));
            for (CSVRecord record : parser) {
                record.forEach(r -> {
                    System.out.println(r);
                });
                System.out.println("------------------------------------");
            }
        } catch (IOException e) {
           
            e.printStackTrace();
        }
    }
}