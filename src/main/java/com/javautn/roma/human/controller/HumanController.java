package com.javautn.roma.human.controller;

import com.javautn.roma.human.dto.CitizenCreateDTO;
import com.javautn.roma.human.dto.SlaveCreateDTO;
import com.javautn.roma.human.entity.CitizenEntity;
import com.javautn.roma.human.entity.SlaveEntity;
import com.javautn.roma.human.service.HumanService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/human/")
public class HumanController {

    protected final HumanService humanService;

    public HumanController(final HumanService humanService) {
        this.humanService = humanService;
    }

    @GetMapping("citizen/")
    public ArrayList<CitizenEntity> getAllCitizen() {
        return new ArrayList<CitizenEntity>();
    }

    @GetMapping("slave/")
    public ArrayList<SlaveEntity> getAllSlave() {
        return new ArrayList<SlaveEntity>();
    }

    @GetMapping("citizen/:id/")
    public CitizenEntity getCitizen(@PathVariable("id") final long id) {
        return null;
    }

    @GetMapping("slave/:id/")
    public CitizenEntity getSlave(@PathVariable("id") final long id) {
        return null;
    }

    @PostMapping("citizen/")
    public CitizenEntity createCitizen(@RequestBody final CitizenCreateDTO ccdto) {
        return null;
    }

    @PostMapping("slave/")
    public CitizenEntity createSlave(@RequestBody final SlaveCreateDTO scdto) {
        return null;
    }

    @PutMapping("citizen/:id/")
    public CitizenEntity updateCitizen(
            @PathVariable("id") final long id,
            @RequestBody final CitizenCreateDTO postdto) {
        return null;
    }

    @PutMapping("slave/:id/")
    public CitizenEntity updateSlave(
            @PathVariable("id") final long id,
            @RequestBody final SlaveCreateDTO postdto) {
        return null;
    }

    @DeleteMapping("citizen/:id/")
    public CitizenEntity deleteCitizen() {
        return null;
    }

    @DeleteMapping("slave/:id/")
    public CitizenEntity deleteSlave() {
        return null;
    }

}
