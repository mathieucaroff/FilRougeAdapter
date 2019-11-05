package tests.org.pneditor.petrinet;

import static org.junit.Assert.*;
import org.pneditor.petrinet.models.initial.Transition;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TransitionTest {
	
	static Transition transition;
	static Transition transitionLabeled;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		transition = new Transition(1);
		transitionLabeled = new Transition(2,"Label");
	}
	
	@Before
	public void setUp() {
		transitionLabeled.setLabel("Label");
	}
	
	@Test
	public void testPlace() {
		assertFalse(transition.isPlace());
		assertFalse(transitionLabeled.isPlace());
	}
	
	@Test
	public void testToString() {
		assertEquals("t_1", transition.toString());
		assertEquals("t_2_Label", transitionLabeled.toString());
	}
}

