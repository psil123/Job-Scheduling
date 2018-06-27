package OPL.oplrun;

import java.util.List;

import Data.DataGenerator;
import Job.Job;
import ilog.opl.IloCustomOplDataSource;
import ilog.opl.IloOplDataHandler;
import ilog.opl.IloOplFactory;

public class OPLDataModel extends IloCustomOplDataSource
{
	DataGenerator dg;
	public OPLDataModel(DataGenerator dg,IloOplFactory oplEnv)
	{
		super(oplEnv);
		this.dg=dg;
	}
	
	public void customRead() 
	{
//		try {
//			dg.manageData(10, 0.8, 0.6, 100, 1, 10);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		    IloOplDataHandler handler = getDataHandler();
			List<Job> jobs=dg.getJoblist();
						
			handler.startElement("n");
			handler.addIntItem(jobs.size());
	        handler.endElement();
	        
			handler.startElement("p");
	        handler.addIntItem((int) Math.floor(jobs.stream()
	        										.mapToInt(j->j.processingTime)
	        										.average()
	        										.getAsDouble()));
	        handler.endElement();
	        
			handler.startElement("r");
	        handler.startArray();
			for(Job i:jobs)
				handler.addIntItem(i.setUpTime);
			handler.endArray();
	        handler.endElement();
	        
			handler.startElement("d");
	        handler.startArray();
			for(Job i:jobs)
				handler.addIntItem(i.dueDate);
			handler.endArray();
	        handler.endElement();
	        
			handler.startElement("w");
	        handler.startArray();
			for(Job i:jobs)
				handler.addIntItem(i.weight);
			handler.endArray();
	        handler.endElement();
	}

}
