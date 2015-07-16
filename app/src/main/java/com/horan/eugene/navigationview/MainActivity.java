package com.horan.eugene.navigationview;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mNavigationDrawer;
    private Fragment fragment;
    private Toolbar toolbar;
    private NavigationView mNavigationView;

    public static final String NAV_ITEM_ID = "navItemId";
    private int mNavItemId;

    public static final String NAV_MENU_VIEW = "navInflatedMenu";
    boolean navMenuFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            mNavItemId = savedInstanceState.getInt(NAV_ITEM_ID);
            navMenuFirst = savedInstanceState.getBoolean(NAV_MENU_VIEW);
        } else {
            mNavItemId = R.id.nav_frag_one;
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_menu_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCloseNavDrawer();
            }
        });

        mNavigationView = (NavigationView) findViewById(R.id.nav);
        // Handles the menu within the Navigation View
        if (navMenuFirst) {
            mNavigationView.inflateMenu(R.menu.nav_menu);
            mNavigationView.getMenu().findItem(mNavItemId).setChecked(true);
        } else {
            mNavigationView.inflateMenu(R.menu.nav_menu_two);
        }


        mNavigationDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.nav_settings) {
                    Toast.makeText(MainActivity.this, "Settings Clicked", Toast.LENGTH_SHORT).show();
                } else if (menuItem.getItemId() == R.id.nav_test_one) {
                    Toast.makeText(MainActivity.this, "Add Account Clicked", Toast.LENGTH_SHORT).show();
                } else if (menuItem.getItemId() == R.id.nav_test_two) {
                    Toast.makeText(MainActivity.this, "Manage Account Clicked", Toast.LENGTH_SHORT).show();
                } else {
                    mNavItemId = menuItem.getItemId();
                    switchFragment(menuItem.getItemId());
                    menuItem.setChecked(true);
                }
                OpenCloseNavDrawer();
                return false;
            }
        });

        RelativeLayout header = (RelativeLayout) findViewById(R.id.header);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView arrow = (ImageView) findViewById(R.id.arrow);
                if (navMenuFirst) {
                    arrow.setImageResource(R.mipmap.ic_arrow_drop_up_white_24dp);
                    mNavigationView.getMenu().clear();
                    mNavigationView.inflateMenu(R.menu.nav_menu_two);
                    navMenuFirst = false;
                } else {
                    arrow.setImageResource(R.mipmap.ic_arrow_drop_down_white_24dp);
                    mNavigationView.getMenu().clear();
                    mNavigationView.inflateMenu(R.menu.nav_menu);
                    mNavigationView.getMenu().findItem(mNavItemId).setChecked(true);
                    navMenuFirst = true;
                }
            }
        });

        switchFragment(mNavItemId);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NAV_ITEM_ID, mNavItemId);
        outState.putBoolean(NAV_MENU_VIEW, navMenuFirst);
    }

    /**
     * Handles Navigation Logic
     */
    private void switchFragment(int menuId) {
        switch (menuId) {
            case R.id.nav_frag_one:
                fragment = new FragmentOne();
                toolbar.setTitle("Fragment One");
                break;
            case R.id.nav_frag_two:
                fragment = new FragmentTwo();
                toolbar.setTitle("Fragment Two");
                break;
            default:
                break;
        }
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        }
    }

    /**
     * Used to handle the closing and opening of the Navigation Drawer.
     * Prevent repetitive statements
     */
    private void OpenCloseNavDrawer() {
        if (mNavigationDrawer != null) {
            if (mNavigationDrawer.isDrawerOpen(GravityCompat.START)) {
                mNavigationDrawer.closeDrawer(GravityCompat.START);
            } else {
                mNavigationDrawer.openDrawer(GravityCompat.START);
            }
        }
    }
}
