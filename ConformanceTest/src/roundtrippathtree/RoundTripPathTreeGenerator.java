package roundtrippathtree;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse429.conformancetest.statemodel.State;
import ca.mcgill.ecse429.conformancetest.statemodel.Transition;
import ca.mcgill.ecse429.conformancetest.statemodel.StateMachine;
import ca.mcgill.ecse429.conformancetest.statemodel.persistence.PersistenceStateMachine;

public class RoundTripPathTreeGenerator {
	
	private List<String> statesCovered;
	private List<TreeNode> nodesToDevelop;
	private StateMachine sm;
	private TreeNode root;
	
	public RoundTripPathTreeGenerator() {
		this.statesCovered = new ArrayList<String>();
		this.nodesToDevelop = new ArrayList<TreeNode>();
	}
	
	public static void main(String[] args) {
		RoundTripPathTreeGenerator rg = new RoundTripPathTreeGenerator();
		rg.buildRoundTripPathTree("ccoinbox.xml");
	}
	
	public void buildRoundTripPathTree(String filename) {
		PersistenceStateMachine.loadStateMachine(filename);
		sm = StateMachine.getInstance();
		
		// Retrieve start state form the loaded state machine
		//State startState = sm.getStartState();
		State startState = sm.getStates().get(1);
		this.root = new TreeNode(startState.getName());
		
		exploreNodeAndTransitions(root);
		
		while (nodesToDevelop.size() > 0) {
			exploreNodeAndTransitions(nodesToDevelop.get(0));
			nodesToDevelop.remove(0);
		}
		
		printRoundTripPathTree(root, "", true);
	}
	
	private void exploreNodeAndTransitions(TreeNode node) {
		String currentStateName = node.getStateName();
		
		if (hasStateBeenCovered(currentStateName)) return;
		
		for (Transition transition : sm.getTransitions()) {
			
			// Transition is associated to the current state
			if (transition.getFrom().getName().equals(currentStateName)) {
				
				TreeNode undevelopedNode = new TreeNode(transition.getTo().getName());
				TreeEdge newTreeEdge = new TreeEdge(transition, undevelopedNode);
				node.addTreeEdge(newTreeEdge);
				
				if (!undevelopedNode.getStateName().equals(currentStateName)) {
					nodesToDevelop.add(undevelopedNode);
				}
			}
			
		}
		statesCovered.add(currentStateName);
	}
	
	private boolean hasStateBeenCovered(String stateName) {
		for (String coveredState : statesCovered) {
			if (stateName.equals(coveredState)) return true;
		}
		return false;
	}
	
	public void printRoundTripPathTree(TreeNode node, String indent, boolean last) {
		
		System.out.print(indent);
		if (last) {
			System.out.print("\\-");
			indent+= "  ";
		} else {
			System.out.print("|-");
			indent += "| ";
		}
		System.out.println(node.getStateName());
		
		List<TreeEdge> edges = node.getTreeEdgeList();
		TreeEdge edge;
		for (int i = 0; i < edges.size(); i++) {
			edge = edges.get(i);
			printRoundTripPathTree(edge.getToNode(), indent, i == edges.size() - 1);
		}
	}
	
	
}
