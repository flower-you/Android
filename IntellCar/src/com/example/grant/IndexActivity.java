package com.example.grant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * ��ť�ʹ�����ģʽѡ�����
 * @author 17976
 *
 */
public class IndexActivity extends Activity {
	private Button bt_con,sen_con;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index);
		bt_con=(Button)findViewById(R.id.button);
		sen_con=(Button)findViewById(R.id.sensor);
		bt_con.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//��ת����ť����ҳ��
				Intent intent=new Intent();
				intent.setClass(IndexActivity.this, CarActivity.class);
				startActivity(intent);
			}
		});
		sen_con.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//��ת������������ҳ��
				Intent intent=new Intent();
				intent.setClass(IndexActivity.this, SensorActivity.class);
				startActivity(intent);
			}
		});
	}
}
