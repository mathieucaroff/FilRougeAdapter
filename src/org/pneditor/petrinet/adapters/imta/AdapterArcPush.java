package org.pneditor.petrinet.adapters.imta;

import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.hamcyt.ArcPush;

/**
 * This class of the is part of the adapter, and implements the behaviors of
 * arcs going from a transition to a place.
 */

public class AdapterArcPush extends AdapterArc {

	private ArcPush arcPush;

	/**
	 * Create an instance of arc, from a transition to a place, with a multiplicity.
	 * 
	 * @param source       the transition
	 * @param destination  the place
	 * @param multiplicity the multiplicity of the arc
	 * @param arcPush      the wrapped arcPush
	 */
	public AdapterArcPush(AbstractNode source, AbstractNode destination, int multiplicity, ArcPush arcPush) {
		super(source, destination, multiplicity);
		this.arcPush = arcPush;
	}

	/**
	 * Get the wrapped arcPush
	 * 
	 * This allows to call the delete method of the Petri network on that arcPush.
	 * 
	 * @return the wrapped arcPush
	 */
	ArcPush getArcPush() { // package-protected, rather than public
		return arcPush;
	}

	@Override
	public boolean isRegular() {
		return true;
	}

	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		super.setMultiplicity(multiplicity);
		arcPush.setMultiplicity(multiplicity);
	}

}
