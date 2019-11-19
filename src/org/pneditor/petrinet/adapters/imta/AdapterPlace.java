package org.pneditor.petrinet.adapters.imta;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.hamcyt.Place;

public class AdapterPlace extends AbstractPlace {

	private Place place;

	public AdapterPlace(Place place) {
		super("");
		this.place = place;
	}

	public Place getPlace() {
		return place;
	}

	@Override
	public void addToken() {
		place.setCounter(place.getCounter() + 1);
	}

	@Override
	public void removeToken() {
		place.setCounter(place.getCounter() - 1);

	}

	@Override
	public int getTokens() {
		return place.getCounter();
	}

	@Override
	public void setTokens(int tokens) {
		place.setCounter(tokens);
	}

}
