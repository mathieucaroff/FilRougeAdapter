package org.pneditor.petrinet.adapters.imta;

import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.hamcyt.ArcPull;

public abstract class AdapterArcPull extends AdapterArc {

	private ArcPull arcPull;

	public AdapterArcPull(AbstractNode source, AbstractNode destination, int multiplicity, ArcPull arcPull) {
		super(source, destination, multiplicity);
		this.arcPull = arcPull;
	}

	public ArcPull getArcPull() {
		return arcPull;
	}

	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		super.setMultiplicity(multiplicity);
		try {
			arcPull.setMultiplicity(multiplicity);
		} catch (UnsupportedOperationException e) {
			throw new ResetArcMultiplicityException();
		}
	}

}
