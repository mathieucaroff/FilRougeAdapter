package org.pneditor.petrinet.adapters.imta;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.PetriNetInterface;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.UnimplementedCaseException;
import org.pneditor.petrinet.models.hamcyt.PetriNet;
import org.pneditor.petrinet.models.hamcyt.PetriNetFactory;
import org.pneditor.petrinet.models.hamcyt.Place;
import org.pneditor.petrinet.models.hamcyt.Transition;

public class PetriNetAdapter extends PetriNetInterface {

	private PetriNet petri;

	public PetriNetAdapter() {
		this.petri = new PetriNetFactory().createPetriNet();
	}

	@Override
	public AbstractPlace addPlace() {
		return new AdapterPlace(petri.createPlace());
	}

	@Override
	public AbstractTransition addTransition() {
		return new AdapterTransition(petri.createTransition());
	}

	@Override
	public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination) throws UnimplementedCaseException {
		if (source.isPlace() && destination.isPlace()) {
			throw new UnimplementedCaseException("Cannot put arc between two places");
		} else if (!source.isPlace() && !destination.isPlace()) {
			throw new UnimplementedCaseException("Cannot put arc between two transitions");
		}
		if (source.isPlace()) {
			int multiplicity = 1;
			return new AdapterArcPullMultiplicity(source, destination, multiplicity,
					petri.createArcPullMultiplicity((Place) source, (Transition) destination, multiplicity));
		} else {
			int multiplicity = 1; // SRP wins over DRY
			return new AdapterArcPush(source, destination, multiplicity,
					petri.createArcPush((Place) destination, (Transition) source, multiplicity));
		}

	}

	@Override
	public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		return new AdapterArcPullZero(place, transition,
				petri.createArcPullZero((Place) place, (Transition) transition));
	}

	@Override
	public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		return new AdapterArcPullVacuum(place, transition,
				petri.createArcPullVacuum((Place) place, (Transition) transition));
	}

	@Override
	public void removePlace(AbstractPlace place) {
		AdapterPlace real = (AdapterPlace) place;
		petri.deletePlace(real.getPlace());
	}

	@Override
	public void removeTransition(AbstractTransition transition) {
		AdapterTransition real = (AdapterTransition) transition;
		petri.deleteTransition(real.getTransition());

	}

	@Override
	public void removeArc(AbstractArc arc) {
		if (arc.getSource().isPlace()) {
			AdapterArcPull real = (AdapterArcPull) arc;
			petri.deleteArcPull(real.getArcPull());
		} else {
			AdapterArcPush real = (AdapterArcPush) arc;
			petri.deleteArcPush(real.getArcPush());
		}

	}

	@Override
	public boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException {
		AdapterTransition real = (AdapterTransition) transition;
		return real.getTransition().pullable();
	}

	@Override
	public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
		AdapterTransition real = (AdapterTransition) transition;
		try {
			real.getTransition().pull();
		} catch (Exception e) {
			throw new ResetArcMultiplicityException();
		}
	}

}
