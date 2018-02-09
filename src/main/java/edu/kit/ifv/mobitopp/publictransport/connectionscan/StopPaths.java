package edu.kit.ifv.mobitopp.publictransport.connectionscan;

import java.util.List;

import edu.kit.ifv.mobitopp.publictransport.model.Connection;
import edu.kit.ifv.mobitopp.publictransport.model.Stop;
import edu.kit.ifv.mobitopp.publictransport.model.StopPath;
import edu.kit.ifv.mobitopp.simulation.SimulationDateIfc;

public interface StopPaths {

	List<Stop> stops();

	StopPath pathTo(Stop stop);

	List<StopPath> stopPaths();
	
	boolean isConnectionReachableAt(Stop stop, SimulationDateIfc time, Connection connection);

}
