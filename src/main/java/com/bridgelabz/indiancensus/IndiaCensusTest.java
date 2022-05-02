package com.bridgelabz.indiancensus;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IndiaCensusTest {

	private static final String INDIA_CENSUS_PATH_FILE = "D:/Eclipse_LFP_112/IndiaStateCensusAnalyser/src/main/resources/IndiaStateCensusData.csv";
	private static final String WRONG_CENSUS_PATH_FILE = "";
	private static final String TXT_CENSUS_PATH_FILE = "D:/Eclipse_LFP_112/IndiaStateCensusAnalyser/src/main/resources/IndiaStateCode.txt";

	
	//Given the States Census CSV file, Check to ensure the Number of Record matches.
	@Test
	public void givenIndiaCensusCSVFile_WhenLoaded_ShouldReturnCorrectRecords() {
		IndianCensusAnalyzer censusAnalyser = new IndianCensusAnalyzer();
		try {
			int numOfRecord = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_PATH_FILE);
			Assert.assertEquals(29, numOfRecord);
		} catch (CensusAnalyserException e) {

		}
	}

	 //Given the State Census CSV file incorrect, returns a custom exception.
	@Test
	public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
		try {
			IndianCensusAnalyzer censusAnalyser = new IndianCensusAnalyzer();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(WRONG_CENSUS_PATH_FILE);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}
	
	//Given the State Census CSV file with incorrect type, Should throw exception.
    @Test
    public void givenIndiaCensusCSVFile_WhenLoadedWithWrongType_ShouldThrowException() {
        try {
        	IndianCensusAnalyzer censusAnalyser = new IndianCensusAnalyzer();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(TXT_CENSUS_PATH_FILE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }
}
