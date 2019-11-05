package filRouge.FilRouge;

/**
 * This class holds the generic operations on ArcPull objects and what can be
 * factorized between all children classes.
 */

abstract class ArcPullAbstract implements ArcPull {
	private PlaceClass place;

	/**
	 * ArcPull(Abstract) pulls from a place to a transition, when the place has
	 * enough counters
	 * 
	 * @param place the place the arc pulls from
	 */
	ArcPullAbstract(PlaceClass place) {
		this.place = place;
	}

	public abstract boolean active();

	/**
	 * Pull counters from the place of the arc
	 */
	public abstract void pullCounter();

	public void setMultiplicity(int m) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Identity test with the place instance held by the arc
	 * 
	 * @param other the place to test against
	 * @return whether the given place is identical to the arc's place
	 */
	public boolean placeEquals(PlaceClass other) {
		return place == other;
	}

	PlaceClass getPlace() {
		return place;
	}
}
