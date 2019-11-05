package filRouge.FilRouge;

/**
 * This interface gives a type to ArcPush objects that can be manipulated by.
 * the user, and defines operations available on that object.
 */

public interface ArcPush {
	/**
	 * Set the multiplicity of the arc.
	 * 
	 * @param multiplicity the new value to set
	 * @throws UnsupportedOperationException ArcPush supports setting multiplicity
	 *                                       and will not throw
	 */
	void setMultiplicity(int multiplicity) throws UnsupportedOperationException;
}
