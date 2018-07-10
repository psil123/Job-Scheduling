package OPL.oplrun;

import Data.DataGenerator;
import ilog.concert.IloDiscreteDataCollectionArray;
import ilog.concert.IloException;
import ilog.concert.IloIntMap;
import ilog.concert.IloMap;
import ilog.concert.IloNumMap;
import ilog.cplex.IloCplex;
import ilog.opl.IloOplDataSource;
import ilog.opl.IloOplErrorHandler;
import ilog.opl.IloOplException;
import ilog.opl.IloOplFactory;
import ilog.opl.IloOplModel;
import ilog.opl.IloOplModelDefinition;
import ilog.opl.IloOplModelSource;
import ilog.opl.IloOplSettings;

/**
 * This class runs OPL model
 * Instruction for setting up the project
 * https://www.ibm.com/support/knowledgecenter/en/SSSA5P_12.3.0/ilog.odms.ide.help/Content/Optimization/Documentation/Optimization_Studio/_pubskel/ps_opl1243.html
 * @author sarkara1
 *
 */
public class OPL {
	
	String modelPath = "";
	String dataPath = "";
	
	IloOplFactory oplF;
	IloCplex cplex;
	IloOplModel opl;
	DataGenerator dg;
	
	Long timeTaken = 0L;
	 
	boolean debugMode = false;
	
	public OPL(String modelPath,DataGenerator dg) {
		super();
		this.modelPath = modelPath;
		this.dg = dg;
	}
	
	public void startEngine() throws Exception{
		IloOplFactory.setDebugMode(debugMode);
		oplF = new IloOplFactory();
		IloOplErrorHandler errHandler = oplF.createOplErrorHandler();
		IloOplModelSource modelSource = oplF.createOplModelSource(modelPath);
		IloOplSettings settings = oplF.createOplSettings(errHandler);
		IloOplModelDefinition def = oplF.createOplModelDefinition(modelSource,settings);
		cplex = oplF.createCplex();
		//cplex.setOut(System.out);
		opl = oplF.createOplModel(def, cplex);
		IloOplDataSource dataSource = oplF.createOplDataSource(dg.writeToDat(""));
		opl.addDataSource(dataSource);
		opl.generate();
	}

	public void solve() throws Exception{
		timeTaken = System.currentTimeMillis();
		if (cplex.solve()){
			System.out.println("OBJECTIVE: " + opl.getCplex().getObjValue());
			opl.postProcess();
		}
		else
		{
			throw new Exception("No Solution is found");
		}
		timeTaken = (System.currentTimeMillis() - timeTaken) / 1000;
	}
	
	public double[][][] getMatrix3DParam(String name) throws IloException{
		double[][][] m = null;
		IloNumMap m3d = opl.getElement(name).asNumMap();
		int numRow = m3d.getSize();
		int numCol = m3d.getNbDim();
		int numSlice = m3d.getTotalSize() / (numRow * numCol);
		m = new double[numRow][numCol][numSlice];
		for(int i=0; i<numRow; i++){
			for(int j=0; j<numCol; j++){
				for(int k=0; k<numSlice; k++){
					m[i][j][k] = m3d.getSub(i+1).getSub(j+1).get(k+1);
				}
			}
		}
		return m;
	}
	
	public int[][] getMatrix2DParam(String name) throws IloException{
	
		IloIntMap m2d = opl.getElement(name).asIntMap();
		int row = m2d.getSize();
		int column = m2d.getSub(1).getSize();
		int[][] m = new int[row][column];
		for(int i=1;i<=row;i++){
			IloIntMap m1d = m2d.getSub(i);
			for(int j=1;j<=column;j++){
				String vals = m1d.toString().replaceAll("\n", "");
				vals = vals.substring(1, vals.length()-1);
				m[i-1][j-1] = Integer.parseInt(vals.split(",")[j-1].trim());
			}
		}
		return m;
	}
	
	public int getValiable(String name) throws IloException{
		
		return opl.getElement(name).asIntVar().getMax();
	}
	
	public void writeSchedule(int[][] ks){
		for(int i=0; i<ks.length; i++){
			System.out.print("Job#"+i+">");
			for(int j=0; j<ks[i].length; j++){
				System.out.print(ks[i][j]);
			}
			System.out.println();
		}
	}
	
	public void printResult(){
		opl.printSolution(System.out);
	}
	
	public int getObjectiveValue() throws IloException{
		return (int) Math.abs(opl.getCplex().getObjValue());
	}
	
	public void handleException(Exception ex){
		if(ex instanceof IloOplException){
			System.err.println("### OPL exception: " + ex.getMessage());
			ex.printStackTrace();
		}
		else if (ex instanceof IloException){
			System.err.println("### CONCERT exception: " + ex.getMessage());
			ex.printStackTrace();
		}
		else if (ex instanceof Exception ){
			System.err.println("### other exception: " + ex.getMessage());
		}
	}
	
	public void closeOPL(){
		if (opl.hasCplex())
			 opl.getCplex().end();
		else
			 opl.getCP().end();
		oplF.end();
			 
	}

	public int getTimeTaken() {
		return timeTaken.intValue();
	}
	
}
