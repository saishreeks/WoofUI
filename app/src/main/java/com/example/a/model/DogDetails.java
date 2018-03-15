package com.example.a.model;

import java.util.Collection;
import java.util.Date;

/**
 * Created by A on 3/7/2018.
 */

public class DogDetails {

    private Integer dogId;

    private String name;

    private Date dob;


    private String breed;

    private String pic;

    private Collection<Matereq> matereqCollection;

    private Collection<WalkInfo> walkInfoCollection;

    private Collection<Mateinfo> mateinfoCollection;

    public void setMateinfoCollection1(Collection<Mateinfo> mateinfoCollection1) {
        this.mateinfoCollection1 = mateinfoCollection1;
    }

    private Collection<Mateinfo> mateinfoCollection1;



    //private Collection<Dogpics> dogpicsCollection;

    private OwnerDetails ownerId;

    public DogDetails() {
    }

    public DogDetails(Integer dogId) {
        this.dogId = dogId;
    }

    public Integer getDogId() {
        return dogId;
    }

    public void setDogId(Integer dogId) {
        this.dogId = dogId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }


    public Collection<Matereq> getMatereqCollection() {
        return matereqCollection;
    }

    public void setMatereqCollection(Collection<Matereq> matereqCollection) {
        this.matereqCollection = matereqCollection;
    }


    public Collection<WalkInfo> getWalkInfoCollection() {
        return walkInfoCollection;
    }

    public void setWalkInfoCollection(Collection<WalkInfo> walkInfoCollection) {
        this.walkInfoCollection = walkInfoCollection;
    }


    public Collection<Mateinfo> getMateinfoCollection() {
        return mateinfoCollection;
    }


    public Collection<Mateinfo> getMateinfoCollection1() {
        return mateinfoCollection1;
    }

    public void setMateinfoCollection(Collection<Mateinfo> mateinfoCollection) {
        this.mateinfoCollection = mateinfoCollection;
    }



//    public Collection<Dogpics> getDogpicsCollection() {
//        return dogpicsCollection;
//    }
//
//    public void setDogpicsCollection(Collection<Dogpics> dogpicsCollection) {
//        this.dogpicsCollection = dogpicsCollection;
//    }

    public OwnerDetails getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(OwnerDetails ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dogId != null ? dogId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DogDetails)) {
            return false;
        }
        DogDetails other = (DogDetails) object;
        if ((this.dogId == null && other.dogId != null) || (this.dogId != null && !this.dogId.equals(other.dogId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "webw.DogDetails[ dogId=" + dogId + " ]";
    }
}
