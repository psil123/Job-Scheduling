package Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Job.Job;

public class DataGenerator 
{
	/* Generating a list of jobs using Potts method
	 * */
	public List<Job> generate(int n,double r,double t,int MAX_PI,int MIN_WI,int MAX_WI,boolean writeToCSV,String fname) throws IOException
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
		if(writeToCSV)
			writeToCSV(jobs,(fname==null)?"Data":fname,false);
		return jobs;
	}
	
	public String writeToCSV(List<Job> jobs,String filename,boolean returnPath) throws IOException
	{
		File file =new File((filename==null)?"Data":filename+".csv");
		FileWriter fw=new FileWriter(file);
		for(Job i:jobs)
			fw.append(i.toString()+"\n");
		fw.flush();
		fw.close();
		System.out.println("Data stored in : "+file.getAbsolutePath());
		if(returnPath)
			return file.getAbsolutePath();
		return null;
	}
	
	public List<Job> readFromCSV(String path) throws Exception
	{
		List<Job> jobs=new ArrayList<Job>();
		BufferedReader read= new BufferedReader(new FileReader(path));
		String line=null;
		while((line=read.readLine())!=null)
			jobs.add(new Job(line));
		read.close();
		return jobs;
	}
}
