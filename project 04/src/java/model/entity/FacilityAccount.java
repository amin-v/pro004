package model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "FacilityAccount")
@Table
public class FacilityAccount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "realPersonId",referencedColumnName ="Id" ,nullable = false)
    private RealPerson realPerson;


    @Column(columnDefinition = "Number")
    private long duration;

    @Column(columnDefinition = "Number")
    private double amount;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "facilityProfileId",referencedColumnName ="id" , nullable = false)
    FacilityProfile facilityProfile;

    public FacilityAccount() {
    }

    public FacilityAccount(long duration, double amount) {
        this.duration = duration;
        this.amount = amount;
    }

    public long getId() {
        return Id;
    }

    public void setId(long loanFileId) {
        this.Id = loanFileId;
    }

    public RealPerson getRealPerson() {
        return realPerson;
    }

    public void setRealPerson(RealPerson realPerson) {
        this.realPerson = realPerson;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public FacilityProfile getFacilityProfile() {
        return facilityProfile;
    }

    public void setFacilityProfile(FacilityProfile facilityProfile) {
        this.facilityProfile = facilityProfile;
    }
}
