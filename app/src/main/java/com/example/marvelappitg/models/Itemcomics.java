
package com.example.marvelappitg.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Itemcomics implements Parcelable {

    @SerializedName("resourceURI")
    @Expose
    private String resourceURI;
    @SerializedName("name")
    @Expose
    private String name;

    protected Itemcomics(Parcel in) {
        resourceURI = in.readString();
        name = in.readString();
    }

    public static final Creator<Itemcomics> CREATOR = new Creator<Itemcomics>() {
        @Override
        public Itemcomics createFromParcel(Parcel in) {
            return new Itemcomics(in);
        }

        @Override
        public Itemcomics[] newArray(int size) {
            return new Itemcomics[size];
        }
    };

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(resourceURI);
        dest.writeString(name);
    }
}
