package com.test.peterphchen.junittest;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
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
    @Rule public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class,true);

    @BeforeClass
    public void initTest(){
        Log.d(TAG, "initTest: Test initialized");
        output = main.getActivity().findViewById(R.id.outputText);
    }

    @Before
    public void preTest(){
        Log.d(TAG, "preTest: pre Test function");
    }

    @Test
    public void JunitTest(){
        Log.d(TAG, "JunitTest: test");
        Assert.assertEquals(true, output.getText());
    }

    @After
    public void postTest(){
        Log.d(TAG, "postTest: post Test function");
    }

    @AfterClass
    public void closeTest(){
        Log.d(TAG, "closeTest: Test closed");
    }
}
