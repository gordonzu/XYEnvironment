package com.gordonzu.agent.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gordonzu.agent.Action;
import com.gordonzu.agent.Agent;
import com.gordonzu.agent.Environment;
import com.gordonzu.agent.EnvironmentObject;
import com.gordonzu.agent.EnvironmentView;
import com.gordonzu.agent.EnvironmentViewNotifier;
import com.gordonzu.agent.Percept;
//import com.gordonzu.util.Tasks;

public abstract class AbstractEnvironment implements Environment, EnvironmentViewNotifier {
    
    private Set<EnvironmentObject> envObjects = new LinkedHashSet<>();
    private Set<Agent> agents = new LinkedHashSet<>();
    private Set<EnvironmentView> views = new LinkedHashSet<>();

	public List<Agent> getAgents() {
	    return new ArrayList<>(agents);
    }

    protected void addEnvironmentObject(EnvironmentObject eo) {
        envObjects.add(eo);
        if (eo instanceof Agent) {
            Agent a = (Agent) eo;
            if (!agents.contains(a)) {
                agents.add(a);
                notifyEnvironmentViews(a);
            }
		}
    }

    private void notifyEnvironmentViews(Agent agent) {
        for (EnvironmentView view : views) {
            view.agentAdded(agent, this);
        }
    }
}


