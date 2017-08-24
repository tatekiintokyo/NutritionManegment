package jp.techacademy.chiku.takahiro.nutritionmanegment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CalendarView;
import android.widget.TextView;

import com.github.mikephil.charting.formatter.IFillFormatter;

import java.util.ArrayList;

import static jp.techacademy.chiku.takahiro.nutritionmanegment.MainActivity.mProteinAmount2;
import static jp.techacademy.chiku.takahiro.nutritionmanegment.MainActivity.mProteinSum;
import static jp.techacademy.chiku.takahiro.nutritionmanegment.R.id.LessProtein;
import static jp.techacademy.chiku.takahiro.nutritionmanegment.R.id.MuchProtein;

public class SummaryActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    String Protein;
    double mVolume;
    TextView ProteinLess,ProteinMuch;
    private ArrayAdapter<CharSequence> adapter;
    private AutoCompleteTextView mMealsResearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setTitle("サマリー");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ジャンルを選択していない場合（mGenre == 0）はエラーを表示するだけ
                Intent intent = new Intent(getApplicationContext(), InputActivity.class);
                //intent.putExtra();
                startActivity(intent);
            }
        });

        summaryviewLess("Protein",mProteinSum,mProteinAmount2,ProteinLess,LessProtein);
        summaryviewMuch("Protein",mProteinSum,mProteinAmount2,ProteinMuch,MuchProtein);

        TextView dateText = (TextView)findViewById(R.id.today_text1) ;
        InputActivity.calendar();
        String today = InputActivity.year+"年"+InputActivity.month+"月"+InputActivity.day+"月";
        dateText.setText(today);

        // ナビゲーションドロワーの設定
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, mToolbar, R.string.app_name, R.string.app_name);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        adapter = ArrayAdapter.createFromResource(this, R.array.list4, android.R.layout.simple_dropdown_item_1line);
        mMealsResearch = (AutoCompleteTextView)findViewById(R.id.wishingmeals_edittext);
        mMealsResearch.setAdapter(adapter);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_meals) {
                    mToolbar.setTitle("食事");
                    Log.d("TESTEST", "この機能は未だ作り途中です");
                } else if (id == R.id.nav_nuetrition) {
                    mToolbar.setTitle("栄養");
                    Log.d("TESTEST", "この機能は未だ作り途中です");
                } else if (id == R.id.nav_saumary) {
                    mToolbar.setTitle("サマリー");
                    Intent intent = new Intent(getApplicationContext(), SummaryActivity.class);
                    startActivity(intent);

                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    /**private void summaryview(String NutritionName, int actualSum, String recommendedAmount, TextView textLess, TextView textMuch){

        int recommendedAmount2 =Integer.parseInt(recommendedAmount);
        int volume =actualSum/recommendedAmount2;

        if (volume <0.75){
            textLess.setText(NutritionName);
            textMuch.setVisibility(View.INVISIBLE);
        }else if(volume > 1.50){
            textMuch.setText(NutritionName);
            textLess.setVisibility(View.INVISIBLE);
        }
    }*/

    private void summaryviewLess(String NutritionName, int actualSum, String recommendedAmount, TextView textLess,int textViewLessid) {

        int recommendedAmount2 =Integer.parseInt(recommendedAmount);
        mVolume =(double)actualSum/recommendedAmount2;

        textLess =(TextView)findViewById(textViewLessid);
        if (mVolume <=0.75){
            textLess.setText(NutritionName);
        }else if(mVolume >= 1.50){
            textLess.setVisibility(View.INVISIBLE);
        }
        Log.d("TEST","actualSum:"+actualSum);
        Log.d("TEST","recommendedAmount2:"+recommendedAmount2);
        Log.d("TEST","mVolume:"+mVolume);
    }

    private void summaryviewMuch(String NutritionName, int actualSum, String recommendedAmount, TextView textMuch,int textViewMuchid) {

        int recommendedAmount2 =Integer.parseInt(recommendedAmount);
        mVolume =(double)actualSum/recommendedAmount2;

        textMuch =(TextView)findViewById(textViewMuchid);
        if (mVolume <0.75){
            textMuch.setVisibility(View.INVISIBLE);
        }else if(mVolume > 1.50){
            textMuch.setText(NutritionName);
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
