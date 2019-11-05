package filRouge.FilRouge;

/**
 * This interface defines the operations accessible a user can use on a Place.
 */

/**
 * A place is an element of the Petri network which can hold counters.
 */
public interface Place {

	/**
	 * Get the value of the counter of the place
	 * 
	 * @return the value of the counter
	 */
	int getCounter();

	/**
	 * Set the counter of the place
	 * 
	 * @param counter the new value
	 */
	void setCounter(int counter);

}
