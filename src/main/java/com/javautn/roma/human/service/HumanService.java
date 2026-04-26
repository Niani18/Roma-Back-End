package com.javautn.roma.human.service;

import com.javautn.roma.human.entity.CitizenEntity;
import com.javautn.roma.human.entity.SlaveEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HumanService {

    public List<CitizenEntity> getAllCitizen() {
        return new ArrayList<CitizenEntity>();
    }

    public List<SlaveEntity> getAllSlaves() {
        return new ArrayList<SlaveEntity>();
    }

    public CitizenEntity getCitizen(final long id) {
        return null;
    }

    public SlaveEntity getSlave(final long id) {
        return null;
    }

    public CitizenEntity createCitizen(CitizenEntity citizen) {
        return citizen;
    }

    public SlaveEntity createSlave(SlaveEntity slave) {
        return slave;
    }

    public CitizenEntity updateCitizen(final long id, final CitizenEntity citizen) {
        return null;
    }

    public SlaveEntity updateSlave(final long id, final SlaveEntity slave) {
        return null;
    }

    public void deleteCitizen(final long id) {

    }

    public void deleteSlave(final long id) {

    }
}
