package com.javautn.roma.property.dto;

public class PropertyOwnerDto {

    private long holdingId;
    private long familyId;
    private String familyName;

    protected PropertyOwnerDto() {}

    public PropertyOwnerDto(long holdingId, long familyId, String familyName) {
        this.holdingId = holdingId;
        this.familyId = familyId;
        this.familyName = familyName;
    }

    public long getHoldingId() {
        return holdingId;
    }

    public void setHoldingId(long holdingId) {
        this.holdingId = holdingId;
    }

    public long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(long familyId) {
        this.familyId = familyId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
