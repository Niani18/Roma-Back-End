package com.javautn.roma.province.dto;

import com.javautn.roma.family.entity.FamilyEntity;
import com.javautn.roma.province.entity.ProvinceEntity;

import java.util.ArrayList;
import java.util.List;

public class ProvinceResponseDto {

    private long id;
    private String name;
//    private List<FamilyEntity> families = new ArrayList<>();

    public ProvinceResponseDto() {}

    public ProvinceResponseDto(long id, String name/*,  List<FamilyEntity> families*/) {
        this.id = id;
        this.name = name;
//        this.families = families;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

//    public List<FamilyEntity> getFamilies() {
//        return families;
//    }

    public static ProvinceResponseDto fromProvince (ProvinceEntity province) {
        return new ProvinceResponseDto(province.getId(), province.getName()/*,  province.getFamilies()*/);
    }
}
