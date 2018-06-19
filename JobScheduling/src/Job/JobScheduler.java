package Job;
import java.util.List;
import java.util.PriorityQueue;

import Data.DataGenerator;


public class JobScheduler {
	
	public DataGenerator dg;
	public JobScheduler(DataGenerator dg)
	{
		this.dg=dg;
	}
	
	public int schedule(List<Job> jobs)
	{
		PriorityQueue<Job> pq = new PriorityQueue<Job>(jobs.size(), new JobComparator());
		double dim = 0;
		for(Job i:jobs)
			dim+=i.processingTime;
		int fjobs[]=new int [jobs.size()];
		int schedule[]=new int [(int) dim];
		for(Job i:jobs)
			pq.add(i);
		for(int i=1;i<=dim;i++)
		{
			Job popped=pq.remove();
			schedule[i-1]=Integer.parseInt(popped.jobID);
			popped.processingTime--;
			if(popped.processingTime>0)
				pq.add(popped);
			else
				fjobs[Integer.parseInt(popped.jobID)]=i;
		}
		int totalTardiness=0;
		for(int i=0;i<jobs.size();i++)
			totalTardiness+=Math.max(0, jobs.get(i).dueDate-fjobs[i]);
		return totalTardiness;
	}
	

}
