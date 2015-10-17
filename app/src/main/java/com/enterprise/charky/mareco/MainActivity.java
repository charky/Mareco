package com.enterprise.charky.mareco;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.support.v4.widget.DrawerLayout;

import com.enterprise.charky.mareco.irclibrary.SamsungIRCodes;
import com.enterprise.charky.mareco.irtransmitter.IRCodes;
import com.enterprise.charky.mareco.irtransmitter.IRTransmitter;
import com.enterprise.charky.mareco.ui.BasicKeyFragment;
import com.enterprise.charky.mareco.ui.ExtendedFragment;
import com.enterprise.charky.mareco.ui.NavigationDrawerFragment;
import com.enterprise.charky.mareco.ui.HomeFragment;
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
     * Pointer at the active Fragment
     */
    private ExtendedFragment activeFragment;

    private BasicKeyFragment genBasicKeyFragment;

    private HomeFragment homeFragment;
    /**
     * IRTransmitter for Sending IRCodes
     */
    public IRTransmitter irTransmitter;

    private ArrayList<IRCodes> IRCodesList = new ArrayList<IRCodes>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get NavigationDrawer
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        // TODO: Asking: Check delete later?, Function not totally clear
        mTitle = getTitle();

        // Add IRCode elements to the list
        IRCodesList.add( new SamsungIRCodes());

        // Initialize IR Transmitter
        irTransmitter = new IRTransmitter(this);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout),
                new NavigationListAdapter(this, IRCodesList));

        genBasicKeyFragment = new BasicKeyFragment();

        loadHome();
    }

    public void loadHome(){
        if(homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        changeFragment(homeFragment);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        genBasicKeyFragment.setIrCodes(IRCodesList.get(position));
        changeFragment(genBasicKeyFragment);
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

    public void buttonClick(View v){
        if(activeFragment != null)
            activeFragment.onButtonClick(v,this);
    }

    /**
     * Update the main content by replacing fragments
     * @param fragment The Fragment to be loaded
     */
    private void changeFragment(ExtendedFragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
        activeFragment = fragment;
    }

}
