package Job;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


public class JobScheduler {
	
	public List<Job> generate(int n,double r,double t,int MAX_PI,int MIN_WI,int MAX_WI)
	{
		int P=0,C=0;
		List<Job> jobs=new ArrayList<Job>();
		for(int i=0;i<n;i++)
		{
			jobs.add(new Job(Integer.toString(i), (int)(1+(MAX_PI-1)*Math.random()), 0, 0));
			P+=jobs.get(i).processingTime;
		}
		for(int i=0;i<n;i++) 
		{
			C+=jobs.get(i).processingTime;
			jobs.get(i).dueDate=(int)(Math.ceil(P*(1-t-r/2))+(Math.ceil(P*(1-t+r/2))-Math.ceil(P*(1-t-r/2)))*Math.random());
			jobs.get(i).setUpTime=(int)(C/2*Math.random());
			jobs.get(i).waitTime=(int)(MIN_WI+(MAX_WI-MIN_WI)*Math.random());
		}
		return jobs;
	}
	int schedule(List<Job> jobs)
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
