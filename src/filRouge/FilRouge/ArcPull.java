package filRouge.FilRouge;

/**
 * This interface gives a type to ArcPull objects that can be manipulated by.
 * the user, and defines operations available on that object.
 */

public interface ArcPull {
	/**
	 * Set the multiplicity of the arc.
	 * 
	 * @param multiplicity the new value to set
	 * @throws UnsupportedOperationException ArcPullMultiplicity supports setting
	 *                                       multiplicity but ArcPullVacuum and
	 *                                       ArcPullZero do not.
	 */
	void setMultiplicity(int multiplicity) throws UnsupportedOperationException;
}
