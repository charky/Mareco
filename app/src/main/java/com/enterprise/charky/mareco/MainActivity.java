package com.enterprise.charky.mareco;

import android.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.widget.DrawerLayout;

import com.enterprise.charky.mareco.irclibrary.PanasonicIRCodes;
import com.enterprise.charky.mareco.irclibrary.SamsungIRCodes;
import com.enterprise.charky.mareco.irclibrary.SkyIRCodes;
import com.enterprise.charky.mareco.irtransmitter.IRCodes;
import com.enterprise.charky.mareco.irtransmitter.IRTransmitter;
import com.enterprise.charky.mareco.ui.AdvancedKeyFragment;
import com.enterprise.charky.mareco.ui.BasicKeyFragment;
import com.enterprise.charky.mareco.ui.ExtendedFragment;
import com.enterprise.charky.mareco.ui.NavigationDrawerFragment;
import com.enterprise.charky.mareco.ui.HomeFragment;
import com.enterprise.charky.mareco.util.NavigationListAdapter;
import com.enterprise.charky.mareco.util.NavigationListItem;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private static final int SCENE_STATE_BASIC = 100;
    private static final int SCENE_STATE_ADVANCED = 200;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Pointer at the active Fragment
     */
    private ExtendedFragment activeFragment;

    /**
     * Save active Position
     */
    private int activePosition;
    /**
     * Save the State of the active Remote Control
     */
    private int activeSceneState;

    private BasicKeyFragment genBasicKeyFragment;
    private BasicKeyFragment genBasicKeyFragment2;
    private AdvancedKeyFragment genAdvancedKeyFragment;

    private HomeFragment homeFragment;
    /**
     * IRTransmitter for Sending IRCodes
     */
    public IRTransmitter irTransmitter;

    private ArrayList<IRCodes> IRCodesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get NavigationDrawer
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);


        // Add IRCode elements to the list
        IRCodesList = new ArrayList<>();
        IRCodesList.add( new SamsungIRCodes() );
        IRCodesList.add( new PanasonicIRCodes() );
        IRCodesList.add( new SkyIRCodes() );

        // Initialize IR Transmitter
        irTransmitter = new IRTransmitter(this);

        // Set Up Navigation Items
        ArrayList<NavigationListItem> mNavigationListItem = new ArrayList<>();
        mNavigationListItem.add(new NavigationListItem(R.layout.drawer_list_item,
                "Home", R.drawable.ic_home_black_48dp));
        mNavigationListItem.add(new NavigationListItem(R.layout.drawer_list_item,
                "Samsung TV", R.drawable.ic_tv_black_48dp));
        mNavigationListItem.add(new NavigationListItem(R.layout.drawer_list_item,
                "Panasonic TV", R.drawable.ic_surround_sound_black_48dp));
        mNavigationListItem.add(new NavigationListItem(R.layout.drawer_list_item,
                "Sky Receiver", R.drawable.ic_movie_black_48dp)) ;

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout),
                new NavigationListAdapter(this, mNavigationListItem));

        genBasicKeyFragment = new BasicKeyFragment();
        genBasicKeyFragment2 = new BasicKeyFragment();
        genAdvancedKeyFragment = new AdvancedKeyFragment();
    }

    public void loadHome(){
        if(homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        changeFragment(homeFragment);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // Position 0 is Home
        if(position < 1){
            loadHome();
        }else{
            BasicKeyFragment basicKeyFragment = genBasicKeyFragment;
            if(activeFragment == genBasicKeyFragment){
                basicKeyFragment = genBasicKeyFragment2;
            }
            // SlideIn depending on the Position of the selectedItem compared to the Last
            if(position > activePosition){
                basicKeyFragment.setEnterTransition(new Slide(Gravity.BOTTOM));
            }else{
                basicKeyFragment.setEnterTransition(new Slide(Gravity.TOP));
            }

            position--;
            basicKeyFragment.setIrCodes(IRCodesList.get(position));
            genAdvancedKeyFragment.setUp(IRCodesList.get(position), irTransmitter);
            activeSceneState = SCENE_STATE_BASIC;
            changeFragment(basicKeyFragment);
        }
        activePosition = position;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        if(activeFragment != homeFragment) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.main, menu);
        }
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_switch_key) {
            if(activeSceneState == SCENE_STATE_BASIC) {
                activeSceneState = SCENE_STATE_ADVANCED;
                item.setTitle(getString(R.string.action_basic_switch));
                item.setIcon(R.drawable.ic_chevron_left_white_48dp);
                genBasicKeyFragment.setEnterTransition(new Slide(Gravity.LEFT));
                changeFragment(genAdvancedKeyFragment);
            }else{
                activeSceneState = SCENE_STATE_BASIC;
                item.setTitle(getString(R.string.action_advance_switch));
                item.setIcon(R.drawable.ic_chevron_right_white_48dp);
                changeFragment(genBasicKeyFragment);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if(mNavigationDrawerFragment.isDrawerOpen()){
            mNavigationDrawerFragment.closeDrawer();
        }else{
            super.onBackPressed();
        }
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
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.commit();
        activeFragment = fragment;
    }

}
