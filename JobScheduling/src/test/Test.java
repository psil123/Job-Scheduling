package test;

import Data.DataGenerator;
import Job.JobScheduler;
import OPL.oplrun.OPL;
import OPL.oplrun.OPLDataModel;

public class Test {


	private String modelPath = "C:/Users/sarkara1/git/opl/minTT1pmtn/Bouma_BLP1.mod";
	private String filepath = "";

	@org.junit.Test
	public void testDataGen() throws Exception{
		DataGenerator dg=new DataGenerator(filepath);
		dg.generateData(10, 0.8, 0.6, 100, 1, 10);  //n, r, t, MAX_PI, MIN_WI, MAX_WI
		//JobScheduler wsrpt = new JobScheduler(ob);
		OPL opl = new OPL(modelPath, dg);		
		//System.out.println( "Total tardiness achieved by heuristic = " + wsrpt.schedule());
		opl.startEngine();
		opl.solve();
		opl.printResult();
		opl.closeOPL();
	}
}
