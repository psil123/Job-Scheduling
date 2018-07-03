package Job;
import java.util.*;
public class JobComparator implements Comparator<Job>
{

	public int compare(Job j1, Job j2) {
		
		if(getSlack(j2)<getSlack(j1))
			return 1;
		else if(getSlack(j2)>getSlack(j1))
			return -1;
		else{
			if(j1.weight < j2.weight)
				return 1;
			else if (j1.weight > j2.weight)
				return -1;
			else
				return 0;
		}
	}
	double getSlack(Job j)
	{
		return Math.max(0, j.dueDate-j.setUpTime-j.processingTime);
	}

}
