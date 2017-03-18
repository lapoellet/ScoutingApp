package org.deltaroboticsftc.scoutingapp;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        int intversion = Build.VERSION.SDK_INT;

        TextView textTest = new TextView(this);
        textTest.setText("Test TextView");
        textTest.setLines(1);
        textTest.setTextSize(getResources().getDimension(R.dimen.appTextTitleSize));
        if(intversion < 23)
        {

            textTest.setTextColor(getResources().getColor(R.color.appTextTitleColor));

        }else
        {

            textTest.setTextColor(getResources().getColor(R.color.appTextTitleColor, null));

        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment contentFragment = null;
        Boolean setupMatch = false;

        if (id == R.id.nav_edit_match)
        {
            contentFragment = new MatchFragment(getApplicationContext(), false);
            this.changeFragment(contentFragment, true);
        }
        else if (id == R.id.nav_new_match)
        {
            contentFragment = new MatchFragment(getApplicationContext(), true);
            this.changeFragment(contentFragment, true);
        }
        else if (id == R.id.nav_review_match)
        {
            MatchSection test1 = new MatchSection("Auto", getApplicationContext());
            System.out.println("MatchSection test1: " + test1.wasSectionResourcesFound());

            LinearLayout conent = (LinearLayout)findViewById(R.id.content_frame);
            conent.addView(test1.getSectionLayout());
        }
        else if (id == R.id.nav_about)
        {

        }
        else if (id == R.id.nav_settings)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void changeFragment(Fragment moveTo, Boolean setupMatch)
    {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, moveTo);
        ft.commit();
    }
}
