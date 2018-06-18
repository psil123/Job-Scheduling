package Job;
public class Job {

	public String jobID;
	public int processingTime; //processing time of the job 
	public int dueDate;	//time when job is due, if completion time is past this time, the job is tardy
	public int setUpTime; //also called ready time, time when job is available, can't be scheduled before this time
	//public String parentJob;
	public int waitTime;
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
	
	public String getJobID() {
		return jobID;
	}
	
	
	public boolean isEqualTo(Job T) {
		
		Job j = T;
		if (j.getJobID()==this.jobID)
			return true;
		else
			return false;
	}
	
	public String toString(){
		String s;
		s = this.getJobID()+"\t"+this.processingTime+"\t"+this.dueDate+"\t"+this.setUpTime;
		return s;
	}
	
	public String getElementCode(){
		return new String("P"+jobID);
	}
	
}
