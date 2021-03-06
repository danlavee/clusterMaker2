package edu.ucsf.rbvi.clusterMaker2.internal.algorithms.attributeClusterers.BicFinder;

import java.util.ArrayList;
import java.util.List;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.work.ContainsTunables;
import org.cytoscape.work.Tunable;
import org.cytoscape.work.util.BoundedDouble;

import edu.ucsf.rbvi.clusterMaker2.internal.algorithms.attributeClusterers.AttributeList;

public class BicFinderContext {

	CyNetwork network;

	@Tunable(description="Threshold for Gene/Condition addition to Biclusters", gravity=1)
	public BoundedDouble alpha = new BoundedDouble(0.0, 0.5, 1.0, true, true);
	
	@Tunable(description="Threshold for Bicluster Evaluation", gravity=2)
	public BoundedDouble delta = new BoundedDouble(-1.0, 0.0, 1.0, true, true);
		
	@ContainsTunables
	public AttributeList attributeList = null;

	public boolean selectedOnly = false;
	
	@Tunable(description="Use only selected nodes/edges for cluster", 
	         groups={"BicFinder Parameters"}, gravity=100)
	public boolean getselectedOnly() { return selectedOnly; }
	
	public void setselectedOnly(boolean sel) {
		//if (network != null && this.selectedOnly != sel) kcluster.updateKEstimates(network, sel);
		this.selectedOnly = sel;
	}
		
	@Tunable(description="Create groups from clusters", groups={"Visualization Options"}, gravity=150)
	public boolean createGroups = false;

	@Tunable(description="Show HeatMap when complete", groups={"Visualization Options"}, gravity=151)
	public boolean showUI = false;


	public BicFinderContext() {
	}

	public void setNetwork(CyNetwork network) {
		if (this.network != null && this.network.equals(network))
			return;

		this.network = network;
		if (attributeList == null)
			attributeList = new AttributeList(network);
		else
			attributeList.setNetwork(network);

		//kcluster.updateKEstimates(network, selectedOnly);
	}

	public CyNetwork getNetwork() { return network; }

	public List<String> getParams() {
		List<String> params = new ArrayList<String>();
		params.add("nodeAttributeList="+attributeList.getNodeAttributeList().toString());
		params.add("edgeAttribute="+attributeList.getEdgeAttribute());
		params.add("selectedOnly="+selectedOnly);
		params.add("createGroups="+createGroups);
		return params;
	}


}
