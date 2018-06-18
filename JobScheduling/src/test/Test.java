package test;

import java.util.List;

import Job.*;

public class Test {


	@org.junit.Test
	public void testEval(){
		JobScheduler ob=new JobScheduler();
		List<Job> jobs=ob.generate(0, 0, 0, 0, 0, 0);
	}

}
