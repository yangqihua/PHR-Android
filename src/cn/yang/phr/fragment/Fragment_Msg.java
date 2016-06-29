package cn.yang.phr.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.yang.phr.R;

public class Fragment_Msg extends Fragment {
	private View layout;
	private Activity ctx;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (layout == null) {
			ctx = this.getActivity();
			layout = ctx.getLayoutInflater().inflate(R.layout.fragment_msg,
					null);
			initViews();
			initData();
		} else {
			ViewGroup parent = (ViewGroup) layout.getParent();
			if (parent != null) {
				parent.removeView(layout);
			}
		}
		return layout;
	}
	
	private void initData() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Ë¢ÐÂÒ³Ãæ
	 */
	public void refresh() {
//		conversationList.clear();
		initViews();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		
	}
}
