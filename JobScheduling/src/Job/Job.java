package Job;

import java.util.StringTokenizer;

public class Job implements Cloneable{

	public String jobID;
	public int processingTime; //processing time of the job 
	public int dueDate;	//time when job is due, if completion time is past this time, the job is tardy


	public int setUpTime; //also called ready time, time when job is available, can't be scheduled before this time
	//public String parentJob;
	public int waitTime;
	public int weight=0;
	/**
	 * @param processingTime
	 * @param dueDate
	 * @param t 
	 * @param parent
	 */
	public Job( String jobID,  int processingTime, int dueDate,int waitTime) {
		this.jobID = jobID;
		this.processingTime = processingTime;
		this.dueDate = dueDate;
		this.waitTime=waitTime;
	}

	/**
	 * @param processingTime
	 * @param dueDate
	 * @param setUpTime
	 * @param parent
	 */
	public Job( String jobID, int processingTime, int dueDate, int setUpTime,int waitTime) {
		
		this.jobID = jobID;
		this.processingTime = processingTime;
		this.dueDate = dueDate;
		this.setUpTime = setUpTime;
		this.waitTime=waitTime;
	}
	public Job(String s)
	{
		StringTokenizer st=new StringTokenizer(s+",",",");
		this.jobID=st.nextToken();
		this.processingTime=Integer.parseInt(st.nextToken());
		this.dueDate=Integer.parseInt(st.nextToken());
		this.setUpTime=Integer.parseInt(st.nextToken());
		this.weight=Integer.parseInt(st.nextToken());
	}
	
	
	
	public boolean isEqualTo(Job T) {
		
		Job j = T;
		if (j.jobID==this.jobID)
			return true;
		else
			return false;
	}
	
	public String toString(){
		String s;
		s = this.jobID+","+this.processingTime+","+this.dueDate+","+this.setUpTime+","+this.weight;
		return s;
	}
	
	public String getElementCode(){
		return new String("P"+jobID);
	}
	
	@Override
	protected Job clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new Job(jobID, processingTime, dueDate, setUpTime, waitTime);
	}
	
}
