package org.pneditor.petrinet.adapters.imta;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;

public abstract class AdapterArc extends AbstractArc {

	private AbstractNode source;
	private AbstractNode destination;
	private int multiplicity;

	protected AdapterArc(AbstractNode source, AbstractNode destination, int multiplicity) {
		this.source = source;
		this.destination = destination;
		this.multiplicity = multiplicity;
	}

	@Override
	public AbstractNode getSource() {
		return source;
	}

	@Override
	public AbstractNode getDestination() {
		return destination;
	}

	@Override
	public boolean isReset() {
		return false;
	}

	@Override
	public boolean isRegular() {
		return false;
	}

	@Override
	public boolean isInhibitory() {
		return false;
	}

	@Override
	public int getMultiplicity() throws ResetArcMultiplicityException {
		return multiplicity;
	}

	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		this.multiplicity = multiplicity;
	}
}
