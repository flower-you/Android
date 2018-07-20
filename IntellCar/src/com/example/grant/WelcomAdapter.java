package com.example.grant;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
/**
 * 适配器，获取滑动的图片
 * @author 17976
 *
 */
public class WelcomAdapter extends PagerAdapter {
	private ArrayList<View> viewLists;

	public WelcomAdapter() {
	}

	public WelcomAdapter(ArrayList<View> viewLists) {
		super();
		this.viewLists = viewLists;
	}

	@Override
	public int getCount() {
//		 获取要滑动的控件的数量，view的数量
		return viewLists.size();
	}

	//  来判断显示的是否是同一张图片，这里我们将两个参数相比较返回即可  
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

/**
 *  当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，
 * 我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可  
 */
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(viewLists.get(position));
		return viewLists.get(position);
	}

	/**
	 *   PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁  
	 */
	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewGroup) container).removeView((View) object);
	}
}
