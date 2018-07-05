package com.test.peterphchen.junittest;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testCalculatorUITest(){
        Espresso.onView(ViewMatchers.withId(R.id.button1)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.button5)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.buttonPlus)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.button3)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.button2)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.buttonEqual)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.outputText)).check(matches(withText("47.0")));
    }

}
