package Job;
import java.util.*;
public class JobComparator implements Comparator<Job>
{

	public int compare(Job j1, Job j2) {
		
		if(getSlack(j2)>getSlack(j1))
			return 1;
		if(getSlack(j2)<getSlack(j1))
			return -1;
		return 0;
	}
	double getSlack(Job j)
	{
		return Math.max(0, j.dueDate-j.setUpTime-j.processingTime);
	}

}
