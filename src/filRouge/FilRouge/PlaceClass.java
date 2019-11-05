package filRouge.FilRouge;

/**
 * This class manages read and write operations permitted on the value of a
 * place.
 */

class PlaceClass implements Place {

	private int counter;

	/**
	 * 
	 * @param counter the counter is the number of tokens contained in the Place
	 */
	PlaceClass(int counter) {
		super();
		this.counter = counter;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int c) {
		this.counter = c;
	}

	/**
	 * Increase the counter of the place by the given value
	 * 
	 * @param c
	 */
	public void addCounter(int c) {
		this.counter += c;
	}

	public void removeCounter(int c) {
		/*
		 * Note for maintainers:
		 * 
		 * No verification that the counter is finally positive here. See the Design
		 * choice** section of the project's README file.
		 */
		this.counter -= c;
	}

}
