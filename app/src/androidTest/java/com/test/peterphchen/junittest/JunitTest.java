package com.test.peterphchen.junittest;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class JunitTest {

    private static final String TAG = "JunitTest";
    private EditText output;
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private Button buttonFour;
    private Button buttonFive;
    private Button buttonSix;

    @Rule public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class,true);

    private static View background;
    private static EditText editText;

    @BeforeClass
    public static void initTest(){
        Log.d(TAG, "initTest: Test initialized");
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                LayoutInflater inflater = LayoutInflater.from(InstrumentationRegistry.getTargetContext());
                background = inflater.inflate(R.layout.activity_main,null);
                background.measure(800,400);
                background.layout(0,0,800,400);
                editText = background.findViewById(R.id.outputText);
            }
        });
    }

    @Before
    public void preTest(){
        Log.d(TAG, "preTest: pre Test function");
        output = main.getActivity().findViewById(R.id.outputText);
        buttonOne = main.getActivity().findViewById(R.id.button1);
        buttonTwo = main.getActivity().findViewById(R.id.button2);
        buttonThree = main.getActivity().findViewById(R.id.button3);
        buttonFour = main.getActivity().findViewById(R.id.button4);
        buttonFive = main.getActivity().findViewById(R.id.button5);
        buttonSix = main.getActivity().findViewById(R.id.button6);

    }

    @Test
    public void testJunitTest(){
        Log.d(TAG, "JunitTest: test");
        Assert.assertNotNull("This object is null",output);
        Assert.assertNotNull("This object is null",buttonOne);
        Assert.assertNotNull("This object is null",buttonTwo);
        Assert.assertNotNull("This object is null",buttonThree);
        Assert.assertNotNull("This object is null",buttonFour);
        Assert.assertNotNull("This object is null",buttonFive);
        Assert.assertNotNull("This object is null",buttonSix);
    }

    @Test
    public void ContextTest(){
        Assert.assertEquals("Error, not equal","",editText.getText());
    }

    @After
    public void postTest(){
        Log.d(TAG, "postTest: post Test function");
    }

    @AfterClass
    public static void closeTest(){
        Log.d(TAG, "closeTest: Test closed");
    }
}
