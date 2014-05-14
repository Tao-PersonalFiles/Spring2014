package com.redcarddev.kickshot.test;

import junit.framework.Assert;

import roboguice.test.RobolectricRoboTestRunner;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Test;

import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class BoardViewTest {

    @Before
    public void setUp() {
    }

    @Test
    public void testSomeNumber() {
        Assert.assertEquals(true,true);
    }

}
