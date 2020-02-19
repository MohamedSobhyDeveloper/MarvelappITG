
package com.example.marvelappitg.models.modelcharacterlist;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Itemseries implements Parcelable {

    @SerializedName("resourceURI")
    @Expose
    private String resourceURI;
    @SerializedName("name")
    @Expose
    private String name;

    protected Itemseries(Parcel in) {
        resourceURI = in.readString();
        name = in.readString();
    }

    public static final Creator<Itemseries> CREATOR = new Creator<Itemseries>() {
        @Override
        public Itemseries createFromParcel(Parcel in) {
            return new Itemseries(in);
        }

        @Override
        public Itemseries[] newArray(int size) {
            return new Itemseries[size];
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
