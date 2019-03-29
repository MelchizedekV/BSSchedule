package com.example.user.bsschedule;


import android.os.Parcel;
import android.os.Parcelable;

public class ModelClass implements Parcelable
  { String date;
    String day;
    String place;
    String subject;
    String speaker;

    public ModelClass()
    {
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

    public static Creator<ModelClass> getCREATOR() {
        return CREATOR;
    }

    protected ModelClass(Parcel in) {
        date = in.readString();
        day = in.readString();
        place = in.readString();
        subject = in.readString();
        speaker = in.readString();
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