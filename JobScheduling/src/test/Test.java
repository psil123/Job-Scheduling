package test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Data.DataGenerator;
import Job.Job;
import Job.JobScheduler;
import OPL.oplrun.OPL;
import OPL.oplrun.OPLDataModel;

public class Test {


	private String modelPath = "C:/Users/sarkara1/git/opl/minTT1pmtn/P2.mod";

	@org.junit.Test
	public void testDataGen1() throws Exception{

		for(int i=0;i<1;i++){
			DataGenerator dg=new DataGenerator();
			System.out.println("Test case # ------------------------>" + i);
			dg.generateData(5, 0.4, 0.3, 5, 1, 10);
			String csvfile = dg.writeToCSV("");
			dg.writeToDat("");
			dg.readFromCSV(csvfile);
			System.out.println("J,p,d,r,w");
			for(Job j:dg.getJoblist()){
				System.out.println(j.toString());
			}
		}	
		
	}
	
	@org.junit.Test
	public void testDataGen2() throws Exception{
		DataGenerator dg=new DataGenerator();
		for(int i=0;i<1;i++){
			dg.generateData(10, 0.8, 0.6, 10, 1, 10);
			String csvfile = dg.writeToCSV("");
			dg.readFromCSV(csvfile);
			for(Job j:dg.getJoblist()){
				System.out.println(j.toString());
			}
		}		
	}
	
	@org.junit.Test
	public void testSolutionComparison() throws Exception{
		DataGenerator dg=new DataGenerator();
		dg.generateData(10, 0.8, 0.6, 10, 1, 10);  //n, r, t, MAX_PI, MIN_WI, MAX_WI
		JobScheduler wsrpt = new JobScheduler(dg);
		OPL opl = new OPL(modelPath, dg);			
		Integer[] schedule = wsrpt.schedule();
		System.out.println( "Total tardiness achieved by heuristic = " + wsrpt.getTotalTardiness(schedule));
		wsrpt.writeSchedule(schedule);
		opl.startEngine();
		opl.solve();
		opl.printResult();
		opl.writeSchedule(opl.getMatrix2DParam("X"));
		opl.closeOPL();
	}
	
	/**
	 * Unit Case
	 * j5-r4-t3-5-1-10_b 
	 * @throws Exception
	 */
	@org.junit.Test
	public void testHeuristic1() throws Exception{
		DataGenerator dg=new DataGenerator();
		dg.readFromCSV("C:\\Users\\sarkara1\\git\\Job-Scheduling\\JobScheduling\\data\\j5-r4-t3-5-1-10_b.csv");
		JobScheduler wsrpt = new JobScheduler(dg);	
		Integer[] schedule = wsrpt.schedule();
		System.out.println( "Total tardiness achieved by heuristic = " + wsrpt.getTotalTardiness(schedule));
		wsrpt.writeSchedule(schedule);
		OPL opl = new OPL(modelPath, dg);	
		opl.startEngine();
		opl.solve();
		opl.printResult();
		opl.writeSchedule(opl.getMatrix2DParam("X"));
		opl.closeOPL();
	}
	
	/**
	 * Unit Case
	 * j5-r8-t6-5-1-10_b
	 * @throws Exception
	 */
	@org.junit.Test
	public void testHeuristic2() throws Exception{
		DataGenerator dg=new DataGenerator();
		dg.readFromCSV("C:\\Users\\sarkara1\\git\\Job-Scheduling\\JobScheduling\\data\\j5-r8-t6-5-1-10_b.csv");
		JobScheduler wsrpt = new JobScheduler(dg);	
		Integer[] schedule = wsrpt.schedule();
		System.out.println( "Total tardiness achieved by heuristic = " + wsrpt.getTotalTardiness(schedule));
		wsrpt.writeSchedule(schedule);
		OPL opl = new OPL(modelPath, dg);	
		opl.startEngine();
		opl.solve();
		opl.printResult();
		opl.writeSchedule(opl.getMatrix2DParam("X"));
		opl.closeOPL();
	}
	
	/**
	 * Unit Case
	 * j5-r8-t6-5-1-10_c
	 * @throws Exception
	 */
	@org.junit.Test
	public void testHeuristic3() throws Exception{
		DataGenerator dg=new DataGenerator();
		dg.readFromCSV("C:\\Users\\sarkara1\\git\\Job-Scheduling\\JobScheduling\\data\\j5-r8-t6-5-1-10_c.csv");
		JobScheduler wsrpt = new JobScheduler(dg);	
		Integer[] schedule = wsrpt.schedule();
		System.out.println( "Total tardiness achieved by heuristic = " + wsrpt.getTotalTardiness(schedule));
		wsrpt.writeSchedule(schedule);
		OPL opl = new OPL(modelPath, dg);	
		opl.startEngine();
		opl.solve();
		opl.printResult();
		opl.writeSchedule(opl.getMatrix2DParam("X"));
		opl.closeOPL();
	}
	
	
	/**
	 * Unit Case
	 * j5-r8-t6-5-1-10_c
	 * @throws Exception
	 */
	@org.junit.Test
	public void testHeuristic4Comp() throws Exception{
		
		runTest1(10, 0.3, 0.6);
		
	}
	
	
	@org.junit.Test
	public void testHeuristic5Comp() throws Exception{
		
		for(int i=0; i< 5; i++) runTest1(5, 0.2, 0.3);
//		for(int i=0; i< 5; i++) runTest1(5, 0.3, 0.6);
//		for(int i=0; i< 5; i++) runTest1(5, 0.6, 0.8);
//		for(int i=0; i< 5; i++) runTest1(10, 0.2, 0.3);
//		for(int i=0; i< 5; i++) runTest1(10, 0.3, 0.6);
//		for(int i=0; i< 5; i++) runTest1(10, 0.6, 0.8);
		
	}
	
	public void runTest1(int n, double r, double t) throws Exception{
		String filePath = "C:\\Users\\sarkara1\\git\\Job-Scheduling\\JobScheduling\\data\\result.csv";
		File file =new File(filePath);
		FileWriter rw=new FileWriter(file, true);
		String res = "";
		DataGenerator dg=new DataGenerator();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddHHmmss");
		LocalDateTime now = LocalDateTime.now();
		res = dtf.format(now) + ",";
		dg.generateData(n, r, t, 10, 1, 10);
		//dg.readFromCSV("C:\\Users\\sarkara1\\git\\Job-Scheduling\\JobScheduling\\data\\j5-r4-t3-5-1-10_b.csv");
		JobScheduler wsrpt = new JobScheduler(dg);	
		Integer[] schedule = wsrpt.schedule();
		int tt = wsrpt.getTotalTardiness(schedule);
		System.out.println( "Total tardiness achieved by heuristic = " + tt);
		res += String.valueOf(tt)+",";
		res += String.valueOf(wsrpt.getCPUTime()) +",";
		wsrpt.writeSchedule(schedule);
		OPL opl = new OPL(modelPath, dg);	
		opl.startEngine();
		opl.solve();
		opl.printResult();
		opl.writeSchedule(opl.getMatrix2DParam("X"));
		int tto = opl.getObjectiveValue();
		res += String.valueOf(tto)+",";
		res +=String.valueOf(opl.getTimeTaken())+",";
		res += String.valueOf((tt-tto)*100/tto)+",";
		res += dg.outputFileName+"\n";
		rw.write(res);
		rw.flush();
		rw.close();
		opl.closeOPL();
	}
}
