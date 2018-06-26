package test;

import Data.DataGenerator;

public class Test {


	@org.junit.Test
	public void testDataGen() throws Exception{
		DataGenerator ob=new DataGenerator("");
		ob.manageData(10, 0.8, 0.6, 100, 1, 10);
	}

}
