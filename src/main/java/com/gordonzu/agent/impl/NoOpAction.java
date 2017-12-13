package com.gordonzu.agent.impl;

public class NoOpAction /*extends DynamicAction*/ {

	public static final NoOpAction NO_OP = new NoOpAction();

	public boolean isNoOp() {
		return true;
	}

	/*private NoOpAction() {
		super("NoOp");
	}*/
}
