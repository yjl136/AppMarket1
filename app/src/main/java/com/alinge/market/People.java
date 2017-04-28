package com.alinge.market;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-03-17 11:42
 * Describe:
 */
public final class People implements Parcelable{
    public int age;
    public int num;
    public String name;
    public People(){

    }
    public People(Parcel in){
        age=in.readInt();
        num=in.readInt();
        name=in.readString();
    }

    public People(String name, int num, int age) {
        this.name = name;
        this.num = num;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

        @Override
        public void writeToParcel(Parcel out, int i) {
            out.writeInt(age);
            out.writeInt(num);
            out.writeString(name);
        }
    public final static Parcelable.Creator<People> CREATOR=new Parcelable.Creator<People>(){
        @Override
        public People createFromParcel(Parcel parcel) {
            return new People(parcel);
        }

        @Override
        public People[] newArray(int size) {
            return new People[size];
        }
    };


}
