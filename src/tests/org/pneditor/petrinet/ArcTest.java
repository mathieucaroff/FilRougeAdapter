package tests.org.pneditor.petrinet;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.pneditor.petrinet.models.initial.Arc;
import org.pneditor.petrinet.models.initial.InhibitoryArc;
import org.pneditor.petrinet.models.initial.Place;
import org.pneditor.petrinet.models.initial.RegularArc;
import org.pneditor.petrinet.models.initial.ResetArc;
import org.pneditor.petrinet.models.initial.Transition;

public class ArcTest {

	static Place place;
	static Place placeBis;
	static Transition transition;
	static Transition transitionBis;
	static Arc arc;
	static Arc arcBis;
	static RegularArc rarc;
	static InhibitoryArc iarc;
	static ResetArc zarc;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@BeforeClass
	public static void setUpBeforeClass() {
		place = new Place(1);
		placeBis = new Place(2);
		transition = new Transition(3);
		transitionBis = new Transition(4);
	}
	
	@Before
	public void setUp() {
	}
	
	@Test
	public void testRegularArcCreation() throws Exception {
		arc = new RegularArc(place, transition);
		arc = new RegularArc(placeBis,transitionBis);
		arc = new RegularArc(place, transitionBis);
		arc = new RegularArc(placeBis, transition);
	}
	
	@Test
	public void testRegularArcCreationFailingBecauseArcComposedOfTwoPlaces() throws Exception {
		this.thrown.expect(IllegalArgumentException.class);
		this.thrown.expectMessage("Source and destination must consist of a Transition and a Place");
		
		arcBis = new RegularArc(place,placeBis);
	}
	
	@Test
	public void testRegularArcCreationFailingBecauseArcComposedOfTwoTransitions() throws Exception {
		this.thrown.expect(IllegalArgumentException.class);
		this.thrown.expectMessage("Source and destination must consist of a Transition and a Place");
		
		arcBis = new RegularArc(transition,transitionBis);
	}
	
	@Test
	public void testResetAndInhibitoryArcCreation() throws Exception {
		arc = new InhibitoryArc(place, transition);
		arc = new ResetArc(placeBis,transitionBis);
	}


	@Test
	public void testGetterNodesAndIsSourceAPlace() {
		arc = new RegularArc(place, transition);
		assertEquals(arc.getSource(),place);
		assertEquals(arc.getDestination(),transition);
		assertEquals(arc.getTransition(),transition);
		assertEquals(arc.getPlace(),place);
		assertTrue(arc.isSourceAPlace());
		
		arc = new RegularArc(transition, place);
		assertEquals(arc.getSource(),transition);
		assertEquals(arc.getDestination(),place);
		assertEquals(arc.getTransition(),transition);
		assertEquals(arc.getPlace(),place);
		assertFalse(arc.isSourceAPlace());

	}
	
	@Test
	public void testChangeMultiplicity() {
		rarc = new RegularArc(place, transition);
		rarc.setMultiplicity(5);
		assertEquals(rarc.getMultiplicity(),5);
	}
	
	@Test
	public void testChangeMultiplicityRaisingException() throws Exception {
		this.thrown.expect(IllegalArgumentException.class);
		this.thrown.expectMessage("Arc multiplicity must be strictly positive");
		
		rarc = new RegularArc(place, transition);
		rarc.setMultiplicity(-3);
	}
	
	@Test
	public void testToString() {
		iarc = new InhibitoryArc(place, transition);
		iarc.setMultiplicity(2);
		assertEquals(iarc.toString(),"1o->3 (Multiplicity 2)");
		
		rarc = new RegularArc(transition, placeBis);
		assertEquals(rarc.toString(),"3-->2 (Multiplicity 1)");
		
		zarc = new ResetArc(place, transitionBis);
		assertEquals(zarc.toString(),"1->>4");
	}
}
