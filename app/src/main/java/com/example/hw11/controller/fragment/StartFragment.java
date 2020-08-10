package com.example.hw11.controller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.hw11.R;
import com.example.hw11.controller.activity.ListActivity;
import com.example.hw11.model.State;
import com.example.hw11.repository.TaskRepository;

public class StartFragment extends Fragment {

    //region initialization
     Button mStartButton;
   static EditText mEditTextName;
    EditText mEditTextCount;

    //endregion
    public static StartFragment newInstance() {
        Bundle args = new Bundle();
        StartFragment fragment = new StartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        findViews(view);
        setOnclickListner();
        return view;
    }

    private void setOnclickListner() {
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskRepository.getInstance().createListByCount(mEditTextName.getText().toString(),Integer.parseInt(mEditTextCount.getText().toString()));
                Intent intent = ListActivity.newIntent(getContext()
                        , State.Todo);
                startActivity(intent);
            }
        });
    }

    private void findViews(View view) {
        mStartButton = view.findViewById(R.id.button_start);
        mEditTextCount = view.findViewById(R.id.edittext_count);
        mEditTextName = view.findViewById(R.id.edittext_name);
    }
}