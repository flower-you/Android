package com.example.hello;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

	private TextView tv_digst1, tv_operator, tv_show;
	private Button DigstButton;
	private StringBuilder text=new StringBuilder();
	private String resultText,operatorStr;
	private Integer operator1,operator2;
	private Float result;
	
	/**
	 * 数字
	 */
	private Button num0;
	private Button num1;
	private Button num2;
	private Button num3;
	private Button num4;
	private Button num5;
	private Button num6;
	private Button num7;
	private Button num8;
	private Button num9;
	/**
	 * 运算符
	 */
	private Button plus_btn;
	private Button subtract_btn;
	private Button multiply_btn;
	private Button divide_btn;
	private Button equal_btn;

	// 结果
//    private EditText mResultText;  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 绑定布局文件
		setContentView(R.layout.activity_main);

		//获取显示框
		tv_show = (TextView) findViewById(R.id.active_main_show_name);
		
		/**
		 * 获取数字按钮
		 */
		Button bt0=(Button)findViewById(R.id.active_main_bt_0);
		bt0.setOnClickListener(this);
		Button bt1=(Button)findViewById(R.id.active_main_bt_1);
		bt1.setOnClickListener(this);
		Button bt2=(Button)findViewById(R.id.active_main_bt_2);
		bt2.setOnClickListener(this);
		Button bt3=(Button)findViewById(R.id.active_main_bt_3);
		bt3.setOnClickListener(this);
		Button bt4=(Button)findViewById(R.id.active_main_bt_4);
		bt4.setOnClickListener(this);
		Button bt5=(Button)findViewById(R.id.active_main_bt_5);
		bt5.setOnClickListener(this);
		Button bt6=(Button)findViewById(R.id.active_main_bt_6);
		bt6.setOnClickListener(this);
		Button bt7=(Button)findViewById(R.id.active_main_bt_7);
		bt7.setOnClickListener(this);
		Button bt8=(Button)findViewById(R.id.active_main_bt_8);
		bt8.setOnClickListener(this);
		Button bt9=(Button)findViewById(R.id.active_main_bt_9);
		bt9.setOnClickListener(this);
		
		/**
		 * 获取操作符
		 */
		Button add_bt=(Button)findViewById(R.id.active_main_bt_add);
		add_bt.setOnClickListener(this);
		Button sub_bt=(Button)findViewById(R.id.active_main_bt_sub);
		sub_bt.setOnClickListener(this);
		Button div_bt=(Button)findViewById(R.id.active_main_bt_div);
		div_bt.setOnClickListener(this);
		Button x_bt=(Button)findViewById(R.id.active_main_bt_x);
		x_bt.setOnClickListener(this);
		Button equal_bt=(Button)findViewById(R.id.active_main_bt_equal);
		equal_bt.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.active_main_bt_0:
			if(operator1==null) {
				operator1=0;
			}
			else if(operator2==null) {
				operator2=0;
			}
			else {
				operator2=0;
			}
			text.append("0");
			break;
		case R.id.active_main_bt_1:
			text.append("1");
			if(operator1==null) {
				operator1=1;
			}
			else if(operator2==null) {
				operator2=1;
			}
			else {
				operator2=1;
			}
			break;
		case R.id.active_main_bt_2:
			text.append("2");
			if(operator1==null) {
				operator1=2;
			}
			else if(operator2==null) {
				operator2=2;
			}
			else {
				operator2=2;
			}
			break;
		case R.id.active_main_bt_3:
			text.append("3");
			if(operator1==null) {
				operator1=3;
			}
			else if(operator2==null) {
				operator2=3;
			}
			else {
				operator2=3;
			}
			break;
		case R.id.active_main_bt_4:
			text.append("4");
			if(operator1==null) {
				operator1=4;
			}
			else if(operator2==null) {
				operator2=4;
			}
			else {
				operator2=4;
			}
			break;
		case R.id.active_main_bt_5:
			text.append("5");
			if(operator1==null) {
				operator1=5;
			}
			else if(operator2==null) {
				operator2=5;
			}
			else {
				operator2=5;
			}
			break;
		case R.id.active_main_bt_6:
			text.append("6");
			if(operator1==null) {
				operator1=6;
			}
			else if(operator2==null) {
				operator2=6;
			}
			else {
				operator2=6;
			}
			break;
		case R.id.active_main_bt_7:
			text.append("7");
			if(operator1==null) {
				operator1=7;
			}
			else if(operator2==null) {
				operator2=7;
			}
			else {
				operator2=7;
			}
			break;
		case R.id.active_main_bt_8:
			text.append("8");
			if(operator1==null) {
				operator1=8;
			}
			else if(operator2==null) {
				operator2=8;
			}
			else {
				operator2=8;
			}
			break;
		case R.id.active_main_bt_9:
			text.append("9");
			if(operator1==null) {
				operator1=9;
			}
			else if(operator2==null) {
				operator2=9;
			}
			else {
				operator2=9;
			}
			break;
		case R.id.active_main_bt_add:
			text.append("+");
			operatorStr="+";
			break;
		case R.id.active_main_bt_sub:
			text.append("-");
			operatorStr="-";
			
			break;
		case R.id.active_main_bt_x:
			text.append("x");
			operatorStr="x";
			break;
		case R.id.active_main_bt_div:
			text.append("÷");
			operatorStr="÷";
			break;
		case R.id.active_main_bt_equal:
			text.append("=");
			if(result==null) {
				if(operatorStr.equals("+")) {
					result=(float)operator1+operator2;
				}
				else if(operatorStr.equals("-")){
					result=(float)operator1-operator2;
				}
				else if(operatorStr.equals("x")) {
					result=(float)(operator1*operator2);
				}
				else if(operatorStr.equals("÷")) {
					result=(float)operator1/operator2;
				}
				else {
					//显示出错信息
					Toast.makeText(MainActivity.this, "无法识别的操作符", 0).show();
				}
				
//				switch(operatorStr) {
//				case "+":
//					result=(float)(operator1+operator2);
//				}
			}
			else {
				operator1=(int)Float.parseFloat(result.toString());
			}
			operator2=null;
			text.append(result);
//			break;
//		default:
//			break;
		
		}
		tv_show.setText(text.toString());
	}
}
	

