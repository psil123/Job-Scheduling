package test;

import java.util.List;

import Data.DataGenerator;
import Job.*;

public class Test {


	@org.junit.Test
	public void testEval() throws Exception{
		DataGenerator ob=new DataGenerator();
		List<Job> jobs1=ob.generate(10, 0.8, 0.6, 100, 1, 10,true,null);
		String path=ob.writeToCSV(jobs1, "Data", true);
		List<Job> jobs=ob.readFromCSV(path);
		for(int i=0;i<jobs.size();i++) 
		{
			if(!jobs.get(i).toString().equals(jobs1.get(i).toString()))
				throw new Exception("CSV File Read Write Error");
		}
	}

}
