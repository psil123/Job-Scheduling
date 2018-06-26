package OPL.oplrun.heuristics;

import java.util.HashSet;
import java.util.Set;

public class Bouma46 {	
	
	public void execute(double[][][] x, int n, int p, int T) throws Exception{
		
		Set<Integer> J = new HashSet<Integer>();
		Set<Integer> S = new HashSet<Integer>();
		Integer tp = 0, j0 = 0, t1, tk;
		
		//Step 0 
		//Initialize set J and S
		for(int i=0; i<n; i++){
			J.add(i+1);
		}
		for(int i= 0; i<T; i++){
			S.add(i);
		}
 		
		//Step 1
		while(J.size()>1){
			
			//derive tp and j0
			Integer[] mins = getArgMin(x, J, S, p);
			if(mins!=null){
				tp = mins[0];
				j0 = mins[1];				
			}
			else{
				throw new Exception("Cannot find ArgMin");
			}
			
			if(x[j0][p][tp]==1){
				
				
				
			}
			
			
		}
		
	}

	private Integer[] getArgMin(double[][][] x, Set<Integer> J, Set<Integer> S, int p) {
		Integer[] result = new Integer[2];
		for(Integer tp:S){
			for(Integer j0:J){
				if(x[tp][j0][p]>0){
					result[0] = tp;
					result[1] = j0;
					return result;
				}
			}
		}
		return null;
	}

	
	
}
