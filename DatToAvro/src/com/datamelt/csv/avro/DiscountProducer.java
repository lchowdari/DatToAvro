package com.datamelt.csv.avro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DiscountProducer {
	public static void main(String[] args) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		System.out.println(df.format(new Date()) + " processing records started");

		String folder = "C:\\Users\\HANSINI_LUCKY\\Desktop\\AvroTest";
		String outputFile = folder + "/discount_test.avro";
		String schemaFilename = "/discount.avsc";

		File inputFile = new File(folder + "/discount_test.dat");
		FileReader fileReader = new FileReader(inputFile);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		long counter = 0;
		String line;
		File schemaFile = new File( folder + schemaFilename);
		String[] headerFields = { "id", "age", "destination_region", "destination_airport", "price", "frequent_traveller" };

		DatToAvroGenericWriter writer = new DatToAvroGenericWriter(schemaFile, outputFile, DatToAvroWriter.MODE_WRITE);
		//writer.setCsvHeader(headerFields);
		while ((line = bufferedReader.readLine()) != null) {
			counter++;

			writer.append(line);

			if (counter % 10000 == 0) {
				System.out.println(df.format(new Date()) + " processed records: " + counter);
			}
		}
		fileReader.close();
		writer.closeWriter();

		System.out.println(df.format(new Date()));
		System.out.println(df.format(new Date()) + " processing records complete");
		System.out.println(df.format(new Date()) + " total processed records: " + counter);
	}
}