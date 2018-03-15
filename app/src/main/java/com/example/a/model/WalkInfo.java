package com.example.a.model;

import java.util.Date;

/**
 * Created by A on 3/7/2018.
 */

public class WalkInfo {
    private Integer walkInfoId;

    private Date walkInfoDate;

    private DogDetails dogId;

    private OwnerDetails walkerId;
    private Date walkTime;


//    private Collection<WalkReq> walkReqCollection;

    public WalkInfo() {
    }

    public WalkInfo(Integer walkInfoId) {
        this.walkInfoId = walkInfoId;
    }

    public Integer getWalkInfoId() {
        return walkInfoId;
    }

    public void setWalkInfoId(Integer walkInfoId) {
        this.walkInfoId = walkInfoId;
    }

    public Date getWalkInfoDate() {
        if(walkInfoDate==null)
            return new Date();
        return walkInfoDate;
    }

    public void setWalkInfoDate(Date walkInfoDate) {
        this.walkInfoDate = walkInfoDate;
    }

    public DogDetails getDogId() {
        return dogId;
    }

    public void setDogId(DogDetails dogId) {
        this.dogId = dogId;
    }

    public OwnerDetails getWalkerId() {
        return walkerId;
    }

    public void setWalkerId(OwnerDetails walkerId) {
        this.walkerId = walkerId;
    }

//    @XmlTransient
//    public Collection<WalkReq> getWalkReqCollection() {
//        return walkReqCollection;
//    }
//
//    public void setWalkReqCollection(Collection<WalkReq> walkReqCollection) {
//        this.walkReqCollection = walkReqCollection;
//    }

    public Date getWalkTime() {
        return walkTime;
    }

    public void setWalkTime(Date walkTime) {
        this.walkTime = walkTime;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (walkInfoId != null ? walkInfoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WalkInfo)) {
            return false;
        }
        WalkInfo other = (WalkInfo) object;
        if ((this.walkInfoId == null && other.walkInfoId != null) || (this.walkInfoId != null && !this.walkInfoId.equals(other.walkInfoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "webwoof.WalkInfo[ walkInfoId=" + walkInfoId + " ]";
    }
}