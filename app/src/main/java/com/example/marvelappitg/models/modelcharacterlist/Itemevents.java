
package com.example.marvelappitg.models.modelcharacterlist;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Itemevents implements Parcelable {

    @SerializedName("resourceURI")
    @Expose
    private String resourceURI;
    @SerializedName("name")
    @Expose
    private String name;

    protected Itemevents(Parcel in) {
        resourceURI = in.readString();
        name = in.readString();
    }

    public static final Creator<Itemevents> CREATOR = new Creator<Itemevents>() {
        @Override
        public Itemevents createFromParcel(Parcel in) {
            return new Itemevents(in);
        }

        @Override
        public Itemevents[] newArray(int size) {
            return new Itemevents[size];
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
