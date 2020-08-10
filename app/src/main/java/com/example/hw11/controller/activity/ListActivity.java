package com.example.hw11.controller.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.hw11.R;
import com.example.hw11.controller.fragment.ListFragment;
import com.example.hw11.model.State;
import com.example.hw11.model.Task;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    public static final String EXTRA_STATE = "com.example.hw11.controller.activity.name";
    ViewPager2 mViewPager2;

    public static Intent newIntent(Context src, String name, int count) {
        Intent intent = new Intent(src, ListActivity.class);
        intent.putExtra(EXTRA_STATE, name);
        return intent;
    }

    public static Intent newIntent(Context src, State state) {
        Intent intent = new Intent(src, ListActivity.class);
        intent.putExtra(EXTRA_STATE, state);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().hide(); //hide the title bar
        mViewPager2=findViewById(R.id.viewpager2_task_list);
        FragmentStateAdapter fragmentStateAdapter=new fragmentStateAdapter(this);
        mViewPager2.setAdapter(fragmentStateAdapter);
    }

    private class fragmentStateAdapter extends FragmentStateAdapter{
//        List<Task> mTaskList;
        public fragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
//            mTaskList=mTasks;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return ListFragment.newInstance(position);
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}