package com.javautn.roma.property.dto;

import java.util.ArrayList;
import java.util.List;

public class PropertyWithAllHoldingsDto {

    private long id;
    private String name;
    private String description;
    private List<PropertyOwnerDto> owner = new ArrayList<>();

    protected PropertyWithAllHoldingsDto() {}

    public PropertyWithAllHoldingsDto(long id, String name, String description, List<PropertyOwnerDto> owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PropertyOwnerDto> getOwner() {
        return owner;
    }

    public void setOwner(List<PropertyOwnerDto> owner) {
        this.owner = owner;
    }
}
