package com.example.hw11.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.hw11.R;
import com.example.hw11.controller.fragment.ListFragment;
import com.example.hw11.controller.fragment.StartFragment;

public class ListActivity extends AppCompatActivity {

    public static final String EXTRA_START_NAME = "com.example.hw11.controller.activity.name";
    public static final String EXTRA_START_COUNT = "com.example.hw11.controller.activity.count";

    public static Intent newIntent(Context src, String name, int count) {
        Intent intent = new Intent(src, ListActivity.class);
        intent.putExtra(EXTRA_START_NAME, name);
        intent.putExtra(EXTRA_START_COUNT, count);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().hide(); //hide the title bar

        FragmentManager fragmentManager=getSupportFragmentManager();
        Fragment fragment=fragmentManager.findFragmentById(R.id.container_fragment_list);
        if (fragment==null){
            Intent intent=getIntent();
            String name=intent.getStringExtra(EXTRA_START_NAME);
            int count=intent.getIntExtra(EXTRA_START_COUNT,0);
            fragmentManager
                    .beginTransaction()
                    .add(R.id.container_fragment_list, ListFragment.newInstance(name,count))
                    .commit();
        }
    }
}