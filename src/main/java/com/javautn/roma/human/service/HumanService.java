package com.javautn.roma.human.service;

import com.javautn.roma.familyRol.repository.FamilyRolRepository;
import com.javautn.roma.human.entity.CitizenEntity;
import com.javautn.roma.human.entity.SlaveEntity;
import com.javautn.roma.human.repository.CitizenRepository;
import com.javautn.roma.human.repository.SlaveRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HumanService {

    private final CitizenRepository citizenRepository;
    private final SlaveRepository slaveRepository;
    private final FamilyRolRepository familyRolRepository;

    public HumanService(final CitizenRepository citizenRepository, final SlaveRepository slaveRepository,  final FamilyRolRepository familyRolRepository) {
        this.citizenRepository = citizenRepository;
        this.slaveRepository = slaveRepository;
        this.familyRolRepository = familyRolRepository;
    }

    public List<CitizenEntity> getAllCitizen() {
        return citizenRepository.findAll();
    }

    public List<SlaveEntity> getAllSlaves() {
        return slaveRepository.findAll();
    }

    public Optional<CitizenEntity> getCitizen(final long id) {
        return citizenRepository.findById(id);
    }

    public Optional<SlaveEntity> getSlave(final long id) {
        return slaveRepository.findById(id);
    }

    public Optional<CitizenEntity> createCitizen(CitizenEntity citizen) {
        final CitizenEntity newCitizen = citizenRepository.saveAndFlush(citizen);
        return Optional.of(newCitizen);
    }

    public Optional<SlaveEntity> createSlave(SlaveEntity slave) {
        final SlaveEntity newSlave = slaveRepository.saveAndFlush(slave);
        return Optional.of(newSlave);
    }

    public Optional<CitizenEntity> updateCitizen(final long id, final CitizenEntity citizen) {
        if (!citizenRepository.existsById(id)) return Optional.empty();
        citizen.setId(id);
        return Optional.of(citizenRepository.saveAndFlush(citizen));
    }

    public Optional<SlaveEntity> updateSlave(final long id, final SlaveEntity slave) {
        if (!slaveRepository.existsById(id)) return Optional.empty();
        slave.setId(id);
        return Optional.of(slaveRepository.saveAndFlush(slave));
    }

    public void deleteCitizen(final long id) {
        if (!citizenRepository.existsById(id)) return;
        citizenRepository.deleteById(id);
        citizenRepository.flush();
    }

    public void deleteSlave(final long id) {
        if (!slaveRepository.existsById(id)) return;
        slaveRepository.deleteById(id);
        slaveRepository.flush();
    }

    @Transactional
    public Optional<CitizenEntity> setDeathDate(final long id) {
        Optional<CitizenEntity> nuevoFiambre = citizenRepository.findById(id);

        if (nuevoFiambre.isEmpty()) {
            return Optional.empty();
        }

        Date deathDate = new Date();
        CitizenEntity citizen = nuevoFiambre.get();

        citizen.setDeathDate(deathDate);

        familyRolRepository.findByCitizenId(citizen.getId())
                .forEach(familyRol -> familyRol.setDateOfUnjoining(deathDate));

        return Optional.of(citizenRepository.save(citizen));
    }

    public Optional<CitizenEntity> getCitizenByFamily(long id) {
        return citizenRepository.findCitizenWithFamilies(id);
    }
}
