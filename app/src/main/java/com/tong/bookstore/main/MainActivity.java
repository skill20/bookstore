package com.tong.bookstore.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.tong.bookstore.BookStoreFragment;
import com.tong.bookstore.MarketFragment;
import com.tong.bookstore.MyBookFragment;
import com.tong.bookstore.R;
import com.tong.bookstore.SettingFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigation;
    private MyBookFragment myBookFragment;
    private FragmentManager fragmentManager;
    private BookStoreFragment bookStoreFragment;
    private MarketFragment marketFragment;
    private SettingFragment settingFragment;
    private Fragment tempFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewByIds();
        initViews();
    }

    private void initViews() {
        setSupportActionBar(toolBar);
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.open, R.string.close);
        mActionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(mActionBarDrawerToggle);
        navigation.setNavigationItemSelectedListener(this);
        navigation.setCheckedItem(R.id.nav_menu_my_book);

        fragmentManager = getSupportFragmentManager();
        myBookFragment = new MyBookFragment();
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, myBookFragment).commit();
        tempFragment = myBookFragment;
    }

    private void findViewByIds() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolBar = (Toolbar) findViewById(R.id.toolbar);
        navigation = (NavigationView) findViewById(R.id.navigation);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;
        String title = "";

        switch (item.getItemId()) {
            case R.id.nav_menu_my_book:
                title = (String) item.getTitle();

                if (myBookFragment == null) {
                    myBookFragment = new MyBookFragment();
                }
                fragment = myBookFragment;

                break;
            case R.id.nav_menu_book_store:
                title = (String) item.getTitle();

                if (bookStoreFragment == null) {
                    bookStoreFragment = new BookStoreFragment();
                }
                fragment = bookStoreFragment;

                break;
            case R.id.nav_menu_market:
                title = (String) item.getTitle();

                if (marketFragment == null) {
                    marketFragment = new MarketFragment();
                }
                fragment = marketFragment;

                break;
            case R.id.nav_menu_setting:
                title = (String) item.getTitle();

                if (settingFragment == null) {
                    settingFragment = new SettingFragment();
                }
                fragment = settingFragment;

                break;
        }

        item.setChecked(true);
        drawerLayout.closeDrawers();
        toolBar.setTitle(title);
        switchContent(tempFragment, fragment);
        return true;
    }

    public void switchContent(Fragment from, Fragment to) {
        if (tempFragment != to) {
            tempFragment = to;
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(R.id.fragment_layout, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }
}
