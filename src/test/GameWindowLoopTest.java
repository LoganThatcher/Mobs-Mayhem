package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import javafx.scene.shape.Circle;


import org.junit.Test;
import logic.GameWindow;

public class GameWindowLoopTest {

	@Test
	public void testBirthCopsNone() {
		ArrayList<Circle> expected = new ArrayList<Circle>();
		ArrayList<Circle> actual = GameWindow.giveBirthToCops(0);
		
		assertEquals(expected, actual);
		
	}

}
