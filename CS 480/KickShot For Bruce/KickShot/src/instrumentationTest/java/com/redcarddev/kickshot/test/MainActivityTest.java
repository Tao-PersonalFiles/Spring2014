package com.redcarddev.kickshot.test;

import android.app.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.pivotallabs.injected.InjectedActivity;
import com.pivotallabs.tracker.RecentActivityActivity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.robolectric.Robolectric.clickOn;
import static org.robolectric.Robolectric.shadowOf;

import android.widget.Button;

import com.redcarddev.kickshot.MainActivity;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    protected MainActivity activity;
    protected Button juniorButton;

    @Before
    public void setUp() {

        activity = Robolectric.buildActivity(MainActivity.class).create().get();
        juniorButton = (Button) activity.findViewById(R.id.junior);
    }

    @Test
    public void shouldHaveAButtonThatSaysJuniorEdition() throws Exception {
        assertThat((String) juniorButton.getText(), equalTo("Junior"));
    }

}
