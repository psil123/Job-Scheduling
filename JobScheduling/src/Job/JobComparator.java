package Job;

import java.util.*;

public class JobComparator implements Comparator<Job> {

	public int compare(Job j1, Job j2) {
		if (getPriority(j1) < getPriority(j2))
			return 1;
		else if (getPriority(j1) > getPriority(j2))
			return -1;
		else {
			return 0;
		}
	}

	double getPriority(Job j) {
		// return Math.max(0, j.dueDate-j.setUpTime-j.processingTime);
		return j.weight / (getRPT(j) + getRemainingSlack(j));
	}

	/**
	 * calculate remaining available time
	 * 
	 * @param j
	 * @return
	 */
	double getRAT(Job j) {
		return Math.max(0, j.dueDate - JobScheduler.dim + 2);
	}

	/**
	 * Calculate remaining processing time
	 * 
	 * @param j
	 * @return
	 */
	double getRPT(Job j) {
		return j.processingTime;
	}

	double getRemainingSlack(Job j) {
		return Math.max(0, getRAT(j) - getRPT(j));
	}
}
