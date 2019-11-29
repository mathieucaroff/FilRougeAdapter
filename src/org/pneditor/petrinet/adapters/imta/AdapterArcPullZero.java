package org.pneditor.petrinet.adapters.imta;

import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.models.hamcyt.ArcPull;

/**
 * This class adds a few behaviors that are specific to the inhibitory arcs.
 */
public class AdapterArcPullZero extends AdapterArcPull {

	/**
	 * Create an instance of arc, from a place to a transition.
	 * 
	 * @param source       the place
	 * @param destination  the transition
	 * @param multiplicity the multiplicity of the arc
	 * @param arcPull      the wrapped arcPull
	 */
	public AdapterArcPullZero(AbstractNode source, AbstractNode destination, ArcPull arcPull) {
		// No need to call the ArcPullZero constructor or anything, this is done by
		// the parent class
		super(source, destination, 0, arcPull);
	}

	@Override
	public int getMultiplicity() {
		return 0;
	}

	@Override
	public boolean isInhibitory() {
		return true;
	}

}
