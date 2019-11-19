package org.pneditor.petrinet.adapters.imta;

import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.hamcyt.ArcPush;

public class AdapterArcPush extends AdapterArc {

	private ArcPush arcPush;

	public AdapterArcPush(AbstractNode source, AbstractNode destination, int multiplicity, ArcPush arcPush) {
		super(source, destination, multiplicity);
		this.arcPush = arcPush;
	}

	public ArcPush getArcPush() {
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
