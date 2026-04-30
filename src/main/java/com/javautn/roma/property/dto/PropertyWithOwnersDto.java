package com.javautn.roma.property.dto;

public class PropertyWithOwnersDto {

    private long id;
    private String name;
    private String description;
    private PropertyOwnerDto owner;

    protected PropertyWithOwnersDto() {}

    public PropertyWithOwnersDto(long id, String name, String description, PropertyOwnerDto owner) {
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

    public PropertyOwnerDto getOwner() {
        return owner;
    }

    public void setOwner(PropertyOwnerDto owner) {
        this.owner = owner;
    }
}
