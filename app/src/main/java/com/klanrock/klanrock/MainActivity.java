package com.klanrock.klanrock;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //viewPager
    private ViewPager viewPager;
    private BottomNavigationView BottomNavigation;
    //Fragments
    HomeFragment HomeFragment;
    MyorderFragment MyOrderFragment;
    JadwalFragment JadwalFragment;
    MenuItem prevMenuItem;
    String id,nama;
    SharedPreferences sharedPreferences;
    TextView nama_pelanggan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences(LoginActivity.my_session,Context.MODE_PRIVATE);
        //Get data login
        id = getIntent().getStringExtra("id");
        nama = getIntent().getStringExtra(LoginActivity.TAG_NAMA);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View nav_view = navigationView.getHeaderView(0);
        nama_pelanggan = (TextView) nav_view.findViewById(R.id.name);
        nama_pelanggan.setText(nama);
//        Toast.makeText(MainActivity.this,nama,Toast.LENGTH_LONG).show();

        BottomNavigation = (BottomNavigationView) findViewById(R.id.navigation_bottom);
        BottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.navigation_myorder:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.navigation_jadwal:
                                viewPager.setCurrentItem(2);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    BottomNavigation.getMenu().getItem(0).setChecked(false);
                }
                BottomNavigation.getMenu().getItem(position).setChecked(true);
                prevMenuItem = BottomNavigation.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(viewPager);
    }



    //Coding menu navigasi samping
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_atas, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_account) {
            // Handle the camera action
        } else if (id == R.id.nav_log_out) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(LoginActivity.session_status,false);
            editor.putString(LoginActivity.TAG_USERNAME,null);
            editor.putString(LoginActivity.TAG_NAMA,null);
            editor.putString(LoginActivity.TAG_ID_LOGIN,null);
            editor.putString(LoginActivity.TAG_ID_PELANGGAN,null);
            editor.commit();
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            finish();
            startActivity(intent);

        }
        else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(MainActivity.this,GaleryActivity.class);
            startActivity(intent);
        }
//        else if (id == R.id.nav_manage) {
//
//        }
////        else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Coding view pager
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        HomeFragment = new HomeFragment();
        MyOrderFragment = new MyorderFragment();
        JadwalFragment = new JadwalFragment();

        adapter.addFragment(HomeFragment);
        adapter.addFragment(MyOrderFragment);
        adapter.addFragment(JadwalFragment);
        viewPager.setAdapter(adapter);
    }

    public void openPaket(View view) {
        Intent intent = new Intent(MainActivity.this,OrderActivity.class);
        startActivity(intent);
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }

    }


}
