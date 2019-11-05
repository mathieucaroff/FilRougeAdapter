package tests.org.pneditor.petrinet;

import static org.junit.Assert.*;
import org.pneditor.petrinet.models.initial.Place;
import org.pneditor.petrinet.models.initial.Transition;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NodeTest {

	static Place place;
	static Place placeLabeled;
	static Transition transition;
	static Transition transitionLabeled;

	@BeforeClass
	public static void setUpBeforeClass() {
		place = new Place(1);
		placeLabeled = new Place(3, "Label");
		transition = new Transition(2);
		transitionLabeled = new Transition(4, "Label");
	}

	@Before
	public void setUp() {
		place.setLabel("");
		transition.setLabel("");
		placeLabeled.setLabel("Label");
		transitionLabeled.setLabel("Label");
	}

	@Test
	public void testIdGetter() {
		assertEquals(transition.getId(), 2);
		assertEquals(place.getId(), 1);
		assertEquals(placeLabeled.getId(), 3);
		assertEquals(transitionLabeled.getId(), 4);
	}

	@Test
	public void testLabelGetter() {
		assertEquals(transition.getLabel(), "");
		assertEquals(place.getLabel(), "");
		assertEquals(placeLabeled.getLabel(), "Label");
		assertEquals(transitionLabeled.getLabel(), "Label");
	}

	@Test
	public void testLabelSetter() {
		place.setLabel("null");
		transition.setLabel("1");
		placeLabeled.setLabel("");
		transitionLabeled.setLabel("test");
		assertEquals(transition.getLabel(), "1");
		assertEquals(place.getLabel(), "null");
		assertEquals(placeLabeled.getLabel(), "");
		assertEquals(transitionLabeled.getLabel(), "test");
	}

}
