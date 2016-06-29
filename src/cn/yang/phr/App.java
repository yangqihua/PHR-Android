package cn.yang.phr;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.xutils.x;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;


public class App extends Application {

	private static Context _context;

	@Override
	public void onCreate() {
		super.onCreate();
		_context = getApplicationContext();
		x.Ext.init(this); 
	}


//	private String getAppName(int pID) {
//		String processName = null;
//		ActivityManager am = (ActivityManager) this
//				.getSystemService(ACTIVITY_SERVICE);
//		List l = am.getRunningAppProcesses();
//		Iterator i = l.iterator();
//		PackageManager pm = this.getPackageManager();
//		while (i.hasNext()) {
//			ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i
//					.next());
//			try {
//				if (info.pid == pID) {
//					CharSequence c = pm.getApplicationLabel(pm
//							.getApplicationInfo(info.processName,
//									PackageManager.GET_META_DATA));
//					processName = info.processName;
//					return processName;
//				}
//			} catch (Exception e) {
//			}
//		}
//		return processName;
//	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		try {
			deleteCacheDirFile(getHJYCacheDir(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.gc();
	}

	public static Context getInstance() {
		return _context;
	}

	// 杩愮敤list鏉ヤ繚瀛樹滑姣忎竴涓猘ctivity鏄叧閿?
	private List<Activity> mList = new LinkedList<Activity>();
	private static App instance;

	// 鏋勯?犳柟娉?
	// 瀹炰緥鍖栦竴娆?
	public synchronized static App getInstance2() {
		if (null == instance) {
			instance = new App();
		}
		return instance;
	}

	// add Activity
	public void addActivity(Activity activity) {
		mList.add(activity);
	}

	// 鍏抽棴姣忎竴涓猯ist鍐呯殑activity
	public void exit() {
		try {
			for (Activity activity : mList) {
				if (activity != null)
					activity.finish();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	public static String getHJYCacheDir() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED))
			return Environment.getExternalStorageDirectory().toString()
					+ "/Health/Cache";
		else
			return "/System/com.juns.Walk/Walk/Cache";
	}

	public static String getHJYDownLoadDir() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED))
			return Environment.getExternalStorageDirectory().toString()
					+ "/Walk/Download";
		else {
			return "/System/com.Juns.Walk/Walk/Download";
		}
	}
	
	//delete cachedir
	public static void deleteCacheDirFile(String filePath,
			boolean deleteThisPath) throws IOException {
		if (!TextUtils.isEmpty(filePath)) {
			File file = new File(filePath);
			if (file.isDirectory()) {// 澶勭悊鐩綍
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					deleteCacheDirFile(files[i].getAbsolutePath(), true);
				}
			}
			if (deleteThisPath) {
				if (!file.isDirectory()) {// 濡傛灉鏄枃浠讹紝鍒犻櫎
					file.delete();
				} else {// 鐩綍
					if (file.listFiles().length == 0) {// 鐩綍涓嬫病鏈夋枃浠舵垨鑰呯洰褰曪紝鍒犻櫎
						file.delete();
					}
				}
			}
		}
	}
}
