package com.example.androidapp;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {
	public static List<CSVRecord> parseCSV(InputStream filename) {
		List<CSVRecord> records = new ArrayList<>();
		try {
			Reader reader = new InputStreamReader(filename);
			Iterable<CSVRecord> recordsIterable = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
			for (CSVRecord record : recordsIterable) {
				records.add(record);
			}
			reader.close();
		} catch (Exception e) {
			System.out.println("Exeception : " + e.getMessage());
		}
		return records;
	}
}
