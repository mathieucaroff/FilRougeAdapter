package org.pneditor.petrinet.adapters.imta;

import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.models.hamcyt.ArcPull;

public class AdapterArcPullVacuum extends AdapterArcPull {

	public AdapterArcPullVacuum(AbstractNode source, AbstractNode destination, ArcPull arcPull) {
		super(source, destination, 0, arcPull);
	}

	@Override
	public boolean isReset() {
		return true;
	}

}
