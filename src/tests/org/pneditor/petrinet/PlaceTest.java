package tests.org.pneditor.petrinet;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.pneditor.petrinet.models.initial.Place;


public class PlaceTest {
	
	static Place place;
	static Place placeLabeled;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		place = new Place(1);
		placeLabeled = new Place(2,"Label");
	}
	
	@Before
	public void setUp() {
		place.setTokens(0);
		placeLabeled.setTokens(0);
		placeLabeled.setLabel("Label");
	}
	
	@Test
	public void testPlace() {
		assertTrue(place.isPlace());
		assertTrue(placeLabeled.isPlace());
	}
	
	@Test
	public void testToString() {
		assertEquals("p_1 (0 token(s))", place.toString());
		assertEquals("p_2_Label (0 token(s))", placeLabeled.toString());
		place.setTokens(8);
		placeLabeled.setTokens(14);
		assertEquals("p_1 (8 token(s))", place.toString());
		assertEquals("p_2_Label (14 token(s))", placeLabeled.toString());
		placeLabeled.setLabel("NewLabel");
		assertEquals("p_2_NewLabel (14 token(s))", placeLabeled.toString());
	}
	
	@Test
	public void testGetTokensAfterInitialisation() {
		assertEquals(place.getTokens(),0);
		assertEquals(placeLabeled.getTokens(),0);
	}
	
	@Test
	public void testAddOneToken() {
		assertEquals(place.getTokens(),0);
		place.addToken();
		assertEquals(place.getTokens(),1);
		place.addToken();
		assertEquals(place.getTokens(),2);

	}
	
	@Test
	public void testAddSeveralTokens() {
		assertEquals(place.getTokens(),0);
		place.addTokens(11);
		assertEquals(place.getTokens(),11);
		place.addTokens(12);
		assertEquals(place.getTokens(),23);

	}
	
	@Test
	public void testSetTokens() {
		assertEquals(place.getTokens(),0);
		place.setTokens(2);
		assertEquals(place.getTokens(),2);
		place.setTokens(8);
		assertEquals(place.getTokens(),8);
	}
	
	@Test
	public void testRemoveOneToken() {
		assertEquals(place.getTokens(),0);
		place.setTokens(2);
		assertEquals(place.getTokens(),2);
		place.removeToken();
		assertEquals(place.getTokens(),1);
		place.removeToken();
		assertEquals(place.getTokens(),0);
	}
	
	@Test
	public void testRemoveSeveralTokens() {
		assertEquals(place.getTokens(),0);
		place.setTokens(28);
		assertEquals(place.getTokens(),28);
		place.removeTokens(13);
		assertEquals(place.getTokens(),15);
		place.removeTokens(11);
		assertEquals(place.getTokens(),4);
	}
	
}
