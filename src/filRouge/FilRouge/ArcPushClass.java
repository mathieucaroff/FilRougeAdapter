package filRouge.FilRouge;

/**
 * This class defines the execution of the ArcPush operations, and implement the
 * methods needed by TransitionClass.
 */

class ArcPushClass implements ArcPush {
	private int multiplicity;
	private PlaceClass place;

	/**
	 * ArcPush adds tokens to a place when fired by a transition
	 * 
	 * @param place        the place the arc pushes to
	 * @param multiplicity the value of the arc
	 */
	ArcPushClass(PlaceClass place, int multiplicity) {
		this.multiplicity = multiplicity;
		this.place = place;
	}

	public void setMultiplicity(int m) throws UnsupportedOperationException {
		this.multiplicity = m;
	}

	/**
	 * Add the counter value of the arc to the place
	 * 
	 * @param other the place to test against
	 * @return whether the given place is identical to the arc's place
	 */
	public void pushCounter() {
		this.place.addCounter(multiplicity);
	}

	/**
	 * Identity test with the place instance held by the arc
	 * 
	 * @param other the place to test against
	 * @return whether the given place is identical to the arc's place
	 */
	public boolean placeEquals(Object other) {
		return place == other;
	}
}
