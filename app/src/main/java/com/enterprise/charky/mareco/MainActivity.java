package com.enterprise.charky.mareco;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;

import com.enterprise.charky.mareco.irclibrary.SamsungIRCodes;
import com.enterprise.charky.mareco.irtransmitter.IRProvider;
import com.enterprise.charky.mareco.irtransmitter.IRTransmitter;
import com.enterprise.charky.mareco.ui.NavigationDrawerFragment;
import com.enterprise.charky.mareco.ui.PlaceholderFragment;
import com.enterprise.charky.mareco.util.NavigationListAdapter;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    /**
     * IRTransmitter for Sending IRCodes
     */
    public IRTransmitter irTransmitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get NavigationDrawer
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        // TODO: Check delete later, no need at the moment?
        mTitle = getTitle();

        // Create IRProvider List
        ArrayList<IRProvider> IRProviders = new ArrayList<IRProvider>();
        IRProviders.add(new IRProvider("Samsung TV","Smart TV 2012 and Later",
                R.drawable.ic_tv_black_48dp, new SamsungIRCodes()));

        // Initialize IR Transmitter
        //Create IRTransmitter
        irTransmitter = new IRTransmitter(this);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout),
                new NavigationListAdapter(this,IRProviders));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        // actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }
}
