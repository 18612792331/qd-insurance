package com.qding.api.call.app.qding.v2_3_0.struct.watch;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.passport.struct.response.location.Address;

import java.io.Serializable;

/**
 * Created by qd on 2016/5/24.
 */
public class WatchLocation implements Serializable {

    @ExplainAnnotation(explain = "地址描述")
    private String addressDescription;


    @ExplainAnnotation(explain = "经度")
    private String longitude;


    @ExplainAnnotation(explain = "纬度")
    private String latitude;


    @ExplainAnnotation(explain = "精度")
    private String accuracy;

    @ExplainAnnotation(explain = "地址")
    private Address address;

    public String getAddressDescription() {
        return addressDescription;
    }

    public void setAddressDescription(String addressDescription) {
        this.addressDescription = addressDescription;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Location{" +
                "addressDescription='" + addressDescription + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", accuracy='" + accuracy + '\'' +
                ", address=" + address +
                '}';
    }
}
