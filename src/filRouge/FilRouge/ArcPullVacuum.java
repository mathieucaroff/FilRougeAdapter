package filRouge.FilRouge;

/**
 * This class defines the way operations are handled for "vacuum" pull arcs.
 *
 * When the transition of a Vacuum Pull Arc is fired, the vacuum pull arc will
 * consume all counters from the place it's linked to.
 */

class ArcPullVacuum extends ArcPullAbstract {
	ArcPullVacuum(PlaceClass place) {
		super(place);
	}

	public boolean active() {
		return getPlace().getCounter() > 0;
	}

	public void pullCounter() {
		getPlace().removeCounter(getPlace().getCounter());
	}

	@Override
	public void setMultiplicity(int m) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
}
