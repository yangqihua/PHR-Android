package cn.yang.phr;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.yang.phr.fragment.Fragment_Dicover;
import cn.yang.phr.fragment.Fragment_Friends;
import cn.yang.phr.fragment.Fragment_Msg;

public class MainActivity extends FragmentActivity {
	@ViewInject(R.id.txt_title)
	private TextView txt_title;
	@ViewInject(R.id.img_right)
	private ImageView img_right;
	@ViewInject(R.id.fragment_container)
	private ViewPager mViewPager;
	
	private List<Fragment> fragments;
	public Fragment_Msg homefragment;
	private Fragment_Friends contactlistfragment;
	private Fragment_Dicover findfragment;
	
	protected static final String TAG = "MainActivity";
	
	private ImageView[] imagebuttons;
	private TextView[] textviews;
	
	private int index;
	private int currentTabIndex;// 当前fragment的index
	private int keyBackClickCount = 0;//点击返回按钮的次数

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		x.view().inject(this);
		initTabView();
	}
	
	/*
	 * 初始化fragment，将tab和fragment联系起来
	 */
	private void initTabView() {
		
		homefragment = new Fragment_Msg();
		contactlistfragment = new Fragment_Friends();
		findfragment = new Fragment_Dicover();
		
		fragments = new ArrayList<Fragment>();
		fragments.add(homefragment);
		fragments.add(contactlistfragment);
		fragments.add(findfragment);
		imagebuttons = new ImageView[3];
		mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(),fragments));
		mViewPager.setCurrentItem(0);
		mViewPager.addOnPageChangeListener(new MyOnPageChangeListener());
		
		imagebuttons[0] = (ImageView) findViewById(R.id.ib_weixin);
		imagebuttons[1] = (ImageView) findViewById(R.id.ib_contact_list);
		imagebuttons[2] = (ImageView) findViewById(R.id.ib_find);

		imagebuttons[0].setSelected(true);
		textviews = new TextView[3];
		textviews[0] = (TextView) findViewById(R.id.tv_weixin);
		textviews[1] = (TextView) findViewById(R.id.tv_contact_list);
		textviews[2] = (TextView) findViewById(R.id.tv_find);
		textviews[0].setTextColor(0xFF45C01A);
	}
	
	
	public void onTabClicked(View view) {
		img_right.setVisibility(View.GONE);
		switch (view.getId()) {
		case R.id.re_weixin:
			img_right.setVisibility(View.VISIBLE);
			index = 0;
			if (homefragment != null) {
				homefragment.refresh();
			}
			txt_title.setText(R.string.app_name);
			img_right.setImageResource(R.drawable.icon_add);
			break;
		case R.id.re_contact_list:
			index = 1;
			txt_title.setText(R.string.contacts);
			img_right.setVisibility(View.VISIBLE);
			img_right.setImageResource(R.drawable.icon_titleaddfriend);
			break;
		case R.id.re_find:
			index = 2;
			txt_title.setText(R.string.discover);
			break;
		}
		if (currentTabIndex != index) {
			mViewPager.setCurrentItem(index);
		}
		
		imagebuttons[currentTabIndex].setSelected(false);
		// 把当前tab设为选中状态
		imagebuttons[index].setSelected(true);
		textviews[currentTabIndex].setTextColor(0xFF999999);
		textviews[index].setTextColor(0xFF45C01A);
		currentTabIndex = index;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onKeyDown(int, android.view.KeyEvent)
	 * 处理后退按钮
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			switch (keyBackClickCount++) {
			case 0:
				Toast.makeText(this, "再次按返回键退出", Toast.LENGTH_SHORT).show();
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						keyBackClickCount = 0;
					}
				}, 3000);
				break;
			case 1:
				finish();
				overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
				break;
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
	class MyAdapter extends FragmentPagerAdapter{
		private List<Fragment> fragments;
		
		public MyAdapter(FragmentManager fm,List<Fragment> fragments) {
			super(fm);
			this.fragments=fragments;
		}

		public Fragment getItem(int fragment) {
			return fragments.get(fragment);
		}

		public int getCount() {
			return fragments.size();
		}

	}
	
	
	class MyOnPageChangeListener implements OnPageChangeListener {
		 
        @Override
        public void onPageSelected(int arg0) {
        	img_right.setVisibility(View.GONE);
            switch (arg0) {
            case 0:
            	img_right.setVisibility(View.VISIBLE);
    			index = 0;
    			if (homefragment != null) {
    				homefragment.refresh();
    			}
    			txt_title.setText(R.string.app_name);
    			img_right.setImageResource(R.drawable.icon_add);
                break;
            case 1:
            	index = 1;
    			txt_title.setText(R.string.contacts);
    			img_right.setVisibility(View.VISIBLE);
    			img_right.setImageResource(R.drawable.icon_titleaddfriend);
                break;
            case 2:
            	index = 2;
    			txt_title.setText(R.string.discover);
                break;
            }
            imagebuttons[currentTabIndex].setSelected(false);
    		// 把当前tab设为选中状态
    		imagebuttons[index].setSelected(true);
    		textviews[currentTabIndex].setTextColor(0xFF999999);
    		textviews[index].setTextColor(0xFF45C01A);
    		currentTabIndex = index;
        }
 
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
 
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }
	

}
