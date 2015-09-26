package testgenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import ca.mcgill.ecse429.conformancetest.statemodel.StateMachine;
import roundtrippathtree.*;


public class StateMachineConformanceTestGenerator {

	private File file;
	private TreeNode roundTripPathTreeRoot;
	private String smImplementationName;
	private int conformanceTestCount;
	
	public StateMachineConformanceTestGenerator(String filepath, TreeNode roundTripPathTreeRoot) {
		this.file = new File(filepath);
		this.roundTripPathTreeRoot = roundTripPathTreeRoot;
	}
	
	public void writeToFile(String textToPrint) {
		try {

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.append(textToPrint);
			bw.newLine();
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void buildUnitTestsUsingDFS() {
		this.conformanceTestCount = 1;
		buildUnitTestsUsingDFSHelper(roundTripPathTreeRoot, null, "");
	}
	
	public void buildUnitTestsUsingDFSHelper(TreeNode node, TreeEdge connectingEdge, String cachedCode) {
		List<TreeEdge> edgeList = node.getTreeEdgeList();
		
		if (connectingEdge == null) {
			// special case for root node
			cachedCode = addCodeToBeginNewConformanceTest(cachedCode);
		} else {
			cachedCode = addCodeToExecuteEvent(connectingEdge, cachedCode);
		}
		
		cachedCode = addCodeToTestCurrentState(node, cachedCode);
		
		if (node.getTreeEdgeList().size() == 0) {
			// case for leaf nodes
			cachedCode = addCodeToCloseConformanceTest(cachedCode);
			printCachedCode(cachedCode);
		}
		
		for (TreeEdge edge : edgeList) {
			TreeNode neighbour = edge.getToNode();
			buildUnitTestsUsingDFSHelper(neighbour, edge, cachedCode);
		}
	}

	
	public String addCodeToTestCurrentState(TreeNode node, String cachedCode) {
		String currentState = node.getStateName();
		cachedCode += "\t\tassertEquals(\"Verify current state matches FSM\",\"" + currentState + "\", sm.getStateFullName());\n";
		return cachedCode;
	}
	
	public String addCodeToBeginNewConformanceTest(String cachedCode) {
		cachedCode += "\t@Test\n" + "\tpublic void conformanceTest@TESTNUM@() {\n";
		return cachedCode;
	}
	
	public String addCodeToCloseConformanceTest(String cachedCode) {
		cachedCode += "\t}\n";
		return cachedCode;
	}
	
	public String addCodeToExecuteEvent(TreeEdge edge, String cachedCode) {
		if (edge == null) return cachedCode;
		cachedCode += "\t\tsm." + edge.getTransitionData().getEvent() + "();\n";
		return cachedCode;
	}
	
	public void printCachedCode(String cachedCode) {
		cachedCode = cachedCode.replaceFirst("@TESTNUM@", "" + this.conformanceTestCount);
		conformanceTestCount++;
		writeToFile(cachedCode.toString());
		writeToFile("");
	}
	
	public void initializeJavaFile() {
		StateMachine sm = StateMachine.getInstance();
		String smClassName = sm.getClassName();
		String[] arr = smClassName.split("\\.");
		String smPackageName = sm.getPackageName();
		this.smImplementationName = arr[0];
		
		writeToFile("package testgenerator;");
		writeToFile("");
		writeToFile("import static org.junit.Assert.*;");
		writeToFile("import org.junit.After;");
		writeToFile("import org.junit.Before;");
		writeToFile("import org.junit.Test;");
		writeToFile("import ca.mcgill.ecse429.conformancetest.statemodel.*;");
		writeToFile("import " + smPackageName + ".*;");
		writeToFile("");
		writeToFile("public class StateMachineGeneratedTests {");
		writeToFile("");
		
		writeToFile("	private " + smImplementationName + " sm;");
		writeToFile("");
		writeToFile("	@Before");
		writeToFile("	public void setUp() {");
		writeToFile("		sm = new " + smImplementationName + "();");
		writeToFile("	}");
		writeToFile("");
		writeToFile("	@After");
		writeToFile("	public void tearDown() {");
		writeToFile("");
		writeToFile("	}");
		writeToFile("");
	}
	
	public void finalizeJavaFile() {
		writeToFile("}");
	}
	
}
