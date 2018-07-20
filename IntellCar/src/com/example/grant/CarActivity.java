package com.example.grant;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;



import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;
/**
 * 按钮控制小车移动
 * @author 17976
 *
 */
public class CarActivity extends Activity {
	
	private Button but_go, but_back, but_left, but_right;// 前进后退左右的按钮
	private byte[] commands;// 发送的指令
	private Socket socket;// socket对象
	static OutputStream socketWriter;// 输出流

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.car_view);
		
		but_back = (Button) findViewById(R.id.but_back);
		but_go = (Button) findViewById(R.id.but_go);
		but_left = (Button) findViewById(R.id.but_left);
		but_right = (Button) findViewById(R.id.but_right);
		// 创建事件监听对象
		ButtonListener buttonListener = new ButtonListener();
		but_back.setOnTouchListener(buttonListener);
		but_go.setOnTouchListener(buttonListener);
		but_left.setOnTouchListener(buttonListener);
		but_right.setOnTouchListener(buttonListener);
		InitSocket();
		toast("连接.....");	
	}
	
	// 按钮点击监听的事件处理
		class ButtonListener implements OnTouchListener {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.but_go:
					// 按下事件
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						toast("按下");
						// 向前 按下
						commands = (new byte[] { (byte) 0xff, (byte) 0x00,
								(byte) 0x01, (byte) 0x00, (byte) 0xff });
						sendcmd(commands);// 前进的指令
					}
					// 抬起，停止
					if (event.getAction() == MotionEvent.ACTION_UP) {
						commands = (new byte[] { (byte) 0xff, (byte) 0x00,
								(byte) 0x00, (byte) 0x00, (byte) 0xff });
						sendcmd(commands);// 前进的指令;
					}
					break;
				// 后退
				case R.id.but_back:
					// 按下事件
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						toast("按下");
						// 向后 按下事件
						commands = (new byte[] { (byte) 0xff, (byte) 0x00,
								(byte) 0x02, (byte) 0x00, (byte) 0xff });
						sendcmd(commands);
					}
					// 抬起，停止
					if (event.getAction() == MotionEvent.ACTION_UP) {
						commands = (new byte[] { (byte) 0xff, (byte) 0x00,
								(byte) 0x00, (byte) 0x00, (byte) 0xff });
						sendcmd(commands);// 前进的指令;
					}
					break;
				// 左转
				case R.id.but_left:
					// 左转按下事件
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						toast("按下");
						commands = (new byte[] { (byte) 0xff, (byte) 0x00,
								(byte) 0x03, (byte) 0x00, (byte) 0xff });
						sendcmd(commands);// 前进的指令
					}
					// 抬起，停止
					if (event.getAction() == MotionEvent.ACTION_UP) {
						commands = (new byte[] { (byte) 0xff, (byte) 0x00,
								(byte) 0x00, (byte) 0x00, (byte) 0xff });
						sendcmd(commands);// 前进的指令;
					}

					break;
				// 右转
				case R.id.but_right:
					// 按下事件
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						toast("按下");
						// 右转 按下
						commands = (new byte[] { (byte) 0xff, (byte) 0x00,
								(byte) 0x04, (byte) 0x00, (byte) 0xff });
						sendcmd(commands);// 前进的指令
					}
					// 抬起，停止
					if (event.getAction() == MotionEvent.ACTION_UP) {
						commands = (new byte[] { (byte) 0xff, (byte) 0x00,
								(byte) 0x00, (byte) 0x00, (byte) 0xff });
						sendcmd(commands);// 前进的指令;
					}
					break;

				default:
					break;
				}
				return false;// 阻止事件向下发送
			}

		}

		public void toast(String msg) {
			Toast.makeText(getApplicationContext(), msg, 0).show();
		}

		public void onDestroy() {
			super.onDestroy();
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// 初始化socket
		public void InitSocket() {
			try {
//				socket = new Socket("192.168.1.1", Integer.parseInt("2001"));
				socket = new Socket("192.168.43.168", 9091);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (socket != null) {
					socketWriter = socket.getOutputStream();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// 发送指令的方法
		public static void sendcmd(byte[] commands) {
			try {
				socketWriter.write(commands);
				socketWriter.flush();

			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}// sendcmd

}