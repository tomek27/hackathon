package com.hackathon.reminder;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import com.hackathon.reminder.strategies.AlarmPreferenceStrategy;
import com.hackathon.reminder.strategies.FiftyPreferenceStrategy;
import com.hackathon.reminder.strategies.MisPreferenceStrategy;
import com.hackathon.reminder.strategies.TCSPrefernceStrategy;
import roboguice.activity.RoboPreferenceActivity;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends RoboPreferenceActivity {
    private static final String PREFS_NAME = "defaults";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Display the fragment as the main content.
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }

    public static class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
        private static String TAG = SettingsFragment.class.getSimpleName();

        private List<AlarmPreferenceStrategy> alarmStrategies;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // create list of strategies to handle notification create and cancel
            alarmStrategies = new ArrayList<AlarmPreferenceStrategy>();
            alarmStrategies.add(new MisPreferenceStrategy());
            alarmStrategies.add(new TCSPrefernceStrategy());
            alarmStrategies.add(new FiftyPreferenceStrategy());

            getPreferenceManager().setSharedPreferencesName(PREFS_NAME);
            PreferenceManager.setDefaultValues(getActivity(), PREFS_NAME, MODE_PRIVATE, R.xml.fragment_preferences, false);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.fragment_preferences);
        }

        @Override
        public void onResume() {
            super.onResume();
            // Set up a listener whenever a key changes
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            super.onPause();
            // Unregister the listener whenever a key changes
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            for (AlarmPreferenceStrategy strategy : alarmStrategies) {
                if (strategy.accept(key)) {
                    strategy.process(getActivity(), sharedPreferences, key);
                    break;
                }
            }
            Log.e(TAG, "onSharedPreferenceChanged no strategy for key: " + key);
        }
    }
}
