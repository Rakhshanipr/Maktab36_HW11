package com.example.hw11.repository;

import com.example.hw11.model.State;
import com.example.hw11.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskRepository implements RepositoryInterface<Task> {

    private static List<Task> sTaskList;
    public static TaskRepository mTaskRepository;
    private TaskRepository(){

    }

    public static TaskRepository getInstance(){
        if (mTaskRepository==null)
            mTaskRepository=new TaskRepository();
        return mTaskRepository;
    }


    @Override
    public void add(Task task) {
        sTaskList.add(task);
    }

    @Override
    public void insertList(List<Task> tasks) {
        sTaskList=new ArrayList<>();
        sTaskList=tasks;
    }

    public void createListByCount(String name,int count){
        sTaskList=new ArrayList<>();
        for (int i=0;i<count;i++){
//            Task task=;new Task("name", State.values()[i%3]);
            sTaskList.add(new Task(name, State.values()[i%3]));
        }
    }

    @Override
    public void update(Task e) {
        Task task1=get(e.getId());
        task1.setState(e.getState());
        task1.setTitle(e.getTitle());
    }

    @Override
    public void delete(Task task) {
        sTaskList.remove(task);
    }

    @Override
    public void delete(UUID uuid) {
        for (int i=0;i<sTaskList.size();i++){
            if (sTaskList.get(i).getId()==uuid){
                sTaskList.remove(i);
                return;
            }
        }
    }

    @Override
    public Task get(UUID uuid) {
        for (int i=0;i<sTaskList.size();i++){
            if (sTaskList.get(i).getId()==uuid){
                return sTaskList.get(i);
            }
        }
    return null;
    }

    @Override
    public List<Task> getList() {
        return sTaskList;
    }
}
