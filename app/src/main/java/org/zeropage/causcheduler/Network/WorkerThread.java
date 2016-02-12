package org.zeropage.causcheduler.network;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import io.realm.Realm;

/**
 * Created by Donghwan on 2016-02-10.
 */
public class WorkerThread extends Thread {

	public Handler handler;
	private Context context;

	public WorkerThread(Context context) {
		this.context = context;
	}

	@Override
	public void run() {
		Realm realm = null;
		try{
			Looper.prepare();
			realm = Realm.getDefaultInstance();
			handler = new WorkerHandler(context, realm);
			Looper.loop();
		}finally {
			if (realm != null) {
				realm.close();
			}
		}
	}
}
