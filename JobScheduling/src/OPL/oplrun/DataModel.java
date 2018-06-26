package OPL.oplrun;

import ilog.opl.IloCustomOplDataSource;
import ilog.opl.IloOplDataHandler;
import ilog.opl.IloOplFactory;

public class DataModel extends IloCustomOplDataSource {

	public DataModel(IloOplFactory oplEnv) {
		super(oplEnv);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void customRead() 
	{
		// TODO Auto-generated method stub
		IloOplDataHandler handler = getDataHandler();
		
		handler.startElement("n");
        handler.addIntItem(4);
        handler.endElement();
        
        handler.startElement("p");
        handler.addIntItem(2);
        handler.endElement();
        
        handler.startElement("r");
        handler.startArray();
        handler.addIntItem(0);
        handler.addIntItem(1);
        handler.addIntItem(3);
        handler.addIntItem(4);
        handler.endArray();
        handler.endElement();
        
        handler.startElement("d");
        handler.startArray();
        handler.addIntItem(100);
        handler.addIntItem(100);
        handler.addIntItem(100);
        handler.addIntItem(100);
        handler.endArray();
        handler.endElement();
        
        handler.startElement("w");
        handler.startArray();
        handler.addIntItem(1);
        handler.addIntItem(10);
        handler.addIntItem(2);
        handler.addIntItem(2);
        handler.endArray();
        handler.endElement();

	}



}
