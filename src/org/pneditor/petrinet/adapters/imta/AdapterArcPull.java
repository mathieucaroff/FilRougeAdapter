package org.pneditor.petrinet.adapters.imta;

import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.hamcyt.ArcPull;

/**
 * This class of the is part of the adapter, and implements the behaviors common
 * to the arcs going from a place to a transition.
 */

public abstract class AdapterArcPull extends AdapterArc {

	private ArcPull arcPull;

	/**
	 * Create an instance of arc, from a place to a transition.
	 * 
	 * @param source       the place
	 * @param destination  the transition
	 * @param multiplicity the multiplicity of the arc, or any value if it is
	 *                     irrelevant.
	 * @param arcPull      the underlying, wrapped arcPull
	 */
	public AdapterArcPull(AbstractNode source, AbstractNode destination, int multiplicity, ArcPull arcPull) {
		super(source, destination, multiplicity);
		this.arcPull = arcPull;
	}

	/**
	 * Get the wrapped arcPull
	 * 
	 * This allows to call the delete method of the Petri network on that arcPull.
	 * 
	 * @return the wrapped arcPull
	 */
	ArcPull getArcPull() { // package-protected, rather than public
		return arcPull;
	}

	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		// Both the adapter and the model code need to know the multiplicity of the arc,
		// so this setMultiplicit() performs a dispatch
		super.setMultiplicity(multiplicity);
		try {
			arcPull.setMultiplicity(multiplicity);
		} catch (UnsupportedOperationException e) {
			throw new ResetArcMultiplicityException();
		}
	}

}
