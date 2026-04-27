package com.javautn.roma.human.controller;

import com.javautn.roma.human.dto.*;
import com.javautn.roma.human.entity.CitizenEntity;
import com.javautn.roma.human.entity.SlaveEntity;
import com.javautn.roma.human.service.HumanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//samu, cuando veas esto RequestEntity es para fromatear directamente el mensaje para el front a un http asi que porfa cambialo uwu
// Gotcha bro, entiendo igual que es ResponseEntity

@RestController
@RequestMapping("/human/")
public class HumanController {

    protected final HumanService humanService;

    public HumanController(final HumanService humanService) {
        this.humanService = humanService;
    }

    @GetMapping("citizen/getAll")
    public ResponseEntity<List<CitizenResponseDTO>> getAllCitizen() {
        return ResponseEntity.ok(humanService.getAllCitizen().stream().map(CitizenResponseDTO::fromCitizen).toList());
    }

    @GetMapping("slave/getAll")
    public ResponseEntity<List<SlaveResponseDTO>> getAllSlave() {
        return ResponseEntity.ok(humanService.getAllSlaves().stream().map(SlaveResponseDTO::fromSlave).toList());
    }

    @GetMapping("citizen/{id}")
    public ResponseEntity<CitizenResponseDTO> getCitizen(@PathVariable("id") final long id) {
        return ResponseEntity.of(humanService.getCitizen(id).map(CitizenResponseDTO::fromCitizen));
    }

    @GetMapping("slave/{id}")
    public ResponseEntity<SlaveResponseDTO> getSlave(@PathVariable("id") final long id) {
        return ResponseEntity.of(humanService.getSlave(id).map(SlaveResponseDTO::fromSlave));
    }

    @PostMapping("citizen")
    public ResponseEntity<CitizenResponseDTO> createCitizen(
            @Valid @RequestBody final CitizenCreateDTO ccdto) {
        final CitizenEntity newCitizen = ccdto.newCitizen();
        return ResponseEntity.of(humanService.createCitizen(newCitizen).map(CitizenResponseDTO::fromCitizen));
    }

    @PostMapping("slave")
    public ResponseEntity<SlaveResponseDTO> createSlave(
            @Valid @RequestBody final SlaveCreateDTO scdto) {
        final SlaveEntity newSlave = scdto.newSlave();
        return ResponseEntity.of(humanService.createSlave(newSlave).map(SlaveResponseDTO::fromSlave));
    }

    @PutMapping("citizen/{id}")
    public ResponseEntity<CitizenResponseDTO> updateCitizen(
            @PathVariable("id") final long id,
            @Valid @RequestBody final CitizenCreateDTO postdto) {
        final CitizenEntity newCitizen = postdto.newCitizen();
        return ResponseEntity.of(humanService.updateCitizen(id, newCitizen).map(CitizenResponseDTO::fromCitizen));
    }

    @PutMapping("slave/{id}")
    public ResponseEntity<SlaveResponseDTO> updateSlave(
            @PathVariable("id") final long id,
            @Valid @RequestBody final SlaveCreateDTO postdto) {
        final SlaveEntity newSlave = postdto.newSlave();
        return ResponseEntity.of(humanService.updateSlave(id, newSlave).map(SlaveResponseDTO::fromSlave));
    }

    @DeleteMapping("citizen/{id}")
    public ResponseEntity<CitizenResponseDTO> deleteCitizen(@PathVariable("id") final long id) {
        final Optional<CitizenEntity> citizen = humanService.getCitizen(id);
        humanService.deleteCitizen(id);
        return ResponseEntity.of(citizen.map(CitizenResponseDTO::fromCitizen));
    }

    @DeleteMapping("slave/{id}")
    public ResponseEntity<SlaveResponseDTO> deleteSlave(@PathVariable final long id) {
        final Optional<SlaveEntity> slave = humanService.getSlave(id);
        humanService.deleteSlave(id);
        return ResponseEntity.of(slave.map(SlaveResponseDTO::fromSlave));
    }
}
