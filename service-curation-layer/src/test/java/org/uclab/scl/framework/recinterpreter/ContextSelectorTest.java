package org.uclab.scl.framework.recinterpreter;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uclab.scl.framework.RecInterpreter.PARecommender.ContextSelector;
import static org.junit.Assert.*;

public class ContextSelectorTest {
	private static ContextSelector contextSelector;
	
	@BeforeClass
	public static void setup(){
		contextSelector = new ContextSelector();
	}
	
	@AfterClass
	public static void windeup(){
		
	}
	
	
	@Test
	public void selectContextTestHomeCase(){
		String receivedContext = "Home,Inactivity,Sunny,Neutral,No";
		
		boolean expected = true;
		boolean actual = contextSelector.selectContext(receivedContext);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void selectContextTestOfficeCase(){
		String receivedContext = "Office,OfficeWork,Sunny,Neutral,No";
		
		boolean expected = true;
		boolean actual = contextSelector.selectContext(receivedContext);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void selectContextTestMealCase(){
		String receivedContext = "Home,HavingMeal,Sunny,Neutral,No";
		
		boolean expected = false;
		boolean actual = contextSelector.selectContext(receivedContext);		
		assertEquals(expected, actual);
	}
	
	@Test
	public void selectContextTestOfficeLocationUnavailableCase(){
		String receivedContext = "UnidentifiedLocation,HavingMeal,Sunny,Neutral,No";
		
		boolean expected = false;
		boolean actual = contextSelector.selectContext(receivedContext);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void selectContextTestOfficeContextUnavailabeCase(){
		String receivedContext = "UnidentifiedLocation,UnidentifiedHLC,UnidentifiedWeather,UnidentifiedEmotion,No";
		
		boolean expected = true;
		boolean actual = contextSelector.selectContext(receivedContext);
		
		assertEquals(expected, actual);
	}	
}
