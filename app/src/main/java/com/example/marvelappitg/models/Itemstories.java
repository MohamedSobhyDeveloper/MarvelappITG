
package com.example.marvelappitg.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Itemstories implements Parcelable {

    @SerializedName("resourceURI")
    @Expose
    private String resourceURI;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;

    protected Itemstories(Parcel in) {
        resourceURI = in.readString();
        name = in.readString();
        type = in.readString();
    }

    public static final Creator<Itemstories> CREATOR = new Creator<Itemstories>() {
        @Override
        public Itemstories createFromParcel(Parcel in) {
            return new Itemstories(in);
        }

        @Override
        public Itemstories[] newArray(int size) {
            return new Itemstories[size];
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(resourceURI);
        dest.writeString(name);
        dest.writeString(type);
    }
}
