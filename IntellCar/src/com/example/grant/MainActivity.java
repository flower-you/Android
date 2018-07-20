package com.example.grant;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * 滑动导航页面
 * @author 17976
 *
 */
public class MainActivity extends Activity {

	private ViewPager viewpager;
	private Button but_start;
	private ArrayList<View> aList;//创建存放欢迎导航界面图片的集合
	private WelcomAdapter adapter;
	
	private Button bt_con,sen_con;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hint();
    }
    public void hint(){
    	viewpager = (ViewPager) findViewById(R.id.vpager);
		but_start = (Button) findViewById(R.id.but_start_welcome);
		
		//设置监听器
		but_start.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {	
				//跳转到第二个页面
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, IndexActivity.class);
//				intent.setClass(IndexActivity.class, CarActivity.class);
				startActivity(intent);
			}
		});
		//创建容器对象
		aList = new ArrayList<View>();
		LayoutInflater inflater = getLayoutInflater();
		aList.add(inflater.inflate(R.layout.guide1, null, false));
		aList.add(inflater.inflate(R.layout.guide2, null, false));
		aList.add(inflater.inflate(R.layout.guide3, null, false));
		aList.add(inflater.inflate(R.layout.guide4, null,false));
		//创建适配器对象
		adapter = new WelcomAdapter(aList);
		//将适配器添加到组件中
		viewpager.setAdapter(adapter);
		//设置viewpage的滑动监听
		viewpager.addOnPageChangeListener(new OnPageChangeListener() {	
			@Override
			//监听到的组件的id
			public void onPageSelected(int id) {
				if(id == 3){
				   //设置按钮可见
					but_start.setVisibility(View.VISIBLE);	
					
				}else{
					//如果不是最后一张图片，设置按钮不可见
					but_start.setVisibility(View.GONE);
				}	
			}		
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub		
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
    }
}
