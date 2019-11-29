package org.pneditor.petrinet.adapters.imta;

import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.models.hamcyt.ArcPull;

/**
 * AdapterArcPullMultiplicity wraps an arc, and gives to the methods it inherits
 * from AbstractArcPull the right behavior.
 * 
 * Note it assumes that the `arcPull` it receives has the right behavior ; this
 * is not enforced by types, because the interface of our petriNetwork model
 * does not allow it.
 */

public class AdapterArcPullMultiplicity extends AdapterArcPull {

	/**
	 * Create an instance of arc, from a place to a transition, with a multiplicity.
	 * 
	 * @param source       the place
	 * @param destination  the transition
	 * @param multiplicity the multiplicity of the arc
	 * @param arcPull      the wrapped arcPull
	 */
	public AdapterArcPullMultiplicity(AbstractNode source, AbstractNode destination, int multiplicity,
			ArcPull arcPull) {
		super(source, destination, multiplicity, arcPull);
	}

	@Override
	public boolean isRegular() {
		return true;
	}

}
