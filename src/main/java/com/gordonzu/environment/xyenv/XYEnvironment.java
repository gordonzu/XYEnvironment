package com.gordonzu.environment.xyenv;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.gordonzu.util.datastructure.XYLocation;
import com.gordonzu.agent.EnvironmentObject;
import com.gordonzu.agent.impl.AbstractEnvironment;

public class XYEnvironment extends AbstractEnvironment {
    private XYEnvironmentState envState = null;

    public XYEnvironment(int width, int height) {
        assert (width > 0);
        assert (height > 0);

        envState = new XYEnvironmentState(width, height);
    } 

   	public void addObjectToLocation(EnvironmentObject eo, XYLocation loc) {
		    moveObjectToAbsoluteLocation(eo, loc);
	  }

    public void moveObjectToAbsoluteLocation(EnvironmentObject eo, XYLocation loc) {
		    envState.moveObjectToAbsoluteLocation(eo, loc);
		    addEnvironmentObject(eo);
	  }

}

class XYEnvironmentState {
    int height;
    int width;

		private Map<XYLocation, Set<EnvironmentObject>> objsAtLocation = new LinkedHashMap<XYLocation, Set<EnvironmentObject>>();

    public XYEnvironmentState(int width, int height) {
        this.width = width;
        this.height = height;
    }  

		public void moveObjectToAbsoluteLocation(EnvironmentObject eo, XYLocation loc) {
				for (Set<EnvironmentObject> eos : objsAtLocation.values()) {
						if (eos.remove(eo)) {
								break; // Should only every be at 1 location
						}
				}
				getObjectsAt(loc).add(eo);
		}

		public Set<EnvironmentObject> getObjectsAt(XYLocation loc) {
				Set<EnvironmentObject> objectsAt = objsAtLocation.get(loc);
				if (null == objectsAt) {
						// Always ensure an empty Set is returned
						objectsAt = new LinkedHashSet<EnvironmentObject>();
						objsAtLocation.put(loc, objectsAt);
				}
				return objectsAt;
		}
}
