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
 * ��������ҳ��
 * @author 17976
 *
 */
public class MainActivity extends Activity {

	private ViewPager viewpager;
	private Button but_start;
	private ArrayList<View> aList;//������Ż�ӭ��������ͼƬ�ļ���
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
		
		//���ü�����
		but_start.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {	
				//��ת���ڶ���ҳ��
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, IndexActivity.class);
//				intent.setClass(IndexActivity.class, CarActivity.class);
				startActivity(intent);
			}
		});
		//������������
		aList = new ArrayList<View>();
		LayoutInflater inflater = getLayoutInflater();
		aList.add(inflater.inflate(R.layout.guide1, null, false));
		aList.add(inflater.inflate(R.layout.guide2, null, false));
		aList.add(inflater.inflate(R.layout.guide3, null, false));
		aList.add(inflater.inflate(R.layout.guide4, null,false));
		//��������������
		adapter = new WelcomAdapter(aList);
		//����������ӵ������
		viewpager.setAdapter(adapter);
		//����viewpage�Ļ�������
		viewpager.addOnPageChangeListener(new OnPageChangeListener() {	
			@Override
			//�������������id
			public void onPageSelected(int id) {
				if(id == 3){
				   //���ð�ť�ɼ�
					but_start.setVisibility(View.VISIBLE);	
					
				}else{
					//����������һ��ͼƬ�����ð�ť���ɼ�
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
