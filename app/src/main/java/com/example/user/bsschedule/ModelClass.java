package com.example.user.bsschedule;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelClass implements Parcelable
{
    private String date;
    private String day;
    private String place;
    private String subject;
    private String speaker;
    private String time;
    private String classType;

    public ModelClass() {
        
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public static Creator<ModelClass> getCREATOR() {
        return CREATOR;
    }

        ModelClass(Parcel in) {
        date = in.readString();
        day = in.readString();
        place = in.readString();
        subject = in.readString();
        speaker = in.readString();
        time = in.readString();
        classType = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(day);
        dest.writeString(place);
        dest.writeString(subject);
        dest.writeString(speaker);
        dest.writeString(time);
        dest.writeString(classType);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ModelClass> CREATOR = new Parcelable.Creator<ModelClass>() {
        @Override
        public ModelClass createFromParcel(Parcel in) {
            return new ModelClass(in);
        }

        @Override
        public ModelClass[] newArray(int size) {
            return new ModelClass[size];
        }
    };
}