package roundtrippathtree;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse429.conformancetest.statemodel.*;

public class TreeNode {
	
	private String stateName;
	private List<TreeEdge> treeEdgeList;

	public TreeNode(String stateName) {
		this.stateName = stateName;
		this.treeEdgeList= new ArrayList<TreeEdge>();
	}
	
	public List<TreeEdge> getTreeEdgeList() {
		return treeEdgeList;
	}

	public void setTreeEdgeList(List<TreeEdge> treeEdgeList) {
		this.treeEdgeList = treeEdgeList;
	}
	
	public void addTreeEdge(TreeEdge treeEdge) {
		this.treeEdgeList.add(treeEdge);
	}

	public String getStateName() {
		return this.stateName;
	}
	
}
