
package com.example.marvelappitg.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comics implements Parcelable {

    @SerializedName("available")
    @Expose
    private Integer available;
    @SerializedName("collectionURI")
    @Expose
    private String collectionURI;
    @SerializedName("items")
    @Expose
    private List<Itemcomics> itemcomics = null;
    @SerializedName("returned")
    @Expose
    private Integer returned;

    protected Comics(Parcel in) {
        if (in.readByte() == 0) {
            available = null;
        } else {
            available = in.readInt();
        }
        collectionURI = in.readString();
        itemcomics = in.createTypedArrayList(Itemcomics.CREATOR);
        if (in.readByte() == 0) {
            returned = null;
        } else {
            returned = in.readInt();
        }
    }

    public static final Creator<Comics> CREATOR = new Creator<Comics>() {
        @Override
        public Comics createFromParcel(Parcel in) {
            return new Comics(in);
        }

        @Override
        public Comics[] newArray(int size) {
            return new Comics[size];
        }
    };

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public List<Itemcomics> getItemcomics() {
        return itemcomics;
    }

    public void setItemcomics(List<Itemcomics> itemcomics) {
        this.itemcomics = itemcomics;
    }

    public Integer getReturned() {
        return returned;
    }

    public void setReturned(Integer returned) {
        this.returned = returned;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (available == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(available);
        }
        dest.writeString(collectionURI);
        dest.writeTypedList(itemcomics);
        if (returned == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(returned);
        }
    }
}
