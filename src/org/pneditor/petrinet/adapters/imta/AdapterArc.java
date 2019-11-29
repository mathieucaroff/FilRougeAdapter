package org.pneditor.petrinet.adapters.imta;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;

/**
 * This class of the is part of the adapter, and implements the behaviors common
 * to all arcs.
 * 
 * @author Mathieu CAROFF
 */

public abstract class AdapterArc extends AbstractArc {

	private AbstractNode source;
	private AbstractNode destination;
	private int multiplicity;

	protected AdapterArc(AbstractNode source, AbstractNode destination, int multiplicity) {
		// Constructor called by the child classes
		this.source = source;
		this.destination = destination;
		this.multiplicity = multiplicity;
	}

	/**
	 * Get the source node of the arc
	 * 
	 * @return the source node of the arc
	 */
	@Override
	public AbstractNode getSource() {
		return source;
	}

	/**
	 * Get the destination node of the arc
	 * 
	 * @return the destination node of the arc
	 */
	@Override
	public AbstractNode getDestination() {
		return destination;
	}

	/**
	 * Tells whether the arc is a reset arc.
	 * 
	 * @return true if the arc is a reset arc else false
	 */
	@Override
	public boolean isReset() {
		return false;
	}

	/**
	 * Tells whether the arc is a regular arc.
	 * 
	 * @return true if the arc is a regular arc else false
	 */
	@Override
	public boolean isRegular() {
		return false;
	}

	/**
	 * Tells whether the arc is an inhibitory arc.
	 * 
	 * @return true if the arc is an inhibitory arc else false
	 */
	@Override
	public boolean isInhibitory() {
		return false;
	}

	/**
	 * Get the multiplicity of the arc.
	 * 
	 * @throws ResetArcMultiplicityException ArcPull may throw if setting the arc
	 *                                       multiplicity is not supported by its
	 *                                       kind. For instance, arcs that return
	 *                                       true when asked if ifReset() or if
	 *                                       isInhibitory() will throw when
	 *                                       setMultiplicty is called.
	 */
	@Override
	public int getMultiplicity() throws ResetArcMultiplicityException {
		return multiplicity;
	}

	/**
	 * Set the multiplicity of the arc.
	 * 
	 * @param multiplicity the new value to set
	 * @throws ResetArcMultiplicityException ArcPull may throw if setting the arc
	 *                                       multiplicity is not supported by its
	 *                                       kind. For instance, arcs that return
	 *                                       true when asked if ifReset() or if
	 *                                       isInhibitory() will throw when
	 *                                       setMultiplicty is called.
	 */
	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		this.multiplicity = multiplicity;
	}
}
