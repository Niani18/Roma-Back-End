package com.javautn.roma.property.dto;

public class PropertyResponseDto {

    long id;
    String name;
    String description;

    public PropertyResponseDto(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    protected PropertyResponseDto() {}

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

}
