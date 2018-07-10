package Job;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import Data.DataGenerator;
import edu.ohiou.mfgresearch.lambda.Anything;


public class JobScheduler {
	
	public DataGenerator dg;
	private int fjobs[];
	private Long timeTaken = 0L;
	public static int dim = 0;
	
	public JobScheduler(DataGenerator dg)
	{
		this.dg=dg;
	}
	
	public Integer[] schedule() throws CloneNotSupportedException
	{
		timeTaken = System.currentTimeMillis();
		List<Job> jobs = new LinkedList<Job>();
		for(Job j:dg.getJoblist()){
			jobs.add(j.clone());
		}
		dim = 0;
		PriorityQueue<Job> pq = new PriorityQueue<Job>(jobs.size(), new JobComparator());
//		double dim = 0;
//		for(Job i:jobs)
//			dim+=i.processingTime;
		fjobs = new int [jobs.size()];
//		int schedule[]=new int [(int) dim];
		List<Integer> schedule = new ArrayList<Integer>();
		for(Job j:jobs)
			if(j.setUpTime==0) pq.add(j);
		while(!pq.isEmpty())
		{		
			dim++;
			Job popped=pq.remove();
			schedule.add(Integer.parseInt(popped.jobID));
			System.out.println("Job " + popped.jobID + " scheduled at " + (dim-1));
			popped.processingTime--;
			if(popped.processingTime>0)
				pq.add(popped);
			else
				fjobs[Integer.parseInt(popped.jobID)]=dim-1;
			for(Job j:jobs)
				if(!pq.contains(j) && fjobs[Integer.parseInt(j.jobID)]==0){
					if(dim >= j.setUpTime){
						pq.add(j);
					}
				}
		}
		timeTaken = (System.currentTimeMillis() - timeTaken) / 1000;
		return schedule.stream().toArray(Integer[]::new);
	}
	
	public int getTotalTardiness(Integer[] schedule){
		int totalTardiness=0;
		for(int i=0;i<dg.getJoblist().size();i++)
			totalTardiness+=Math.max(0, fjobs[i]-dg.getJoblist().get(i).dueDate)*dg.getJoblist().get(i).weight;
		return totalTardiness;
	}
	
	public void writeSchedule(Integer[] schedule){
		int dim = schedule.length;
		for(Job j:dg.getJoblist()){
			System.out.print("Job#"+j.jobID+">");
			for(int i=0;i<dim;i++){
				if(schedule[i]==Integer.parseInt(j.jobID)){
					System.out.print("1");
				}
				else{
					System.out.print("0");
				}
			}
			System.out.print("\n");
		}
	}

	public int getCPUTime() {
		// TODO Auto-generated method stub
		return timeTaken.intValue();
	}
	
	
	

}
