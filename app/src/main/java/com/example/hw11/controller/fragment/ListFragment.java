package com.example.hw11.controller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hw11.R;
import com.example.hw11.model.State;
import com.example.hw11.model.Task;
import com.example.hw11.repository.TaskRepository;

import java.util.List;

public class ListFragment extends Fragment {
    //region initialization
    public int mColorCount = 0;
    MyAdapter mMyAdapter;
    Button mButtonAddTask;
    RecyclerView mRecyclerView;
    State mState;
    TaskRepository mTaskRepository;
    //endregion
    //region initialization static
    public static final String ARG_STATE = "com.example.hw11.controller.fragment.state";
//    public static final String ARG_NAME = "com.example.hw11.controller.fragment.state";

    // method
    public static ListFragment newInstance(int intState) {
        State state = State.values()[intState];
        Bundle args = new Bundle();
        args.putSerializable(ARG_STATE, state);
        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    //endregion

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTaskRepository = TaskRepository.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        findViews(view);
        setOnClickListner();
        setInitialization();
        return view;
    }

    private void setOnClickListner() {
        mButtonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTaskRepository.add(new Task(StartFragment.mEditTextName.getText().toString(), mState));
                updateRecyclerView();
            }
        });
    }

    private void updateRecyclerView() {
        mMyAdapter = new MyAdapter(mTaskRepository.getList(mState));

        mRecyclerView.setAdapter(mMyAdapter);
    }

    private void setInitialization() {
        mState = (State) getArguments().getSerializable(ARG_STATE);
//      mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (mMyAdapter == null) {
            mMyAdapter = new MyAdapter(mTaskRepository.getList(mState));
        } else
            mMyAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mMyAdapter);
    }

    private void findViews(View view) {
        mButtonAddTask = view.findViewById(R.id.button_add_task);
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