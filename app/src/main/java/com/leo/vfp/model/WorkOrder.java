package com.leo.vfp.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by Leo on 2017-01-12.
 */

public class WorkOrder extends BmobObject {
    private String number;
    private String workshop;
    private String contract;
    private String materialCode;
    private String materialName;
    private int planProduce;
    private String finishedAt;

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(String finishedAt) {
        this.finishedAt = finishedAt;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getPlanProduce() {
        return planProduce;
    }

    public void setPlanProduce(int planProduce) {
        this.planProduce = planProduce;
    }

    public String getWorkshop() {
        return workshop;
    }

    public void setWorkshop(String workshop) {
        this.workshop = workshop;
    }
}
