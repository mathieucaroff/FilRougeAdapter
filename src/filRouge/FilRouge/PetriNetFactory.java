package filRouge.FilRouge;

/**
 * This class hides the PetriNetClass from the user, while letting them create
 * and use object implementing the PertiNet interface.
 */

public final class PetriNetFactory {

	/**
	 * Factory to create PetriNet instances
	 * 
	 * @return a brand new PetriNet instance
	 */
	public PetriNet createPetriNet() {
		return new PetriNetClass();
	}

}
