package filRouge.FilRouge;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class has the following responsibilities:
 *
 * - Allow building a Petri network - Check all user inputs while building or
 * modifying the network. Network objects cannot be reused. - Allow running one
 * step of Petri network. - Allow running a Petri network until no transition is
 * active anymore, or if it never happens, run it forever.
 */
class PetriNetClass implements PetriNet {

	private ArrayList<PlaceClass> placeList;
	private ArrayList<TransitionClass> transitionList;
	private ArrayList<ArcPushClass> arcPushList;
	private ArrayList<ArcPullAbstract> arcPullList;

	PetriNetClass() {
		this(new ArrayList<PlaceClass>(), new ArrayList<TransitionClass>(), new ArrayList<ArcPullAbstract>(),
				new ArrayList<ArcPushClass>());
	}

	public PetriNetClass(ArrayList<PlaceClass> placeList, ArrayList<TransitionClass> transitionList,
			ArrayList<ArcPullAbstract> arcPullList, ArrayList<ArcPushClass> arcPushList) {
		super();
		this.placeList = placeList;
		this.transitionList = transitionList;
		this.arcPullList = arcPullList;
		this.arcPushList = arcPushList;
	}

	public Place createPlace() {
		PlaceClass place = new PlaceClass(0);
		placeList.add(place);
		return place;
	}

	public Transition createTransition() {
		TransitionClass transition = new TransitionClass(new ArrayList<ArcPullAbstract>(),
				new ArrayList<ArcPushClass>());
		transitionList.add(transition);
		return transition;
	}

	public ArcPush createArcPush(Place place, Transition transition, int multiplicity) {
		PlaceClass placeC = placeCast(place);
		TransitionClass transitionC = transitionCast(transition);

		ArcPushClass arc = new ArcPushClass(placeC, multiplicity);
		arcPushList.add(arc);

		transitionC.addArcPush(arc);

		return arc;
	}

	public ArcPull createArcPullMultiplicity(Place p, Transition t, int multiplicity) throws RuntimeException {
		return createArcPull(p, t, (PlaceClass placeC) -> new ArcPullMultiplicity(placeC, multiplicity));
	}

	public ArcPull createArcPullZero(Place p, Transition t) {
		return createArcPull(p, t, (PlaceClass placeC) -> new ArcPullZero(placeC));
	}

	public ArcPull createArcPullVacuum(Place p, Transition t) {
		return createArcPull(p, t, (PlaceClass placeC) -> new ArcPullVacuum(placeC));
	}

	public void deleteArcPush(ArcPush arc) {
		ArcPushClass arcC = arcPushCast(arc);

		for (TransitionClass transition : transitionList) {
			transition.maybeRemoveArcPush(arcC);
		}

		arcPushList.remove(arc);
	}

	public void deleteArcPull(ArcPull arc) {
		ArcPullAbstract arcC = arcPullCast(arc);

		for (TransitionClass transition : transitionList) {
			transition.maybeRemoveArcPull(arcC);
		}

		arcPullList.remove(arc);
	}

	public void deletePlace(Place place) {
		PlaceClass placeC = placeCast(place);
		RuntimeException stillInUse = new RuntimeException("Trying to remove a place that is still used by some arc.");

		for (ArcPushClass arc : arcPushList) {
			if (arc.placeEquals(placeC)) {
				throw stillInUse;
			}
		}

		for (ArcPullAbstract arc : arcPullList) {
			if (arc.placeEquals(placeC)) {
				throw stillInUse;
			}
		}

		placeList.remove(placeC);
	}

	public void deleteTransition(Transition transition) {
		TransitionClass transitionC = transitionCast(transition);

		if (!transitionC.empty()) {
			throw new RuntimeException("Trying to remove a transition that still has arcs attached.");
		}

		transitionList.remove(transitionC);
	}

	public void singleStep() throws RuntimeException {
		ArrayList<TransitionClass> pullable = new ArrayList<TransitionClass>();
		for (TransitionClass transition : transitionList) {
			if (transition.pullable()) {
				pullable.add(transition);
			}
		}

		if (pullable.size() == 0) {
			throw new RuntimeException("Trying to do a step while no transition is pullable.");
		}

		Random rand = new Random();
		TransitionClass transition = pullable.get(rand.nextInt(pullable.size()));
		transition.pull();
	}

	public int loopStep() {
		int counter = 0;
		try {
			while (true) {
				singleStep();
				counter++;
			}
		} catch (RuntimeException e) {
		}

		return counter;
	}

	private interface ArcPullFactory {
		ArcPullAbstract create(PlaceClass place);
	}

	/**
	 * Create an arcPull with the given factory, and add it to the given transition
	 * and the arcPullList
	 * 
	 * @param place
	 * @param transition
	 * @param b
	 * @return the created arc
	 */
	private ArcPull createArcPull(Place place, Transition transition, ArcPullFactory b) {
		PlaceClass placeC = placeCast(place);
		TransitionClass transitionC = transitionCast(transition);

		ArcPullAbstract arc = b.create(placeC);
		arcPullList.add(arc);

		transitionC.addArcPull(arc);

		return arc;
	}

	/**
	 * Check that the given place is valid to the Petri network
	 * 
	 * @param place
	 * @return the given place, downcasted to a PlaceClass
	 */
	private PlaceClass placeCast(Place place) {
		PlaceClass placeC;
		if (!(place instanceof PlaceClass)) {
			throw new RuntimeException("Unhandled place implementation");
		} else {
			if (!placeList.contains(place)) {
				throw new RuntimeException("Unmanaged place");
			} else {
				placeC = (PlaceClass) place;
			}
		}

		return placeC;
	}

	/**
	 * Check that the given transition is valid to the Petri network
	 * 
	 * @param transition the transition to check and cast
	 * @return the given transition, downcasted to a TransitionClass
	 */
	private TransitionClass transitionCast(Transition transition) {
		TransitionClass transitionC;

		if (!(transition instanceof TransitionClass)) {
			throw new RuntimeException("Unhandled transition implementation");
		} else if (!transitionList.contains(transition)) {
			throw new RuntimeException("Unmanaged transition");
		} else {
			transitionC = (TransitionClass) transition;
		}

		return transitionC;
	}

	/**
	 * Check that the given arcPush is valid to the Petri network
	 * 
	 * @param arc the arcPush to check and downcast
	 * @return the given arcPush, downcasted to an ArcPushClass
	 */
	private ArcPushClass arcPushCast(ArcPush arc) {
		ArcPushClass arcC;

		if (!(arc instanceof ArcPushClass)) {
			throw new RuntimeException("Unhandled arcPush implementation");
		} else if (!arcPushList.contains(arc)) {
			throw new RuntimeException("Unmanaged arcPush");
		} else {
			arcC = (ArcPushClass) arc;
		}

		return arcC;
	}

	/**
	 * Check that the given arcPull is valid to the Petri network
	 * 
	 * @param arc the arcPull to check and downcast
	 * @return the given arcPull, downcasted to an ArcPushAbstract
	 */
	private ArcPullAbstract arcPullCast(ArcPull arc) {
		ArcPullAbstract arcC;

		if (!(arc instanceof ArcPullAbstract)) {
			throw new RuntimeException("Unhandled arcPull implementation");
		} else if (!arcPullList.contains(arc)) {
			throw new RuntimeException("Unmanaged arcPull");
		} else {
			arcC = (ArcPullAbstract) arc;
		}

		return arcC;
	}
}
