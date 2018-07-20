package com.example.grant;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * 通过传感器控制小车移动
 * @author 17976
 *
 */
public class SensorActivity extends Activity implements SensorEventListener{
	private TextView tv_show, tv_x, tv_y, tv_z;
	private Sensor sr;
	private SensorManager sm;
	private Socket socket;
	static OutputStream socketWriter;
	private byte[] commands;
	private RelativeLayout rl;

	URL videoUrl;
	public static String CameraIp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sensor_view);tv_show = (TextView) findViewById(R.id.activity_tv1);
		tv_x = (TextView) findViewById(R.id.activity_x);
		tv_y = (TextView) findViewById(R.id.activity_y);
		tv_z = (TextView) findViewById(R.id.activity_z);

		rl = (RelativeLayout) findViewById(R.id.rl);

		sm = (SensorManager) getSystemService(SENSOR_SERVICE);

		StringBuilder sb = new StringBuilder("");
		InitSocket();

		sr = sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);

		sb.append("方向传感器名字:" + sr.getName() + "类型" + sr.getType() + "\n");
		tv_show.setText(sb.toString());
	}

	@Override
	protected void onStart() {
		super.onStart();

		sm.registerListener(this, sr, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		super.onPause();
		sm.unregisterListener(this);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		Sensor sensor = event.sensor;
		int type = sensor.getType();
		if (type == Sensor.TYPE_ORIENTATION) {
			float[] f = event.values;

			tv_x.setText("X:" + f[0]);
			tv_y.setText("Y:" + f[1]);
			tv_z.setText("Z:" + f[2]);

			if ((f[1] > 0) && (f[1] < 180) && (f[2] > 0) && (f[2] < 14)) {// v[1] 0~180 向前
				tv_show.setText("向前");
				rl.setBackgroundColor(Color.parseColor("#FF99FF"));//粉红
				commands = (new byte[] { (byte) 0xff, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0xff });
//				sendcmd("向前".getBytes());
				sendcmd(commands);

			}
			if ((f[1] >= -180) && (f[1] < 0) && (f[2] > -1) && (f[2] <-7)) {// v[1] -180~0 向后
				tv_show.setText("向后");
				rl.setBackgroundColor(Color.parseColor("#99CCFF"));//天蓝色
				commands = (new byte[] { (byte) 0xff, (byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0xff });
				sendcmd(commands);
			}
			if ((f[1] >= -180) && (f[1] < -10) && (f[2] > 0) && (f[2] <= 90)) {// v[2]: 0~90 向左
				tv_show.setText("向左");
				rl.setBackgroundColor(Color.parseColor("#FFFF66"));//黄色
				commands = (new byte[] { (byte) 0xff, (byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0xff });
				sendcmd(commands);
			}
			if ((f[1] >-180) && (f[1] < -7) && (f[2] >= -28) && (f[2] < 0)) {// v[2] -90~0 向右
				tv_show.setText("向右");
				rl.setBackgroundColor(Color.parseColor("#CCFFCC"));//浅绿色
				commands = (new byte[] { (byte) 0xff, (byte) 0x00, (byte) 0x04, (byte) 0x00, (byte) 0xff });
				sendcmd(commands);
			}
			if ((f[1] > -10) && (f[1] < 0)) {
				tv_show.setText("暂停");
				rl.setBackgroundDrawable(getWallpaper());
				commands = (new byte[] { (byte) 0xff, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xff });
				sendcmd(commands);
			}
		}
	}

	public void onDestroy() {
		super.onDestroy();
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// socket
	public void InitSocket() {
		try {
			socket = new Socket("192.168.43.168", 9091);
//			socket = new Socket("192.168.1.1", 2001);
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
			e.printStackTrace();
		}

	}

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

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {

	}

}
