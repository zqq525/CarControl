package com.example.zqq.carcontrol;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener {
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    private TextView textview_car;
    private TextView textview_message;

    private TextView tab_car_textview_cheliuliang;
    private TextView tab_car_textview_style;

    private List<View> views;
    private Context context;

    private Spinner spinner;
    private ArrayAdapter<String> spinnerAdapter;
    private String[] spinnerstr = {"0:00-4:00", "4:00-8:00", "8:00-12:00", "12:00-16:00",
            "16:00-20:00", "20:00-24:00"};
    private List<String> spinnerarray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        views = new ArrayList<View>();

        textview_car = (TextView) findViewById(R.id.top_textview_car);
        textview_message = (TextView) findViewById(R.id.top_textview_message);

        textview_car.setOnClickListener(this);
        textview_message.setOnClickListener(this);

        LayoutInflater inflater = LayoutInflater.from(this);
        //tab_car页面的初始化
        View tab_car = inflater.inflate(R.layout.tab_car, null);
        spinner = (Spinner) tab_car.findViewById(R.id.tab_car_spinner);
        spinnerarray = new ArrayList<String>();
        Collections.addAll(spinnerarray, spinnerstr);
        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerarray);
        spinner.setAdapter(spinnerAdapter);
        tab_car_textview_cheliuliang = (TextView) findViewById(R.id.tab_car_textview_cheliuliang);
        tab_car_textview_style = (TextView) findViewById(R.id.tab_car_textview_style);
        //tab_message页面的初始化
        View tab_message = inflater.inflate(R.layout.tab_message, null);

        views.add(tab_car);
        views.add(tab_message);
        pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = views.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(views.get(position));
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        };



        viewPager.setAdapter(pagerAdapter);

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.top_textview_car:
                viewPager.setCurrentItem(0);
                break;
            case R.id.top_textview_message:
                viewPager.setCurrentItem(1);
                break;
        }
    }
}
