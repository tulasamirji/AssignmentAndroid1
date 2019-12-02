package com.example.assignmentandroid.view;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.assignmentandroid.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTest = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception {

        mainActivity = mainActivityTest.getActivity();
    }

    @Test
    public void testLaunch() {
        View view = mainActivity.findViewById(R.id.swiperefresh);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {

        mainActivity = null;
    }
}