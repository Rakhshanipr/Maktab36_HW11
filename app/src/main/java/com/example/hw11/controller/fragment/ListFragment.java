package com.example.hw11.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hw11.R;

public class ListFragment extends Fragment {

    public static final String ARG_LISTFRAGMENT_NAME = "name";
    public static final String ARG_LISTFRAGMENT_COUNT = "count";

    public static ListFragment newInstance(String name, int count) {

        Bundle args = new Bundle();
        args.putString(ARG_LISTFRAGMENT_NAME,name);
        args.putInt(ARG_LISTFRAGMENT_COUNT,count);
        ListFragment fragment = new ListFragment();
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
        return inflater.inflate(R.layout.fragment_list, container, false);
    }
}