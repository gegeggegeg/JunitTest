package com.test.peterphchen.junittest;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {
    MainActivity mainActivity = new MainActivity();

    @Test
    public void testMultiplyCalculation(){
        for(Double i = 0.0; i <10; i++) {
            for (Double j =0.0; j <10; j++) {
                String testInput = i+"*"+j;
                Double result = (Double) mainActivity.doCalculation(testInput);
                System.out.println("Input String: "+testInput+"\n"+"Output result: "+result+"\n");
                assertEquals("The result is not matched", i*j, result, 0.0);
            }
        }
    }

    @Test
    public void testAddCalculation(){
        for(Double i = 0.0; i <10; i++) {
            for (Double j =0.0; j <10; j++) {
                String testInput = i+"+"+j;
                Double result = (Double) mainActivity.doCalculation(testInput);
                System.out.println("Input String: "+testInput+"\n"+"Output result: "+result+"\n");
                assertEquals("The result is not matched", i+j, result, 0.0);
            }
        }
    }

    @Test
    public void testSubtractCalculation(){
        for(Double i = 0.0; i <10; i++) {
            for (Double j =0.0; j <10; j++) {
                if(i>=j) {
                    String testInput = i+"-"+j;
                    Double result = (Double) mainActivity.doCalculation(testInput);
                    System.out.println("Input String: "+testInput+"\n"+"Output result: "+result+"\n");
                    assertEquals("The result is not matched", i-j, result, 0.0);
                }
            }
        }
    }

    @Test
    public void testDivideCalculation(){
        for(Double i = 0.0; i <10; i++) {
            for (Double j =1.0; j <10; j++) {
                if(i>=j) {
                    String testInput = i+"/"+j;
                    Double result = (Double) mainActivity.doCalculation(testInput);
                    System.out.println("Input String: "+testInput+"\n"+"Output result: "+result+"\n");
                    assertEquals("The result is not matched", i/j, result, 0.0);
                }
            }
        }
    }
}