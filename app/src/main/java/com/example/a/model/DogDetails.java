package com.example.a.model;

/**
 * Created by saishree on 3/8/18.
 */

import java.util.Date;

public class DogDetails {

    private static final long serialVersionUID = 1L;

    private Integer dogId;

    private String name;

    private Date dob;

    private String pic;

//    private Collection<Matereq> matereqCollection;
//    @OneToMany(mappedBy = "dogId")
//    private Collection<WalkInfo> walkInfoCollection;
//    @OneToMany(mappedBy = "dogId")
//    private Collection<Mateinfo> mateinfoCollection;
//    @OneToMany(mappedBy = "dogId")
//    private Collection<Dogpics> dogpicsCollection;
//    @JoinColumn(name = "owner_id", referencedColumnName = "owner_id")
//    @ManyToOne
    private OwnerDetails ownerId;
    private String breedName;

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
        return breedName;
    }

    public void setBreed(String breedName) {
        this.breedName = breedName;
    }

//    @XmlTransient
//    public Collection<Matereq> getMatereqCollection() {
//        return matereqCollection;
//    }
//
//    public void setMatereqCollection(Collection<Matereq> matereqCollection) {
//        this.matereqCollection = matereqCollection;
//    }
//
//    @XmlTransient
//    public Collection<WalkInfo> getWalkInfoCollection() {
//        return walkInfoCollection;
//    }
//
//    public void setWalkInfoCollection(Collection<WalkInfo> walkInfoCollection) {
//        this.walkInfoCollection = walkInfoCollection;
//    }
//
//    @XmlTransient
//    public Collection<Mateinfo> getMateinfoCollection() {
//        return mateinfoCollection;
//    }
//
//    public void setMateinfoCollection(Collection<Mateinfo> mateinfoCollection) {
//        this.mateinfoCollection = mateinfoCollection;
//    }
//
//    @XmlTransient
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
        return "web.DogDetails[ dogId=" + dogId + " ]";
    }

}
