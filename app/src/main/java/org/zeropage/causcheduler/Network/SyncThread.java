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
		Looper.prepare();
		handler = new SyncHandler(context);
		Looper.loop();
	}
}
