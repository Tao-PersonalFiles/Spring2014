package com.redcarddev.kickshot.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests extends TestSuite {
	
	public static Test suite() {
		
		TestSuite suite = new TestSuite(AllTests.class.getName());
		
		/* Ball Chip Tests */
		suite.addTest(new BoardViewTest("testBallTowardsAway"));
		suite.addTest(new BoardViewTest("testBallTowardsHome"));
		suite.addTest(new BoardViewTest("testBallPosession"));
		
		/* Multiple Dice Tests */
		suite.addTest(new BoardViewTest("testDicePositionAway"));
		
		
		suite.addTestSuite(MainActivityTest.class);
		
		return suite;
	}

}
