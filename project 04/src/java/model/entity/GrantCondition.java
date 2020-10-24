package model.entity;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "GrantCondition")
@Table()
public class GrantCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long Id;

    @Column(columnDefinition = "varchar2(30)", unique = true, nullable = false)
    private String name;

    @Column(columnDefinition = "Number", nullable = false)
    private long minDuration;

    @Column(columnDefinition = "Number", nullable = false)
    private long maxDuration;

    @Column(columnDefinition = "Number", nullable = false)
    private BigDecimal minAmount;

    @Column(columnDefinition = "Number", nullable = false)
    private BigDecimal maxAmount;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "loanTypeId")
    FacilityProfile facilityProfile;


    public GrantCondition() {
    }

    public GrantCondition(Long Id, String name, long minDuration, long maxDuration, BigDecimal minAmount, BigDecimal maxAmount) {
        this.Id = getId();
        this.name = name;
        this.minDuration = minDuration;
        this.maxDuration = maxDuration;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    public FacilityProfile getFacilityProfile() {
        return facilityProfile;
    }

    public GrantCondition setFacilityProfile(FacilityProfile facilityProfile) {
        this.facilityProfile = facilityProfile;
        return this;
    }

    public long getId() {
        return Id;
    }

    public GrantCondition setId(long id) {
        Id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public GrantCondition setName(String name) {
        this.name = name;
        return this;
    }

    public long getMinDuration() {
        return minDuration;
    }

    public GrantCondition setMinDuration(long minDuration) {
        this.minDuration = minDuration;
        return this;
    }

    public long getMaxDuration() {
        return maxDuration;
    }

    public GrantCondition setMaxDuration(long maxDuration) {
        this.maxDuration = maxDuration;
        return this;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public GrantCondition setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
        return this;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public GrantCondition setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
        return this;
    }

    @Override
    public String toString() {
        return "GrantCondition{" +
                "GrantConditionId=" + Id +
                ", name='" + name + '\'' +
                ", minDuration=" + minDuration +
                ", maxDuration=" + maxDuration +
                ", minAmount=" + minAmount +
                ", maxAmount=" + maxAmount +
                '}';
    }
}
