package com.kis.kistools;

public class ScheduleOn {
    String titleschedule;
    String teacherschedule;
    String timeschedule;
    String idschedule;

    public ScheduleOn() {
    }

    public ScheduleOn(String titleschedule, String teacherschedule, String timeschedule, String idschedule) {
        this.titleschedule = titleschedule;
        this.teacherschedule = teacherschedule;
        this.timeschedule = timeschedule;
        this.idschedule = idschedule;
    }

    public String getIdschedule() {
        return idschedule;
    }

    public void setIdschedule(String idschedule) {
        this.idschedule = idschedule;
    }

    public String getTitleschedule() {
        return titleschedule;
    }

    public void setTitleschedule(String titleschedule) {
        this.titleschedule = titleschedule;
    }

    public String getTeacherschedule() {
        return teacherschedule;
    }

    public void setTeacherschedule(String teacherschedule) {
        this.teacherschedule = teacherschedule;
    }

    public String getTimeschedule() {
        return timeschedule;
    }

    public void setTimeschedule(String timeschedule) {
        this.timeschedule = timeschedule;
    }
}
