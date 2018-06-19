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
	List<Job> joblist;
	public DataGenerator(String fname)
	{
		this.fname=fname;
	}
	public void createData(int n,double r,double t,int MAX_PI,int MIN_WI,int MAX_WI) throws Exception
	{
		if(fname.length()==0)
			generatePott(n,r,t,MAX_PI,MIN_WI,MAX_WI);
		else
			readFromCSV(fname);
	}
	void generatePott(int n,double r,double t,int MAX_PI,int MIN_WI,int MAX_WI) throws IOException
	{
		int P=0,C=0;
		joblist=new ArrayList<Job>();
		for(int i=0;i<n;i++)
		{
			joblist.add(new Job(Integer.toString(i), (int)(1+(MAX_PI-1)*Math.random()), 0, 0));
			P+=joblist.get(i).processingTime;
		}
		for(int i=0;i<n;i++) 
		{
			C+=joblist.get(i).processingTime;
			joblist.get(i).dueDate=(int)(Math.ceil(P*(1-t-r/2))+(Math.ceil(P*(1-t+r/2))-Math.ceil(P*(1-t-r/2)))*Math.random());
			joblist.get(i).setUpTime=(int)(C/2*Math.random());
			joblist.get(i).waitTime=(int)(MIN_WI+(MAX_WI-MIN_WI)*Math.random());
		}
		writeToCSV();
	}
	
	 void writeToCSV() throws IOException
	{
		File file =new File("Data\\potts_n_jobs_"+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())+".csv");
		FileWriter fw=new FileWriter(file);
		for(Job i:joblist)
			fw.append(i.toString()+"\n");
		fw.flush();
		fw.close();
		System.out.println("Data stored in : "+file.getAbsolutePath());
		
	}
	
	 void readFromCSV(String fname) throws Exception
	{
		
		BufferedReader read= new BufferedReader(new FileReader("Data\\"+fname));
		String line=null;
		joblist=new ArrayList<Job>();
		while((line=read.readLine())!=null)
			joblist.add(new Job(line));
		read.close();
	}
}
