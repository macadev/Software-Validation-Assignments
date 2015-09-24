package roundtrippathtree;

import ca.mcgill.ecse429.conformancetest.statemodel.*;

public class TreeEdge {
	
	private Transition transitionData;
	private TreeNode toNode;
	
	public TreeEdge(Transition transitionData, TreeNode toNode) {
		this.transitionData = transitionData;
		this.toNode = toNode;
	}

	public Transition getTransitionData() {
		return transitionData;
	}

	public void setTransitionData(Transition transitionData) {
		this.transitionData = transitionData;
	}

	public TreeNode getToNode() {
		return toNode;
	}

	public void setToNode(TreeNode toNode) {
		this.toNode = toNode;
	}

}
