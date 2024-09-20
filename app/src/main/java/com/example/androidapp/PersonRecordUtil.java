package com.example.androidapp;

import org.apache.commons.csv.CSVRecord;

public class PersonRecordUtil {
    public static PersonRecord parsePersonRecord(CSVRecord record) {
        try {
            Integer age = Integer.parseInt(record.get("Age"));
            Integer sex = Integer.parseInt(record.get("Sex"));
            Integer bp = Integer.parseInt(record.get("BP"));
            Integer cholesterol = Integer.parseInt(record.get("Cholesterol"));
            Integer na = Integer.parseInt(record.get("Na"));
            Integer k = Integer.parseInt(record.get("K"));
            String drug = record.get("Drug");
            return new PersonRecord(age, sex, bp, cholesterol, na, k, drug);
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            return null;
        }
    }
}
