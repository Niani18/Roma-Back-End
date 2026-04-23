package com.javautn.roma.human.controller;

import com.javautn.roma.human.dto.CitizenCreateDTO;
import com.javautn.roma.human.dto.SlaveCreateDTO;
import com.javautn.roma.human.entity.Citizen;
import com.javautn.roma.human.entity.Slave;
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
    public ArrayList<Citizen> getAllCitizen() {
        return new ArrayList<Citizen>();
    }

    @GetMapping("slave/")
    public ArrayList<Slave> getAllSlave() {
        return new ArrayList<Slave>();
    }

    @GetMapping("citizen/:id/")
    public Citizen getCitizen(@PathVariable("id") final long id) {
        return null;
    }

    @GetMapping("slave/:id/")
    public Citizen getSlave(@PathVariable("id") final long id) {
        return null;
    }

    @PostMapping("citizen/")
    public Citizen createCitizen(@RequestBody final CitizenCreateDTO ccdto) {
        return null;
    }

    @PostMapping("slave/")
    public Citizen createSlave(@RequestBody final SlaveCreateDTO scdto) {
        return null;
    }

    @PutMapping("citizen/:id/")
    public Citizen updateCitizen(
            @PathVariable("id") final long id,
            @RequestBody final CitizenCreateDTO postdto) {
        return null;
    }

    @PutMapping("slave/:id/")
    public Citizen updateSlave(
            @PathVariable("id") final long id,
            @RequestBody final SlaveCreateDTO postdto) {
        return null;
    }

    @DeleteMapping("citizen/:id/")
    public Citizen deleteCitizen() {
        return null;
    }

    @DeleteMapping("slave/:id/")
    public Citizen deleteSlave() {
        return null;
    }

}
