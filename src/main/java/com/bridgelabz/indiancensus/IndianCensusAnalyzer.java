package com.bridgelabz.indiancensus;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class IndianCensusAnalyzer {

	String path = "D:/Eclipse_LFP_112/IndiaStateCensusAnalyser/src/main/resources/IndiaStateCensusData.csv";
	public int loadIndiaCensusData(String path) throws CensusAnalyserException {

		try {
			Reader reader = Files.newBufferedReader(Paths.get(path));
			CsvToBeanBuilder<IndiaCensusCSV> csvCsvToBeanBuilder = new CsvToBeanBuilder<IndiaCensusCSV>(reader);
			csvCsvToBeanBuilder.withType(IndiaCensusCSV.class);
			csvCsvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<IndiaCensusCSV> csvToBean = csvCsvToBeanBuilder.build();
			Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();
			int numOfEntries = 0;
			while (censusCSVIterator.hasNext()) {
				numOfEntries++;
				censusCSVIterator.next();
			}
			return numOfEntries;
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	public static void main(String[] args) {
		System.out.println("******************Indian Census Analyzer******************");
		
		try {
			IndianCensusAnalyzer census = new IndianCensusAnalyzer();
			census.loadIndiaCensusData();
		} catch (CensusAnalyserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
