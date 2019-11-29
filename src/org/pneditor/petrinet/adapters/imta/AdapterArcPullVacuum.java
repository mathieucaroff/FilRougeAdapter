package org.pneditor.petrinet.adapters.imta;

import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.hamcyt.ArcPull;

/**
 * This class adds a few behaviors that are specific to the reset arcs.
 */
public class AdapterArcPullVacuum extends AdapterArcPull {

	/**
	 * Create an instance of arc, from a place to a transition, with a multiplicity.
	 * 
	 * @param source       the place
	 * @param destination  the transition
	 * @param multiplicity the multiplicity of the arc
	 * @param arcPull      the wrapped arcPull
	 */
	public AdapterArcPullVacuum(AbstractNode source, AbstractNode destination, ArcPull arcPull) {
		// No need to call the ArcPullVacuum constructor or anything, this is done by
		// the parent class
		super(source, destination, 0, arcPull);
	}

	@Override
	public int getMultiplicity() throws ResetArcMultiplicityException {
		throw new ResetArcMultiplicityException();
	}

	@Override
	public boolean isReset() {
		return true;
	}

}
