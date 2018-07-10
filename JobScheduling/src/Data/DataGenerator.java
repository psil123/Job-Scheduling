package Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Job.Job;

public class DataGenerator 
{
	/* Generating a list of jobs using Potts method
	 * */
	private List<Job> joblist;
	public String outputFileName = "";
	
	public DataGenerator()
	{
	}
	/**
	 * 
	 * @param n number of jobs
	 * @param r due date range factor: width of the interval centered around average due date
	 * @param t tardiness factor: is a coarse measure of the proportion of jobs that might be expected to be tardy in an arbitrary sequence
	 * @param MAX_PI maximum processing time
	 * @param MIN_WI lower bound of weight
	 * @param MAX_WI upper bound of weight
	 * @throws Exception
	 */
	public void generateData(int n,double r,double t,int MAX_PI,int MIN_WI,int MAX_WI) throws Exception
	{
		generatePott(n,r,t,MAX_PI,MIN_WI,MAX_WI);
	}
	private void generatePott(int n,double r,double t,int MAX_PI,int MIN_WI,int MAX_WI) throws Exception
	{
		int P=0,minR=Integer.MAX_VALUE,minP=Integer.MAX_VALUE; 
		joblist=new ArrayList<Job>();
		for(int i=0;i<n;i++)
		{
			getJoblist().add(new Job(Integer.toString(i), (int)(1+(MAX_PI-1)*Math.random()), 0, 0));
			P+=getJoblist().get(i).processingTime;
			if(getJoblist().get(i).processingTime<minP) minP = getJoblist().get(i).processingTime;
		}
		for(int i=0;i<n;i++) 
		{
			//what if d is 0? minimum due date is alwys minimum processing time
			getJoblist().get(i).dueDate=(int)(Math.ceil(P*(1-t-r/2))+(Math.ceil(P*(1-t+r/2))-Math.ceil(P*(1-t-r/2)))*Math.random());
			getJoblist().get(i).dueDate = Math.max(0, getJoblist().get(i).dueDate);
			//job needs to be ready at least before due date
			getJoblist().get(i).setUpTime= 0 + (int)((getJoblist().get(i).dueDate)*Math.random()); 
			getJoblist().get(i).weight=(int)(MIN_WI+(MAX_WI-MIN_WI)*Math.random());
			if(getJoblist().get(i).setUpTime<minR){
				minR = getJoblist().get(i).setUpTime; 
			}
		}
		//this portion of code is for making at least one job available at the beginning of horizon
		if(minR>0){
			for(int i=0;i<n;i++) 
			{
				getJoblist().get(i).setUpTime -= minR;
			}
		}
	}
	
	public String writeToCSV(String filePath) throws IOException
	{
		if(filePath.length()==0){
			filePath = "Data\\potts_n_jobs_"+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())+".csv";
		}
		File file =new File(filePath);
		FileWriter fw=new FileWriter(file);
		for(Job i:getJoblist())
			fw.append(i.toString()+"\n");
		fw.flush();
		fw.close();
		System.out.println("Data stored in : "+file.getAbsolutePath());
		return file.getAbsolutePath();
	}
	
	public void readFromCSV(String fname) throws Exception
	{
		BufferedReader read= new BufferedReader(new FileReader(fname));
		String line=null;
		joblist=new ArrayList<Job>();
		while((line=read.readLine())!=null)
			getJoblist().add(new Job(line));
		read.close();
	}
	
	public String writeToDat(String filePath) throws Exception
	{		
		if(filePath.length()==0){
			outputFileName = "potts_n_jobs_"+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())+".dat";
			filePath = "Data\\" + outputFileName;
		}
		File file =new File(filePath);
		FileWriter fw=new FileWriter(file);
		fw.append("N = "+joblist.size()+";\n");
		fw.append("P = [");
		for(int i=0;i<joblist.size()-1;i++)
			fw.append(joblist.get(i).processingTime+",");
		fw.append(joblist.get(joblist.size()-1).processingTime+"];\n");
		

		fw.append("R = [");
		for(int i=0;i<joblist.size()-1;i++)
			fw.append(joblist.get(i).setUpTime+",");
		fw.append(joblist.get(joblist.size()-1).setUpTime+"];\n");
		
		fw.append("D = [");
		for(int i=0;i<joblist.size()-1;i++)
			fw.append(joblist.get(i).dueDate+",");
		fw.append(joblist.get(joblist.size()-1).dueDate+"];\n");
		
		fw.append("W = [");
		for(int i=0;i<joblist.size()-1;i++)
			fw.append(joblist.get(i).weight+",");
		fw.append(joblist.get(joblist.size()-1).weight+"];\n");
		
		fw.flush();
		fw.close();
		System.out.println("Data stored in : "+file.getAbsolutePath());
		return file.getAbsolutePath();
	}
	
	public List<Job> getJoblist() {
		return joblist;
	}
}
