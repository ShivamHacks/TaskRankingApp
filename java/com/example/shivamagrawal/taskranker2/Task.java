package com.example.shivamagrawal.taskranker2;

import android.os.Parcelable;
import android.os.Parcel;

public class Task implements Parcelable {

    private int[] time;
    private int[] date;
    private String task;
    private int importance;
    private int size;
    private int difficulty;
    private int completed;

    public Task(int[] time, int[] date, String task, int importance, int size, int difficulty, int completed) {
        this.time = time;
        this.date = date;
        this.task = task;
        this.importance = importance;
        this.size = size;
        this.difficulty = difficulty;
        this.completed = completed;
    }

    public Task(Parcel in) {
        time = new int[2];
        in.readIntArray(time);
        date = new int[3];
        in.readIntArray(date);
        task = in.readString();
        importance = in.readInt();
        size = in.readInt();
        difficulty = in.readInt();
        completed = in.readInt();
    }

    public int[] getTime() { return time; }
    public void setTime(int[] time) { this.time = time; }

    public int[] getDate() { return date; }
    public void setDate(int[] date) { this.date = date; }

    public String getTask() { return task; }
    public void setTask(String task) { this.task = task; }

    public int getImportance() { return importance; }
    public void setImportance(int importance) { this.importance = importance; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    public int getDifficulty() { return difficulty; }
    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }

    public int getCompleted() { return completed; }
    public void setCompleted(int completed) { this.completed = completed; }

    /* everything below here is for implementing Parcelable */

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeIntArray(time);
        out.writeIntArray(date);
        out.writeString(task);
        out.writeInt(importance);
        out.writeInt(size);
        out.writeInt(difficulty);
        out.writeInt(completed);
    }

    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

}
