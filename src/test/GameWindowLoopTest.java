package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import javafx.scene.shape.Circle;
import org.junit.Test;
import logic.Cops;

public class GameWindowLoopTest {

	@Test
	public void testBirthCopsNone() {
		ArrayList<Circle> expected = new ArrayList<Circle>();
		ArrayList<Circle> actual = Cops.giveBirthToCops(0);
		
		assertEquals(expected, actual);
	}
	
	@Test 
	public void testBirthCopsOne() {
		ArrayList<Circle> expected = new ArrayList<Circle>();
		expected.add(new Circle());
		ArrayList<Circle> actual = Cops.giveBirthToCops(1);
		
		assertEquals(expected.size(),actual.size());
	}
	
	@Test
	public void testBirthCopsTypical() {
		ArrayList<Circle> expected = new ArrayList<Circle>();
		expected.add(new Circle());
		expected.add(new Circle());
		expected.add(new Circle());
		ArrayList<Circle> actual = Cops.giveBirthToCops(3);
		
		assertEquals(expected.size(),actual.size());
	}

}
