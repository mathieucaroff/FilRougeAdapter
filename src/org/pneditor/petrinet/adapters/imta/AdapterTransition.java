package org.pneditor.petrinet.adapters.imta;

import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.models.hamcyt.Transition;

public class AdapterTransition extends AbstractTransition {

	private Transition transition;

	public AdapterTransition(Transition transition) {
		super("");
		this.transition = transition;
	}

	public Transition getTransition() {
		return transition;
	}

}
