package org.zeropage.causcheduler.network;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import io.realm.Realm;

/**
 * Created by Donghwan on 2016-02-10.
 */
public class SyncHandler extends Handler {
	private Realm realm;

	public static final int SYNC = 1;

	public static final String ACTION = "action";

	public SyncHandler(Realm realm) {
		this.realm = realm;
	}

	@Override
	public void handleMessage(Message msg) {
		final Bundle bundle = msg.getData();
		final int action = bundle.getInt(ACTION);

		switch(action){
			case SYNC:
				// TODO 네트워크에서 불러오기
		}
	}
}
