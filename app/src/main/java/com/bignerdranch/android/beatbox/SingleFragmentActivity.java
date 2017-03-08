package com.bignerdranch.android.beatbox;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.AbstractCollection;

/**
 * Created by hasee on 2017/3/6.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {
    protected abstract Fragment createFragment();

    protected int getLayoutResId() {
        return R.layout.activity_baet_box;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        FragmentManager manager  =  getSupportFragmentManager();
        Fragment fragment= manager.findFragmentById(R.id.fragmentContainer);
        if(fragment == null)
        {
            fragment  = createFragment();
            manager.beginTransaction()
                    .add(R.id.fragmentContainer,fragment)
                    .commit();
        }
    }
    }

