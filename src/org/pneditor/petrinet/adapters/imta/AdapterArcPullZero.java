package org.pneditor.petrinet.adapters.imta;

import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.models.hamcyt.ArcPull;

public class AdapterArcPullZero extends AdapterArcPull {

	public AdapterArcPullZero(AbstractNode source, AbstractNode destination, ArcPull arcPull) {
		super(source, destination, 0, arcPull);
	}

	@Override
	public boolean isInhibitory() {
		return true;
	}

}
