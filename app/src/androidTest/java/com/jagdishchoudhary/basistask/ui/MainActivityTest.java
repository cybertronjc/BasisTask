package com.jagdishchoudhary.basistask.ui;

import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import com.jagdishchoudhary.basistask.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }

    @Test
    public void testLaunch() {
        View view = mainActivity.findViewById(R.id.viewPager2);
        assertNotNull(view);
    }

    @Test
    public void testTestView() throws Exception{
        onView(withId(R.id.postionText)).check(matches(not(withText(""))));
    }

    @Test
    public void testViewPager() throws Exception{
        Espresso.onView(withId(R.id.viewPager2)).perform(swipeUp());
    }

    @Test
    public void testRestartButton() throws  Exception{
        Espresso.onView(withId(R.id.btnRestart)).perform(click());
    }


    @Test
    public void onBackPressed() {
    }
}