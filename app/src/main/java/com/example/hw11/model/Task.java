package com.example.hw11.model;

import java.util.UUID;

public class Task {
    private UUID mId;
private String mTitle;
private State mState;

    public Task() {

    }

    public String getTitle() {
        return mTitle;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public State getState() {
        return mState;
    }

    public void setState(State state) {
        mState = state;
    }

    public Task(String title, State state) {
        mTitle = title;
        mState = state;
        mId=UUID.randomUUID();
    }
}
