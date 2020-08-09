package com.example.hw11.controller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hw11.R;
import com.example.hw11.model.Task;
import com.example.hw11.repository.TaskRepository;

import java.util.List;

public class ListFragment extends Fragment {
    public int mColorCount = 0;

    public static final String ARG_LISTFRAGMENT_NAME = "com.example.hw11.controller.fragment.name";
    public static final String ARG_LISTFRAGMENT_COUNT = "com.example.hw11.controller.fragment.count";

    public static ListFragment newInstance(String name, int count) {

        Bundle args = new Bundle();
        args.putString(ARG_LISTFRAGMENT_NAME, name);
        args.putInt(ARG_LISTFRAGMENT_COUNT, count);
        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    RecyclerView mRecyclerView;
    int mCount;
    String mName;
    TaskRepository mTaskRepository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTaskRepository = TaskRepository.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        findViews(view);
        setInitialization();
        return view;
    }

    private void setInitialization() {
        mName = getArguments().getString(ARG_LISTFRAGMENT_NAME);
        mCount = getArguments().getInt(ARG_LISTFRAGMENT_COUNT);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mTaskRepository.createListByCount(mName, mCount);
        mRecyclerView.setAdapter(new MyAdapter(mTaskRepository.getList()));

    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerview_fragment_list_task);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewName;
        private TextView mTextViewState;
        private LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linearlayout_list_task);
            mTextViewName = itemView.findViewById(R.id.textview_name);
            mTextViewState = itemView.findViewById(R.id.textview_state);
        }

        public void setItem(Task task) {
            mTextViewName.setText(task.getTitle());
            mTextViewState.setText(task.getState().toString());
            if (mColorCount++ % 2 == 0)
                linearLayout.setBackgroundColor(getResources().getColor(R.color.green));
            else
                linearLayout.setBackgroundColor(getResources().getColor(R.color.red));

        }
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        List<Task> mTaskList;

        public MyAdapter(List<Task> taskList) {
            mTaskList = taskList;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_list_task, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.setItem(mTaskList.get(position));
        }


        @Override
        public int getItemCount() {
            return mTaskList.size();
        }
    }
}