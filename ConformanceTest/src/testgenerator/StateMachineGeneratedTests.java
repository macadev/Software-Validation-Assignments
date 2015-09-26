package testgenerator;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ca.mcgill.ecse429.conformancetest.statemodel.*;
import ca.mcgill.ecse429.conformancetest.ccoinbox.*;

public class StateMachineGeneratedTests {

	private CCoinBox sm;

	@Before
	public void setUp() {
		sm = new CCoinBox();
	}

	@After
	public void tearDown() {

	}

	@Test
	public void conformanceTest1() {
		assertEquals("Verify current state matches FSM","empty", sm.getStateFullName());
		sm.returnQtrs();
		assertEquals("Verify current state matches FSM","empty", sm.getStateFullName());
	}


	@Test
	public void conformanceTest2() {
		assertEquals("Verify current state matches FSM","empty", sm.getStateFullName());
		sm.reset();
		assertEquals("Verify current state matches FSM","empty", sm.getStateFullName());
	}


	@Test
	public void conformanceTest3() {
		assertEquals("Verify current state matches FSM","empty", sm.getStateFullName());
		sm.addQtr();
		assertEquals("Verify current state matches FSM","notAllowed", sm.getStateFullName());
		sm.returnQtrs();
		assertEquals("Verify current state matches FSM","empty", sm.getStateFullName());
	}


	@Test
	public void conformanceTest4() {
		assertEquals("Verify current state matches FSM","empty", sm.getStateFullName());
		sm.addQtr();
		assertEquals("Verify current state matches FSM","notAllowed", sm.getStateFullName());
		sm.reset();
		assertEquals("Verify current state matches FSM","empty", sm.getStateFullName());
	}


	@Test
	public void conformanceTest5() {
		assertEquals("Verify current state matches FSM","empty", sm.getStateFullName());
		sm.addQtr();
		assertEquals("Verify current state matches FSM","notAllowed", sm.getStateFullName());
		sm.addQtr();
		assertEquals("Verify current state matches FSM","allowed", sm.getStateFullName());
		sm.returnQtrs();
		assertEquals("Verify current state matches FSM","empty", sm.getStateFullName());
	}


	@Test
	public void conformanceTest6() {
		assertEquals("Verify current state matches FSM","empty", sm.getStateFullName());
		sm.addQtr();
		assertEquals("Verify current state matches FSM","notAllowed", sm.getStateFullName());
		sm.addQtr();
		assertEquals("Verify current state matches FSM","allowed", sm.getStateFullName());
		sm.reset();
		assertEquals("Verify current state matches FSM","empty", sm.getStateFullName());
	}


	@Test
	public void conformanceTest7() {
		assertEquals("Verify current state matches FSM","empty", sm.getStateFullName());
		sm.addQtr();
		assertEquals("Verify current state matches FSM","notAllowed", sm.getStateFullName());
		sm.addQtr();
		assertEquals("Verify current state matches FSM","allowed", sm.getStateFullName());
		sm.addQtr();
		assertEquals("Verify current state matches FSM","allowed", sm.getStateFullName());
	}


	@Test
	public void conformanceTest8() {
		assertEquals("Verify current state matches FSM","empty", sm.getStateFullName());
		sm.addQtr();
		assertEquals("Verify current state matches FSM","notAllowed", sm.getStateFullName());
		sm.addQtr();
		assertEquals("Verify current state matches FSM","allowed", sm.getStateFullName());
		sm.vend();
		assertEquals("Verify current state matches FSM","empty", sm.getStateFullName());
	}


	@Test
	public void conformanceTest9() {
		assertEquals("Verify current state matches FSM","empty", sm.getStateFullName());
		sm.addQtr();
		assertEquals("Verify current state matches FSM","notAllowed", sm.getStateFullName());
		sm.addQtr();
		assertEquals("Verify current state matches FSM","allowed", sm.getStateFullName());
		sm.vend();
		assertEquals("Verify current state matches FSM","notAllowed", sm.getStateFullName());
	}


	@Test
	public void conformanceTest10() {
		assertEquals("Verify current state matches FSM","empty", sm.getStateFullName());
		sm.addQtr();
		assertEquals("Verify current state matches FSM","notAllowed", sm.getStateFullName());
		sm.addQtr();
		assertEquals("Verify current state matches FSM","allowed", sm.getStateFullName());
		sm.vend();
		assertEquals("Verify current state matches FSM","allowed", sm.getStateFullName());
	}


}
