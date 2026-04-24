package com.javautn.roma.province.dto;

public class ProvinceResponceDto {

    private long id;
    private String name;

    public ProvinceResponceDto() {}
    public ProvinceResponceDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
