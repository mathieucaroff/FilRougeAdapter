package org.pneditor.petrinet.adapters.imta;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.PetriNetInterface;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.UnimplementedCaseException;
import org.pneditor.petrinet.models.hamcyt.ArcPull;
import org.pneditor.petrinet.models.hamcyt.ArcPush;
import org.pneditor.petrinet.models.hamcyt.PetriNet;
import org.pneditor.petrinet.models.hamcyt.PetriNetFactory;
import org.pneditor.petrinet.models.hamcyt.Place;
import org.pneditor.petrinet.models.hamcyt.Transition;

/**
 * This class implements the PetriNetInterface, adapting the Petri network model
 * that was created earlier in the FilRouge exercise series.
 * 
 * @author Mathieu CAROFF
 */

public class PetriNetAdapter extends PetriNetInterface {

	private PetriNet petri;

	public PetriNetAdapter() {
		this.petri = new PetriNetFactory().createPetriNet();
	}

	/**
	 * create a place and add it to the Petri network.
	 * 
	 * The place is valid to the Petri network until it is deleted from it using
	 * deletePlace().
	 * 
	 * @return the place instance
	 */
	@Override
	public AbstractPlace addPlace() {
		return new AdapterPlace(petri.createPlace());
	}

	/**
	 * create a transition and add it to the Petri network.
	 * 
	 * The transition is managed by the Petri network until it is deleted from it
	 * using deleteTransition().
	 * 
	 * @return the transition instance
	 */
	@Override
	public AbstractTransition addTransition() {
		return new AdapterTransition(petri.createTransition());
	}

	/**
	 * create a regular arc and add it to the Petri network.
	 * 
	 * When fired, it removes it's multiplicity from the place. It is active when
	 * the place holds enough counters that it can be fired without bringing the
	 * place counter below 0.
	 * 
	 * Places and transitions that are created by the network are managed by the
	 * network until they are removed from it using removePlace() or
	 * removeTransition(). Any other place or transition is not managed by the
	 * network.
	 * 
	 * @param place        The place to link from
	 * @param transition   The transition to link to
	 * @param multiplicity The number of counters removed from the place when fired
	 * @return the AbstractArc instance
	 * @throws UnimplementedCaseException if the source and the destination are both
	 *                                    places or both transitions.
	 * @throws RuntimeException           thrown if the given place or the
	 *                                    transition is not managed by the network.
	 */
	@Override
	public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination) throws UnimplementedCaseException {
		if (source.isPlace() && destination.isPlace()) {
			throw new UnimplementedCaseException("Cannot put arc between two places");
		} else if (!source.isPlace() && !destination.isPlace()) {
			throw new UnimplementedCaseException("Cannot put arc between two transitions");
		}
		// The constructors of the concrete arc adapters expect to be given an instance
		// of the arc that they must wrap, so we do that here, below.
		if (source.isPlace()) {
			int multiplicity = 1;
			ArcPull arcPull = petri.createArcPullMultiplicity((Place) source, (Transition) destination, multiplicity);
			return new AdapterArcPullMultiplicity(source, destination, multiplicity, arcPull);
		} else {
			int multiplicity = 1; // SRP wins over DRY
			ArcPush arcPush = petri.createArcPush((Place) destination, (Transition) source, multiplicity);
			return new AdapterArcPush(source, destination, multiplicity, arcPush);
		}
	}

	/**
	 * create an inhibitory arc and add it to the Petri network
	 * 
	 * It is active when the place holds exactly 0 tokens. It does not pull any
	 * token.
	 * 
	 * @param place      The place to link from
	 * @param transition The transition to link to
	 * @return the AbstractArc instance
	 * @throws RuntimeException thrown if the given place or the transition is not
	 *                          managed by the network.
	 */
	@Override
	public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		// The constructors of the concrete arc adapters expect to be given an instance
		// of the arc that it must wrap.
		ArcPull arcPull = petri.createArcPullZero((Place) place, (Transition) transition);
		return new AdapterArcPullZero(place, transition, arcPull);
	}

	/**
	 * create a reset arc and add it to the Petri network
	 * 
	 * When fired, this reset arc removes all tokens from the place, leaving it
	 * empty. It is active whenever the place has 1 or more tokens on it.
	 * 
	 * @param place      The place to link to
	 * @param transition The transition to link from
	 * @return the AbstractArc instance
	 * @throws RuntimeException thrown if the given place or the transition is not
	 *                          managed by the network.
	 */
	@Override
	public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		// The constructors of the concrete arc adapters expect to be given an instance
		// of the arc that it must wrap.
		ArcPull arcPull = petri.createArcPullVacuum((Place) place, (Transition) transition);
		return new AdapterArcPullVacuum(place, transition, arcPull);
	}

	/**
	 * delete a place from the Petri network
	 * 
	 * @param place The place to delete from the network
	 * @throws RuntimeException thrown if any arc is still connected to it or the
	 *                          place is not managed by the network.
	 */
	@Override
	public void removePlace(AbstractPlace place) {
		AdapterPlace real = (AdapterPlace) place;
		petri.deletePlace(real.getPlace());
	}

	/**
	 * delete a transition from the Petri network
	 * 
	 * @param transition The transition to delete from the network
	 * @throws RuntimeException thrown if any arc is still connected to it or the
	 *                          transition is not managed by the network.
	 */
	@Override
	public void removeTransition(AbstractTransition transition) throws RuntimeException {
		AdapterTransition real = (AdapterTransition) transition;
		petri.deleteTransition(real.getTransition());
	}

	/**
	 * delete an arc from the Petri network
	 * 
	 * @param arc The arc to delete from the network
	 * @throws RuntimeException thrown if the arc is not managed by the network.
	 */
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

	/**
	 * tells whether a transition can be fired
	 * 
	 * @return true if transition can be fired, else false
	 */
	@Override
	public boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException {
		AdapterTransition real = (AdapterTransition) transition;
		return real.getTransition().pullable();
	}

	/**
	 * Fire the transition.
	 * 
	 * This runs all the connected arcs actions, removing tokens from the upstream
	 * places and adding tokens to the downstream ones.
	 * 
	 * @param transition The transition to fire
	 */
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
