package com.test.peterphchen.junittest;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

import org.mozilla.javascript.ScriptableObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText output;
    private static String temp = "";
    private static final String TAG = "MainActivity";
    private DatabaseHelper dbhelper;
    private SQLiteDatabase db;
    private static Integer number = 1;
    private DrawerLayout mDrawerLayout;
    private FirebaseJobDispatcher dispatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = findViewById(R.id.outputText);
        dbhelper = new DatabaseHelper(getApplicationContext());
        db = dbhelper.getWritableDatabase();
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.cake:
                        Toast.makeText(MainActivity.this, "Happy birthday", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.music:
                        Toast.makeText(MainActivity.this, "Sounds good", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.eggdroid:
                        Toast.makeText(MainActivity.this, "It is so cuuuute!", Toast.LENGTH_SHORT).show();
                        break;
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        final Job testJob = JobScheduling();
        Switch jobSwitch = findViewById(R.id.jobSwitch);
        final SharedPreferences setting = getPreferences(0);
        jobSwitch.setChecked(setting.getBoolean("switch",false));
        jobSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    dispatcher.mustSchedule(testJob);
                    Toast.makeText(MainActivity.this, "Scheduled job enabled", Toast.LENGTH_SHORT).show();
                }else{
                    dispatcher.cancel("test-job");
                    Toast.makeText(MainActivity.this, "Scheduled job disabled", Toast.LENGTH_SHORT).show();
                }
                SharedPreferences.Editor editor =setting.edit();
                editor.putBoolean("switch",b);
                editor.commit();
            }
        });
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
                Object result = doCalculation(temp);
                String equation = temp + " = "+result;
                ContentValues values = new ContentValues();
                values.put("equation",equation);
                values.put("number",number);
                number++;
                db.insert("result",null,values);
                output.setText(String.valueOf(result));
                break;
            case R.id.buttonClear:
                temp ="";
                output.setText("");
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.list:
                Intent intent = new Intent(MainActivity.this,RecycleActivity.class);
                startActivity(intent);
                return true;
            case R.id.about:
                Toast.makeText(this, "THis app is for test only", Toast.LENGTH_SHORT).show();
                return  true;
            case R.id.delete:
                db.execSQL("DROP TABLE IF EXISTS " + DatabaseHelper.TABLE);
                dbhelper.onCreate(db);
                Toast.makeText(this, "Table deleted", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public Object doCalculation(String input ) {
        org.mozilla.javascript.Context rhino = org.mozilla.javascript.Context.enter();
        rhino.setOptimizationLevel(-1);
        Object result = null;
        try {
            ScriptableObject scope = rhino.initStandardObjects();
            result = rhino.evaluateString(scope,input,"JavaScript",1,null);
        }catch (Exception e){
            Log.e(TAG, "doCalculation: Error: "+e.getMessage());
            output.setText("");
            Toast.makeText(this, "Error, please type again", Toast.LENGTH_SHORT).show();
        }finally {
            org.mozilla.javascript.Context.exit();
        }
        return result;
    }

    private Job JobScheduling(){
        Driver driver = new GooglePlayDriver(this);
        dispatcher = new FirebaseJobDispatcher(driver);
        Job myJob = dispatcher.newJobBuilder()
                .setService(MyjobService.class)
                .setTag("test-job")
                .setRecurring(false)
                .setLifetime(Lifetime.UNTIL_NEXT_BOOT)
                .setTrigger(Trigger.executionWindow(0,15))
                .setReplaceCurrent(true)
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                .setConstraints(Constraint.DEVICE_CHARGING)
                .build();
        return myJob;
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

}
