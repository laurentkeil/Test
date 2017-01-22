package com.sdz.explorer;

import com.sdz.test.R;
import com.sdz.test.R.xml;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class ExploreurPreference extends PreferenceActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_couleur);
    }
    
}
