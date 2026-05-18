package com.javautn.roma.human.controller;

import com.javautn.roma.human.dto.*;
import com.javautn.roma.human.entity.CitizenEntity;
import com.javautn.roma.human.entity.SlaveEntity;
import com.javautn.roma.human.service.HumanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//samu, cuando veas esto RequestEntity es para fromatear directamente el mensaje para el front a un http asi que porfa cambialo uwu
//Gotcha bro, entiendo igual que es ResponseEntity
//Si UnU

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
    public ResponseEntity<CitizenResponseDTO> getCitizen(@PathVariable final long id) {
        return ResponseEntity.ok(CitizenResponseDTO.fromCitizen(humanService.getCitizen(id)));
    }

    @GetMapping("slave/{id}")
    public ResponseEntity<SlaveResponseDTO> getSlave(@PathVariable final long id) {
        return ResponseEntity.ok(SlaveResponseDTO.fromSlave(humanService.getSlave(id)));
    }

    @GetMapping("/getOneWithFamily/{id}")
    public ResponseEntity<CitizenWithFamilyRoleDto> getOneWithFamily(@PathVariable long id) {
        return ResponseEntity.ok(CitizenWithFamilyRoleDto.fromCitizenWith(humanService.getCitizenByFamily(id)));

    }

    @GetMapping("/citizen/getOneWithLegalCases/{id}")
    public ResponseEntity<CitizenWithLegalCasesDto> getOneWithLegalCases(@PathVariable long id) {
        return ResponseEntity.ok(CitizenWithLegalCasesDto.fromCitizen(humanService.getCitizenWithLegalCases(id)));
    }

    @GetMapping("/slave/getOneWithFamily/{id}")
    public ResponseEntity<SlaveWithFamiliesDto> getOneSlaveWithFamily(@PathVariable long id) {
        return ResponseEntity.ok(SlaveWithFamiliesDto.fromSlaveWith(humanService.getSlaveByFamily(id)));
    }

    @PostMapping("citizen")
    public ResponseEntity<CitizenResponseDTO> createCitizen(
            @Valid @RequestBody final CitizenCreateDTO ccdto) {
        final CitizenEntity newCitizen = ccdto.newCitizen();
        return ResponseEntity.ok(CitizenResponseDTO.fromCitizen(humanService.createCitizen(newCitizen)));
    }

    @PostMapping("slave")
    public ResponseEntity<SlaveResponseDTO> createSlave(
            @Valid @RequestBody final SlaveCreateDTO scdto) {
        final SlaveEntity newSlave = scdto.newSlave();
        return ResponseEntity.ok(SlaveResponseDTO.fromSlave(humanService.createSlave(newSlave)));
    }

    @PutMapping("citizen/{id}")
    public ResponseEntity<CitizenResponseDTO> updateCitizen(
            @PathVariable final long id,
            @Valid @RequestBody final CitizenCreateDTO postdto) {
        final CitizenEntity newCitizen = postdto.newCitizen();
        return ResponseEntity.ok(CitizenResponseDTO.fromCitizen(humanService.updateCitizen(id, newCitizen)));
    }

    @PutMapping("slave/{id}")
    public ResponseEntity<SlaveResponseDTO> updateSlave(
            @PathVariable final long id,
            @Valid @RequestBody final SlaveCreateDTO postdto) {
        final SlaveEntity newSlave = postdto.newSlave();
        return ResponseEntity.ok(SlaveResponseDTO.fromSlave(humanService.updateSlave(id, newSlave)));
    }

    @PatchMapping("citizen/death/{id}")
    public ResponseEntity<CitizenResponseDTO> setDeathDate(@PathVariable long id) {
        return ResponseEntity.ok(CitizenResponseDTO.fromCitizen(humanService.setDeathDate(id)));
    }

    @PatchMapping("slave/death/{id}")
    public ResponseEntity<SlaveResponseDTO> setDeathDateofSlave(@PathVariable long id) {
        return ResponseEntity.ok(SlaveResponseDTO.fromSlave(humanService.setDeathDateOfSlaves(id)));
    }

    @DeleteMapping("citizen/{id}")
    public ResponseEntity<CitizenResponseDTO> deleteCitizen(@PathVariable final long id) {
        return ResponseEntity.ok(CitizenResponseDTO.fromCitizen(humanService.deleteCitizen(id)));
    }

    @DeleteMapping("slave/{id}")
    public ResponseEntity<SlaveResponseDTO> deleteSlave(@PathVariable final long id) {
        return ResponseEntity.ok(SlaveResponseDTO.fromSlave(humanService.deleteSlave(id)));
    }
}
