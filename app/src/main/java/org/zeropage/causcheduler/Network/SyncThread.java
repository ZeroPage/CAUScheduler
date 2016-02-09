package org.zeropage.causcheduler.network;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import io.realm.Realm;

/**
 * Created by Donghwan on 2016-02-10.
 */
public class SyncThread extends Thread {

	public Handler handler;
	private Context context;

	public SyncThread(Context context) {
		this.context = context;
	}

	@Override
	public void run() {
		Realm realm = null;
		try{
			Looper.prepare();
			realm = Realm.getInstance(context);
			handler = new SyncHandler(realm);
			Looper.loop();
		}finally {
			if(realm != null) realm.close();
		}
	}
}
