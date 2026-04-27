package com.javautn.roma.human.service;

import com.javautn.roma.human.dto.CitizenResponseDTO;
import com.javautn.roma.human.entity.CitizenEntity;
import com.javautn.roma.human.entity.SlaveEntity;
import com.javautn.roma.human.repository.HumanRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HumanService {

    final HumanRepository humanRepo;

    public HumanService(final HumanRepository humanRepo) {
        this.humanRepo = humanRepo;
    }

    public List<CitizenEntity> getAllCitizen() {
        return new ArrayList<CitizenEntity>();
    }

    public List<SlaveEntity> getAllSlaves() {
        return new ArrayList<SlaveEntity>();
    }

    public Optional<CitizenEntity> getCitizen(final long id) {
        return humanRepo.findById(id).map(h -> (CitizenEntity) h);
    }

    public Optional<SlaveEntity> getSlave(final long id) {
        return humanRepo.findById(id).map(h -> (SlaveEntity) h);
    }

    public Optional<CitizenEntity> createCitizen(CitizenEntity citizen) {
        final CitizenEntity newCitizen = humanRepo.saveAndFlush(citizen);
        return Optional.of(newCitizen);
    }

    public Optional<SlaveEntity> createSlave(SlaveEntity slave) {
        final SlaveEntity newSlave = humanRepo.saveAndFlush(slave);
        return Optional.of(newSlave);
    }

    public Optional<CitizenEntity> updateCitizen(final long id, final CitizenEntity citizen) {
        if (!humanRepo.existsById(id)) return Optional.empty();
        citizen.setId(id);
        return Optional.of(humanRepo.saveAndFlush(citizen));
    }

    public Optional<SlaveEntity> updateSlave(final long id, final SlaveEntity slave) {
        if (!humanRepo.existsById(id)) return Optional.empty();
        slave.setId(id);
        return Optional.of(humanRepo.saveAndFlush(slave));
    }

    public void deleteCitizen(final long id) {
        humanRepo.deleteById(id);
        humanRepo.flush();
    }

    public void deleteSlave(final long id) {
        humanRepo.deleteById(id);
        humanRepo.flush();
    }
}
