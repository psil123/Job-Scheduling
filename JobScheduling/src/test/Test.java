package test;

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
	}
}
