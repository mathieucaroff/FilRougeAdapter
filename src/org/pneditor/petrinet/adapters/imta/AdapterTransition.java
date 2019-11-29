package org.pneditor.petrinet.adapters.imta;

import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.models.hamcyt.Transition;

/**
 * This class of the is part of the adapter, and implements the behaviors
 * required by AbstractTransition.
 */

public class AdapterTransition extends AbstractTransition {

	private Transition transition;

	public AdapterTransition(Transition transition) {
		super("");
		this.transition = transition;
	}

	/**
	 * Get the wrapped transition
	 * 
	 * This allows to call the delete method of the Petri network on that
	 * transition.
	 * 
	 * @return the wrapped transition
	 */
	Transition getTransition() { // package-protected
		return transition;
	}

}
