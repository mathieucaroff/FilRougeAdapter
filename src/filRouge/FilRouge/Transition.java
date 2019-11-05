package filRouge.FilRouge;

/**
 * This interface defines the operations accessible a user can use on a
 * Transition.
 */

public interface Transition {
	/**
	 * @return whether the transition can be pulled (fired), i.e. whether the
	 *         transition is active
	 */
	boolean pullable();

	/**
	 * Fire the transition, by running all connected arcs actions
	 */
	void pull();
}
