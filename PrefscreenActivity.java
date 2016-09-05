package com.example.maor1t.project_final;


import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.widget.Toast;




public class PrefscreenActivity extends PreferenceActivity implements OnPreferenceClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);


        Preference button = findPreference("Button");
        button.setOnPreferenceClickListener(this);

    }



    @Override
    public boolean onPreferenceClick(Preference pref) {


        String key = pref.getKey();

        if (key.equals("Button")){

            DBHandler hand = new DBHandler(this);
            hand.deletefavorites();
        }


        return true;
    }


}

