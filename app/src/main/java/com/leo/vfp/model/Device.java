package com.leo.vfp.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by Leo on 2017-01-12.
 */

public class Device extends BmobObject{
    private String number;
    private String name;
    private String barcode;

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
