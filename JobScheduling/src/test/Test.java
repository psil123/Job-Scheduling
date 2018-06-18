package test;

import java.util.List;

import Job.*;

public class Test {


	@org.junit.Test
	public void testEval(){
		JobScheduler ob=new JobScheduler();
		List<Job> jobs=ob.generate(10, 0.8, 0.6, 100, 1, 10);
		System.out.print(ob.schedule(jobs));
	}

}
