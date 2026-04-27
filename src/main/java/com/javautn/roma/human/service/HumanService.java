package com.javautn.roma.human.service;

import com.javautn.roma.human.entity.CitizenEntity;
import com.javautn.roma.human.entity.SlaveEntity;
import com.javautn.roma.human.repository.HumanRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public CitizenEntity getCitizen(final long id) {
        return (CitizenEntity) humanRepo.getReferenceById(id);
    }

    public SlaveEntity getSlave(final long id) {
        return (SlaveEntity) humanRepo.getReferenceById(id);
    }

    public CitizenEntity createCitizen(CitizenEntity citizen) {
        final CitizenEntity newCitizen = humanRepo.save(citizen);
        humanRepo.flush();
        return newCitizen;
    }

    public SlaveEntity createSlave(SlaveEntity slave) {
        final SlaveEntity newSlave = humanRepo.save(slave);
        humanRepo.flush();
        return newSlave;
    }

    public CitizenEntity updateCitizen(final long id, final CitizenEntity citizen) {
        if (!humanRepo.existsById(id)) return null;
        citizen.setId(id);
        return humanRepo.saveAndFlush(citizen);
    }

    public SlaveEntity updateSlave(final long id, final SlaveEntity slave) {
        if (!humanRepo.existsById(id)) return null;
        slave.setId(id);
        return humanRepo.saveAndFlush(slave);
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
