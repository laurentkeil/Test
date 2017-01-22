package com.sdz.test;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * pref
 *
 */
public class PreferenceActivityExample extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		  addPreferencesFromResource(R.xml.preference);
	}

}
