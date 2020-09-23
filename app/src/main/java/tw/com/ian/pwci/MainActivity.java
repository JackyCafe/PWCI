package tw.com.ian.pwci;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import tw.com.ian.pwci.Fragments.ChatFragment;
import tw.com.ian.pwci.Fragments.GameFragment;
import tw.com.ian.pwci.Fragments.MedicalFragment;
import tw.com.ian.pwci.Fragments.NumberFragment;
import tw.com.ian.pwci.Fragments.SetupFragment;
import tw.com.ian.pwci.Service.OnBootService;


public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestRecordAudioPermission();
        initToggle();

    }



    private void initToggle() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawer =  (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.drawer_open,R.string.drawer_close);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.setDrawerSlideAnimationEnabled(true);
        toggle.syncState();
        drawer.addDrawerListener(toggle);
        navigationView = (NavigationView) findViewById(R.id.nvView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                Class fragmentClass;
                switch(menuItem.getItemId()) {
                    case R.id.medical_fragment:
                        fragmentClass = MedicalFragment.class;
                        break;
                    case R.id.chat_fragment:
                        fragmentClass = ChatFragment.class;
                        break;
                    case R.id.game_fragment:
                        fragmentClass = GameFragment.class;
                        break;
                    case R.id.number_fragment:
                        fragmentClass = NumberFragment.class;
                        break;
                    case R.id.setup_fragment:
                        fragmentClass = SetupFragment.class;
                        break;
                    default:
                        fragmentClass = MedicalFragment.class;
                }

                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                    // Insert the fragment by replacing any existing fragment
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
                } catch (Exception e) {
                   for (StackTraceElement s :e.getStackTrace())
                   {
                       Log.v("Jacky",s.toString());
                   }
                }



                // Highlight the selected item has been done by NavigationView
                menuItem.setChecked(true);
                // Set action bar title
                setTitle(menuItem.getTitle());
                // Close the navigation drawer
                drawer.closeDrawers();
                return true;
            }
        });
    }


    private void requestRecordAudioPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String requiredPermission = Manifest.permission.RECORD_AUDIO;
            if (checkCallingOrSelfPermission(requiredPermission) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{requiredPermission}, 101);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




}
