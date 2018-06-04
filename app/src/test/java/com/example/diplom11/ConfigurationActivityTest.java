package com.example.diplom11;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import com.example.diplom11.Application.View.ConfigurationActivity;
import com.example.diplom11.Application.View.FullImageActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)

public class ConfigurationActivityTest {
    private ConfigurationActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(ConfigurationActivity.class).create().resume().get();
    }

    @Test
    public void createConfigActivity() throws Exception {
        assertNotNull(activity);
    }

    @Test
    public void buttonClickShouldStartInfoActivity() throws Exception
    {
        ImageButton button =  activity.findViewById( R.id.imageButton3 );
        button.performClick();
        assertEquals(View.VISIBLE, button.getVisibility());
        Intent intent = Shadows.shadowOf(activity).peekNextStartedActivity();
        assertEquals(FullImageActivity.class.getCanonicalName(), intent.getComponent().getClassName());
    }

    @Test
    public void onBackPressedEmptyStackTest(){
        ConfigurationActivity activity = Robolectric.setupActivity(ConfigurationActivity.class);
        activity.onBackPressed();
        ShadowActivity activityShadow = Shadows.shadowOf(activity);
        assertTrue(activityShadow.isFinishing());
    }

}



