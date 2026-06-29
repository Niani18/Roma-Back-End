package com.javautn.roma.human.service;

import com.javautn.roma.acquisition.entity.State;
import com.javautn.roma.acquisition.repository.AcquisitionRepository;
import com.javautn.roma.auth.repository.UserRepository;
import com.javautn.roma.familyRol.repository.FamilyRolRepository;
import com.javautn.roma.human.entity.CitizenEntity;
import com.javautn.roma.human.entity.SlaveEntity;
import com.javautn.roma.human.repository.CitizenRepository;
import com.javautn.roma.human.repository.SlaveRepository;
import com.javautn.roma.human.specs.HumanSpecifications;
import com.javautn.roma.shared.exception.ConflictException;
import com.javautn.roma.shared.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HumanService {

    private final CitizenRepository citizenRepository;
    private final SlaveRepository slaveRepository;
    private final FamilyRolRepository familyRolRepository;
    private final AcquisitionRepository acquisitionRepository;
    private final UserRepository userRepository;

    public HumanService(
            final CitizenRepository citizenRepository,
            final SlaveRepository slaveRepository,
            final FamilyRolRepository familyRolRepository,
            final AcquisitionRepository acquisitionRepository,
            final UserRepository userRepository
    ) {
        this.citizenRepository = citizenRepository;
        this.slaveRepository = slaveRepository;
        this.familyRolRepository = familyRolRepository;
        this.acquisitionRepository = acquisitionRepository;
        this.userRepository = userRepository;
    }

    public List<CitizenEntity> getAllCitizen() {
        return citizenRepository.findAll();
    }

    public List<SlaveEntity> getAllSlaves() {
        return slaveRepository.findAll();
    }

    public CitizenEntity getCitizen(final long id) {
        return citizenRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Citizen not found with id " + id));
    }

    public SlaveEntity getSlave(final long id) {
        return slaveRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Slave not found with id " + id));
    }

    public CitizenEntity createCitizen(CitizenEntity citizen) {
        return citizenRepository.saveAndFlush(citizen);
    }

    public SlaveEntity createSlave(SlaveEntity slave) {
        return slaveRepository.saveAndFlush(slave);
    }

    public CitizenEntity updateCitizen(final long id, final CitizenEntity citizen) {
        if (!citizenRepository.existsById(id)) {
            throw new NotFoundException("Citizen not found with id " + id);
        }
        citizen.setId(id);
        CitizenEntity updatedCitizen = citizenRepository.saveAndFlush(citizen);
        syncUserState(updatedCitizen);
        return updatedCitizen;
    }

    public SlaveEntity updateSlave(final long id, final SlaveEntity slave) {
        if (!slaveRepository.existsById(id)) {
            throw new NotFoundException("Slave not found with id " + id);
        }
        slave.setId(id);
        return slaveRepository.saveAndFlush(slave);
    }

    public CitizenEntity deleteCitizen(final long id) {
        CitizenEntity citizen = getCitizen(id);
        citizenRepository.deleteById(id);
        citizenRepository.flush();
        return citizen;
    }

    public SlaveEntity deleteSlave(final long id) {
        SlaveEntity slave = getSlave(id);
        slaveRepository.deleteById(id);
        slaveRepository.flush();
        return slave;
    }

    @Transactional
    public CitizenEntity setDeathDate(final long id) {
        Date deathDate = new Date();
        CitizenEntity citizen = getCitizen(id);

        if (citizen.getBirthDate() != null && deathDate.before(citizen.getBirthDate())) {
            throw new ConflictException("The death date cannot be before the birth date");
        }

        citizen.setDeathDate(deathDate);

        familyRolRepository.findByCitizenId(citizen.getId())
                .forEach(familyRol -> familyRol.setDateOfUnjoining(deathDate));

        CitizenEntity updatedCitizen = citizenRepository.save(citizen);
        syncUserState(updatedCitizen);
        return updatedCitizen;
    }

    @Transactional
    public SlaveEntity setDeathDateOfSlaves(final long id) {
        Date deathDate = new Date();
        SlaveEntity slave = getSlave(id);

        if (slave.getBirthDate() != null && deathDate.before(slave.getBirthDate())) {
            throw new ConflictException("The death date cannot be before the birth date");
        }

        slave.setDeathDate(deathDate);

        acquisitionRepository.findByStateAndSlaveId(State.ACTIVE, slave.getId())
                .forEach(acquisition -> acquisition.setState(State.DEATH));

        return slaveRepository.save(slave);
    }

    public CitizenEntity getCitizenByFamily(long id) {
        return citizenRepository.findCitizenWithFamilies(id)
                .orElseThrow(() -> new NotFoundException("Citizen not found with id " + id));
    }

    public CitizenEntity getCitizenWithLegalCases(long id) {
        return citizenRepository.findCitizenWithLegalCases(id)
                .orElseThrow(() -> new NotFoundException("Citizen not found with id " + id));
    }

    public SlaveEntity getSlaveByFamily(long id) {
        return slaveRepository.findSlaveWithFamilies(id)
                .orElseThrow(() -> new NotFoundException("Slave not found with id " + id));
    }

    private void syncUserState(CitizenEntity citizen) {
        userRepository.findByCitizenId(citizen.getId())
                .ifPresent(user -> {
                    user.syncStateWithCitizenLife();
                    userRepository.save(user);
                });
    }

    public List<CitizenEntity> searchCitizen(Long page, Long id, String name, List<String> sortKeys) {
        return citizenRepository.findAll(
                Specification.where(HumanSpecifications.isId(id))
                        .or(HumanSpecifications.hasName(name)),
                HumanSpecifications.page((int)page.longValue(), 5)
        ).toList();
    }
}
