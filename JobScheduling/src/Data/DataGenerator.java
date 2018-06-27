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
	private String fname;
	private List<Job> joblist;
	public DataGenerator(String fname)
	{
		this.fname=fname;
	}
	public void manageData(int n,double r,double t,int MAX_PI,int MIN_WI,int MAX_WI) throws Exception
	{
		if(fname.length()==0)
			generatePott(n,r,t,MAX_PI,MIN_WI,MAX_WI);
		else
			readFromCSV(fname);
	}
	private void generatePott(int n,double r,double t,int MAX_PI,int MIN_WI,int MAX_WI) throws Exception
	{
		int P=0,C=0;
		joblist=new ArrayList<Job>();
		for(int i=0;i<n;i++)
		{
			getJoblist().add(new Job(Integer.toString(i), (int)(1+(MAX_PI-1)*Math.random()), 0, 0));
			P+=getJoblist().get(i).processingTime;
		}
		for(int i=0;i<n;i++) 
		{
			C+=getJoblist().get(i).processingTime;
			getJoblist().get(i).dueDate=(int)(Math.ceil(P*(1-t-r/2))+(Math.ceil(P*(1-t+r/2))-Math.ceil(P*(1-t-r/2)))*Math.random());
			getJoblist().get(i).setUpTime=(int)(C/2*Math.random());
			getJoblist().get(i).waitTime=(int)(MIN_WI+(MAX_WI-MIN_WI)*Math.random());
		}
		writeToCSV();
		writeToDat();
	}
	
	private void writeToCSV() throws IOException
	{
		File file =new File("Data\\potts_n_jobs_"+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())+".csv");
		FileWriter fw=new FileWriter(file);
		for(Job i:getJoblist())
			fw.append(i.toString()+"\n");
		fw.flush();
		fw.close();
		System.out.println("Data stored in : "+file.getAbsolutePath());
		
	}
	
	private void readFromCSV(String fname) throws Exception
	{
		
		BufferedReader read= new BufferedReader(new FileReader("Data\\"+fname));
		String line=null;
		joblist=new ArrayList<Job>();
		while((line=read.readLine())!=null)
			getJoblist().add(new Job(line));
		read.close();
	}
	private void writeToDat() throws Exception
	{
		File file =new File("Data\\potts_n_jobs_"+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())+".dat");
		FileWriter fw=new FileWriter(file);
		for(Job i:getJoblist())
			fw.append(i.toString()+"\n");
		fw.flush();
		fw.close();
		System.out.println("Data stored in : "+file.getAbsolutePath());
	}
	public List<Job> getJoblist() {
		return joblist;
	}
	public void setfname(String fname)
	{
		this.fname=fname;
	}
	public String getfname()
	{
		return fname;
	}
}
