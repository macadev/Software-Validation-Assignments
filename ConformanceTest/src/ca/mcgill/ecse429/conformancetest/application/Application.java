package ca.mcgill.ecse429.conformancetest.application;

import ca.mcgill.ecse429.conformancetest.statemodel.StateMachine;
import roundtrippathtree.RoundTripPathTreeGenerator;
import roundtrippathtree.TreeNode;
import testgenerator.StateMachineConformanceTestGenerator;

public class Application {
	
	public static void main(String[] args) {
		
		RoundTripPathTreeGenerator rtptg = new RoundTripPathTreeGenerator();
		TreeNode root = rtptg.buildRoundTripPathTree("ccoinbox.xml");
		
		StateMachineConformanceTestGenerator smctg = 
				new StateMachineConformanceTestGenerator("src/testgenerator/StateMachineGeneratedTests.java", root);
		
		smctg.initializeJavaFile();
		smctg.buildUnitTestsUsingDFS();
		smctg.finalizeJavaFile();
		//simpleRec("", 0);
		
		
	}
	
	public static void simpleRec(String st, int num) {
		if (num == 3) return;
		num++;
		st = addStuff(st);
		simpleRec(st, num);
		System.out.println(st);
	}
	
	public static String addStuff(String str) {
		str += "stuff";
		return str;
	}

}
