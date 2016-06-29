package cn.yang.phr.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import cn.yang.phr.R;

//通讯�?

public class Fragment_Friends extends Fragment implements OnClickListener,
		OnItemClickListener {
	private Activity ctx;
	private View layout, layout_head;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (layout == null) {
			ctx = this.getActivity();
			layout = ctx.getLayoutInflater().inflate(R.layout.fragment_friends,
					null);
			initViews();
			initData();
			setOnListener();
		} else {
			ViewGroup parent = (ViewGroup) layout.getParent();
			if (parent != null) {
				parent.removeView(layout);
			}
		}
		return layout;
	}

	private void initViews() {

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	/**
	 * 刷新页面
	 */
	public void refresh() {
		initData();
	}

	private void initData() {
	}

	private void setOnListener() {
//		lvContact.setOnItemClickListener(this);
//		layout_head.findViewById(R.id.layout_addfriend)
//				.setOnClickListener(this);
//		layout_head.findViewById(R.id.layout_search).setOnClickListener(this);
//		layout_head.findViewById(R.id.layout_group).setOnClickListener(this);
//		layout_head.findViewById(R.id.layout_public).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
//		case R.id.layout_search:// 搜索好友及公众号
//			Utils.start_Activity(getActivity(), SearchActivity.class);
//			break;
//		case R.id.layout_addfriend:// 添加好友
//			Utils.start_Activity(getActivity(), NewFriendsListActivity.class);
//			break;
//		case R.id.layout_group:// 群聊
//			Utils.start_Activity(getActivity(), GroupListActivity.class);
//			break;
//		case R.id.layout_public:// 公众�?
//			Utils.start_Activity(getActivity(), PublishUserListActivity.class);
//			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

}
