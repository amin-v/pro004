package model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "facilityProfile")
@Table
public class FacilityProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(columnDefinition = "varchar2(50)", nullable = false)
    private String facilityName;

    @Column(columnDefinition = "Number", nullable = false)
    private String interestRate;

    @OneToMany(mappedBy = "facilityProfile", cascade = CascadeType.PERSIST)
    private List<GrantCondition> grantConditions = new ArrayList<GrantCondition>();


//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    @JoinColumn(name = "FK")


    public FacilityProfile() {

    }

    public FacilityProfile(String facilityName, String interestRate, List<GrantCondition> grantConditions) {
        this.facilityName = facilityName;
        this.interestRate = interestRate;
        this.grantConditions = grantConditions;
    }

    public FacilityProfile(String facilityName, String interestRate) {
        this.facilityName = facilityName;
        this.interestRate = interestRate;
    }

    public FacilityProfile(Long Id) {
        this.Id = Id;

    }

    public Long getId() {
        return Id;
    }

    public FacilityProfile setId(Long loanTypeId) {
        this.Id = loanTypeId;
        return this;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public FacilityProfile setFacilityName(String loanName) {
        this.facilityName = loanName;
        return this;
    }


    public String getInterestRate() {
        return interestRate;
    }

    public FacilityProfile setInterestRate(String interestRate) {
        this.interestRate = interestRate;
        return this;
    }

    public List<GrantCondition> getGrantConditions() {
        return grantConditions;
    }

    public FacilityProfile setGrantConditions(List<GrantCondition> grantConditions) {
        this.grantConditions = grantConditions;
        return this;
    }
}
