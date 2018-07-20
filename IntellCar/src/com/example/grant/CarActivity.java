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
 * ��ť����С���ƶ�
 * @author 17976
 *
 */
public class CarActivity extends Activity {
	
	private Button but_go, but_back, but_left, but_right;// ǰ���������ҵİ�ť
	private byte[] commands;// ���͵�ָ��
	private Socket socket;// socket����
	static OutputStream socketWriter;// �����

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.car_view);
		
		but_back = (Button) findViewById(R.id.but_back);
		but_go = (Button) findViewById(R.id.but_go);
		but_left = (Button) findViewById(R.id.but_left);
		but_right = (Button) findViewById(R.id.but_right);
		// �����¼���������
		ButtonListener buttonListener = new ButtonListener();
		but_back.setOnTouchListener(buttonListener);
		but_go.setOnTouchListener(buttonListener);
		but_left.setOnTouchListener(buttonListener);
		but_right.setOnTouchListener(buttonListener);
		InitSocket();
		toast("����.....");	
	}
	
	// ��ť����������¼�����
		class ButtonListener implements OnTouchListener {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.but_go:
					// �����¼�
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						toast("����");
						// ��ǰ ����
						commands = (new byte[] { (byte) 0xff, (byte) 0x00,
								(byte) 0x01, (byte) 0x00, (byte) 0xff });
						sendcmd(commands);// ǰ����ָ��
					}
					// ̧��ֹͣ
					if (event.getAction() == MotionEvent.ACTION_UP) {
						commands = (new byte[] { (byte) 0xff, (byte) 0x00,
								(byte) 0x00, (byte) 0x00, (byte) 0xff });
						sendcmd(commands);// ǰ����ָ��;
					}
					break;
				// ����
				case R.id.but_back:
					// �����¼�
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						toast("����");
						// ��� �����¼�
						commands = (new byte[] { (byte) 0xff, (byte) 0x00,
								(byte) 0x02, (byte) 0x00, (byte) 0xff });
						sendcmd(commands);
					}
					// ̧��ֹͣ
					if (event.getAction() == MotionEvent.ACTION_UP) {
						commands = (new byte[] { (byte) 0xff, (byte) 0x00,
								(byte) 0x00, (byte) 0x00, (byte) 0xff });
						sendcmd(commands);// ǰ����ָ��;
					}
					break;
				// ��ת
				case R.id.but_left:
					// ��ת�����¼�
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						toast("����");
						commands = (new byte[] { (byte) 0xff, (byte) 0x00,
								(byte) 0x03, (byte) 0x00, (byte) 0xff });
						sendcmd(commands);// ǰ����ָ��
					}
					// ̧��ֹͣ
					if (event.getAction() == MotionEvent.ACTION_UP) {
						commands = (new byte[] { (byte) 0xff, (byte) 0x00,
								(byte) 0x00, (byte) 0x00, (byte) 0xff });
						sendcmd(commands);// ǰ����ָ��;
					}

					break;
				// ��ת
				case R.id.but_right:
					// �����¼�
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						toast("����");
						// ��ת ����
						commands = (new byte[] { (byte) 0xff, (byte) 0x00,
								(byte) 0x04, (byte) 0x00, (byte) 0xff });
						sendcmd(commands);// ǰ����ָ��
					}
					// ̧��ֹͣ
					if (event.getAction() == MotionEvent.ACTION_UP) {
						commands = (new byte[] { (byte) 0xff, (byte) 0x00,
								(byte) 0x00, (byte) 0x00, (byte) 0xff });
						sendcmd(commands);// ǰ����ָ��;
					}
					break;

				default:
					break;
				}
				return false;// ��ֹ�¼����·���
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

		// ��ʼ��socket
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

		// ����ָ��ķ���
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