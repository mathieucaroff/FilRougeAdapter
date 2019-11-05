package filRouge.FilRouge;

/**
 * This class defines the way operations are handled for "zero" pull arcs
 *
 * An arc pull Zero does not consume any counter when fired, but is active only
 * when its place has zero counters on it.
 */

class ArcPullZero extends ArcPullMultiplicity {
	ArcPullZero(PlaceClass place) {
		super(place, 0);
	}

	public boolean active() {
		return getPlace().getCounter() == getMultiplicity();
	}

	@Override
	public void setMultiplicity(int m) {
		throw new UnsupportedOperationException();
	}
}
