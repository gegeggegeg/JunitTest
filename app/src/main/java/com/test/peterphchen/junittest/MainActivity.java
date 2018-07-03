package com.test.peterphchen.junittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.mozilla.javascript.ScriptableObject;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText output;
    private static String temp = "";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = findViewById(R.id.outputText);
    }

    @Override
    public void onClick(View view) {
        String show;
        switch (view.getId()){
            case R.id.button0:
                temp = temp+"0";
                output.setText(temp);
                break;
            case R.id.button1:
                temp = temp+"1";
                output.setText(temp);
                break;
            case R.id.button2:
                temp = temp+"2";
                output.setText(temp);
                break;
            case R.id.button3:
                temp = temp+"3";
                output.setText(temp);
                break;
            case R.id.button4:
                temp = temp+"4";
                output.setText(temp);
                break;
            case R.id.button5:
                temp = temp+"5";
                output.setText(temp);
                break;
            case R.id.button6:
                temp = temp+"6";
                output.setText(temp);
                break;
            case R.id.button7:
                temp = temp+"7";
                output.setText(temp);
                break;
            case R.id.button8:
                temp = temp+"8";
                output.setText(temp);
                break;
            case R.id.button9:
                temp = temp+"9";
                output.setText(temp);
                break;
            case R.id.buttonPlus:
                show = "+"+temp;
                temp = temp+"+";
                output.setText(show);
                break;
            case R.id.buttonMinus:
                show = "-"+temp;
                temp = temp+"-";
                output.setText(show);
                break;
            case R.id.buttonMultiply:
                show = "*"+temp;
                temp = temp+"*";
                output.setText(show);
                break;
            case R.id.buttonDivide:
                show = "/"+temp;
                temp = temp+"/";
                output.setText(show);
                break;
            case R.id.buttonEqual:
                doCalculation();
                break;
            case R.id.buttonClear:
                temp ="";
                output.setText("");
                break;
        }
    }

    private void doCalculation() {
        org.mozilla.javascript.Context rhino = org.mozilla.javascript.Context.enter();
        rhino.setOptimizationLevel(-1);
        String cal = output.getText().toString();
        try {
            ScriptableObject scope = rhino.initStandardObjects();
            Object result = rhino.evaluateString(scope,cal,"JavaScript",1,null);
            output.setText(String.valueOf(result));
        }catch (Exception e){
            Log.e(TAG, "doCalculation: Error: "+e.getMessage());
            output.setText("");
            Toast.makeText(this, "Error, please type again", Toast.LENGTH_SHORT).show();
        }finally {
            org.mozilla.javascript.Context.exit();
        }
    }
}
