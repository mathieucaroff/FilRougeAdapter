package org.pneditor.petrinet.adapters.imta;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.hamcyt.Place;

/**
 * This class of the is part of the adapter, and implements the behaviors
 * required by AbstractPlace.
 * 
 * @author Mathieu CAROFF
 */

public class AdapterPlace extends AbstractPlace {

	private Place place;

	public AdapterPlace(Place place) {
		super("");
		this.place = place;
	}

	/**
	 * Get the wrapped place
	 * 
	 * This allows to call the delete method of the Petri network on that place.
	 * 
	 * @return the wrapped place
	 */
	Place getPlace() { // package-protected
		return place;
	}

	/**
	 * Add one to the number of tokens of the place
	 */
	@Override
	public void addToken() {
		place.setCounter(place.getCounter() + 1);
	}

	/**
	 * Remove one from the number of tokens of the place
	 */
	@Override
	public void removeToken() {
		place.setCounter(place.getCounter() - 1);
	}

	/**
	 * Get the number of tokens of the place
	 * 
	 * @return the number of tokens of the place
	 */
	@Override
	public int getTokens() {
		return place.getCounter();
	}

	/**
	 * Set the number of tokens of the place
	 * 
	 * @param tokens the number of tokens to set
	 */
	@Override
	public void setTokens(int tokens) {
		place.setCounter(tokens);
	}

}
