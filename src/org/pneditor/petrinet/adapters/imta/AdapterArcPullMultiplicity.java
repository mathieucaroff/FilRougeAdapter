package org.pneditor.petrinet.adapters.imta;

import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.models.hamcyt.ArcPull;

public class AdapterArcPullMultiplicity extends AdapterArcPull {

	public AdapterArcPullMultiplicity(AbstractNode source, AbstractNode destination, int multiplicity,
			ArcPull arcPull) {
		super(source, destination, multiplicity, arcPull);
	}

	@Override
	public boolean isRegular() {
		return true;
	}

}
