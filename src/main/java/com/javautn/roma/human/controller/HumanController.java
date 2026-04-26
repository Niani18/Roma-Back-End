package com.javautn.roma.human.controller;

import com.javautn.roma.human.dto.CitizenCreateDTO;
import com.javautn.roma.human.dto.SlaveCreateDTO;
import com.javautn.roma.human.entity.CitizenEntity;
import com.javautn.roma.human.entity.SlaveEntity;
import com.javautn.roma.human.service.HumanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<CitizenEntity>> getAllCitizen() {
        return ResponseEntity.ok(humanService.getAllCitizen());
    }

    @GetMapping("slave/getAll")
    public ResponseEntity<List<SlaveEntity>> getAllSlave() {
        return ResponseEntity.ok(humanService.getAllSlaves());
    }

    @GetMapping("citizen/{id}/")
    public ResponseEntity<CitizenEntity> getCitizen(@PathVariable("id") final long id) {
        return ResponseEntity.ok(humanService.getCitizen(id));
    }

    @GetMapping("slave/{id}/")
    public ResponseEntity<SlaveEntity> getSlave(@PathVariable("id") final long id) {
        return ResponseEntity.ok(humanService.getSlave(id));
    }

    @PostMapping("citizen/")
    public ResponseEntity<CitizenEntity> createCitizen(
            @Valid @RequestBody final CitizenCreateDTO ccdto) {
        final CitizenEntity newCitizen = ccdto.newCitizen();
        return ResponseEntity.ok(humanService.createCitizen(newCitizen));
    }

    @PostMapping("slave/")
    public ResponseEntity<SlaveEntity> createSlave(
            @Valid @RequestBody final SlaveCreateDTO scdto) {
        final SlaveEntity newSlave = scdto.newSlave();
        return ResponseEntity.ok(humanService.createSlave(newSlave));
    }

    @PutMapping("citizen/{id}/")
    public ResponseEntity<CitizenEntity> updateCitizen(
            @PathVariable("id") final long id,
            @Valid @RequestBody final CitizenCreateDTO postdto) {
        final CitizenEntity newCitizen = postdto.newCitizen();
        return ResponseEntity.ok(humanService.updateCitizen(id, newCitizen));
    }

    @PutMapping("slave/{id}/")
    public ResponseEntity<SlaveEntity> updateSlave(
            @PathVariable("id") final long id,
            @Valid @RequestBody final SlaveCreateDTO postdto) {
        final SlaveEntity newSlave = postdto.newSlave();
        return ResponseEntity.ok(humanService.updateSlave(id, newSlave));
    }

    @DeleteMapping("citizen/{id}/")
    public ResponseEntity<CitizenEntity> deleteCitizen(@PathVariable("id") final long id) {
        final CitizenEntity cit = humanService.getCitizen(id);
        humanService.deleteCitizen(id);
        return ResponseEntity.ok(cit);
    }

    @DeleteMapping("slave/{id}/")
    public ResponseEntity<SlaveEntity> deleteSlave(@PathVariable final long id) {
        final SlaveEntity slave = humanService.getSlave(id);
        humanService.deleteSlave(id);
        return ResponseEntity.ok(slave);
    }
}
