package filRouge.FilRouge;

/**
 * This interface defines the way for the user to build a Petri network from
 * places, transitions and arcs of various kinds. It also allows to run one or
 * several steps of the Petri network.
 */

public interface PetriNet {
	/**
	 * create a place and add it to the Petri network. The place is valid to the
	 * petriNet instance until it is deleted from the network using deletePlace().
	 * 
	 * @return the place instance
	 */
	Place createPlace();

	/**
	 * create a transition and add it to the Petri network. The transition is valid
	 * to the petriNet instance until it is deleted from the network using
	 * deleteTransition().
	 * 
	 * @return the transition instance
	 */
	Transition createTransition();

	/**
	 * create an arcPull - an arc which removes counters from a place when fired.
	 * When fired, it removes it's multiplicity from the place. It is active when
	 * the place holds enough counters that it can be fired without bringing the
	 * place counter below 0.
	 * 
	 * All arcPulls created using a createArcPull*() method of the petriNet instance
	 * are valid to the instance until deleted using deleteArcPull().
	 * 
	 * Note: The place and the transition must be valid. See createPlace and
	 * createTransition. Otherwise, the method will throw a RuntimeException.
	 * 
	 * @param place        The place to link from
	 * @param transition   The transition to link to
	 * @param multiplicity The number of counters removed from the place when fired
	 * @return the arcPull instance
	 * @throws RuntimeException thrown if the given place or the transition is not
	 *                          valid
	 */
	ArcPull createArcPullMultiplicity(Place place, Transition transition, int multiplicity);

	/**
	 * create an arcPullZero - It is active when the place holds exactly 0 counters.
	 * 
	 * Note: The place and the transition must be valid. See createPlace and
	 * createTransition. Otherwise, the method will throw a RuntimeException.
	 * 
	 * @param place      The place to link from
	 * @param transition The transition to link to
	 * @return the arcPull instance
	 * @throws RuntimeException thrown if the given place or the transition is not
	 *                          valid
	 */
	ArcPull createArcPullZero(Place place, Transition transition);

	/**
	 * create an arcPullVacuum - an arc which removes counters from a place when
	 * fired. When fired, it removes all counters from the place, leaving it empty.
	 * It is active whenever the place has 1 or more counters on it.
	 * 
	 * Note: The place and the transition must be valid. See createPlace and
	 * createTransition. Otherwise, the method will throw a RuntimeException.
	 * 
	 * @param place      The place to link from
	 * @param transition The transition to link to
	 * @return the arcPull instance
	 * @throws RuntimeException thrown if the given place or the transition is not
	 *                          valid
	 */
	ArcPull createArcPullVacuum(Place place, Transition transition);

	/**
	 * create an arcPush - an arc which adds counters to a place when it's
	 * transition is fired. When fired, it adds it's multiplicity to the place
	 * counter.
	 * 
	 * Note: The place and the transition must be valid. See createPlace and
	 * createTransition. Otherwise, the method will throw a RuntimeException.
	 * 
	 * @param place        The place to link to
	 * @param transition   The transition to link from
	 * @param multiplicity The number of counters added to the place when fired
	 * @return the arcPush instance
	 * @throws RuntimeException thrown if the given place or the transition is not
	 *                          valid
	 */
	ArcPush createArcPush(Place place, Transition transition, int multiplicity);

	/**
	 * delete an arcPull from the Petri network
	 * 
	 * ArcPushs created using a createArcPush() are valid to the instance until
	 * deleted using deleteArcPull().
	 * 
	 * @param arcpull The arcPull instance to remove from the Petri network
	 * @throws RuntimeException thrown if the arcPull is not valid - see
	 *                          createArcPullMultiplicity.
	 */
	void deleteArcPull(ArcPull arcpull);

	/**
	 * delete an arcPush from the Petri network
	 * 
	 * @param arcpull The arcPush instance to remove from the Petri network
	 * @throws RuntimeException thrown if the arcPull is not valid - see
	 *                          createArcPush.
	 */
	void deleteArcPush(ArcPush arcpush);

	/**
	 * delete a place from the Petri network
	 * 
	 * @param place The place to delete from the network
	 * @throws RuntimeException thrown if any arc is still connected to it or if the
	 *                          arcPull is not valid - see createPlace.
	 */
	void deletePlace(Place place);

	/**
	 * delete a transition from the Petri network
	 * 
	 * @param transition The transition to delete from the network
	 * @throws RuntimeException thrown if any arc is still connected to it or if the
	 *                          transition is not valid - see createTransition.
	 */
	void deleteTransition(Transition transition);

	/**
	 * run a single step of simulation, by firing an active transition, or throw a
	 * RuntimeException.
	 * 
	 * @throws RuntimeException if no transition of the Petri network are active
	 */
	void singleStep() throws RuntimeException;

	/**
	 * run the simulation until no more transition is active, or forever
	 * 
	 * @return the number of steps that were successfully executed
	 */
	int loopStep();
}
