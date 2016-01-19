package org.zeropage.causcheduler.activity;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import org.zeropage.causcheduler.R;

public class PrefsFragment extends PreferenceFragment {

    public static PrefsFragment newInstance() {
        PrefsFragment fragment = new PrefsFragment();
        return fragment;
    }

    public PrefsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }

}
