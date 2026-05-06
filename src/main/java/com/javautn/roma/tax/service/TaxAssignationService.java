package com.javautn.roma.tax.service;

import com.javautn.roma.family.entity.FamilyEntity;
import com.javautn.roma.family.repository.FamilyRepository;
import com.javautn.roma.property.entity.PropertyEntity;
import com.javautn.roma.property.repository.PropertyRepository;
import com.javautn.roma.tax.dto.TaxAssignationCreateDTO;
import com.javautn.roma.tax.dto.TaxAssignationUpdateDTO;
import com.javautn.roma.tax.entity.TaxAssignationEntity;
import com.javautn.roma.tax.repository.TaxAssignationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaxAssignationService {

    public enum AssignTo {
        FAMILY,
        PROPERTY
    }

    private final TaxAssignationRepository assignationRepository;
    private final FamilyRepository familyRepository;
    private final PropertyRepository propertyRepository;

    public TaxAssignationService(
            TaxAssignationRepository assignationRepository,
            FamilyRepository familyRepository,
            PropertyRepository propertyRepository) {
        this.assignationRepository = assignationRepository;
        this.familyRepository = familyRepository;
        this.propertyRepository = propertyRepository;
    }

    public List<TaxAssignationEntity> getAll() {
        return assignationRepository.findAll();
    }

    public Optional<TaxAssignationEntity> getOne(long id) {
        return assignationRepository.findById(id);
    }

    public Optional<TaxAssignationEntity> createAssignation(TaxAssignationCreateDTO dto, AssignTo to) {
        TaxAssignationEntity entity = dto.newAssignation();

        if(to == AssignTo.FAMILY) {

            if(dto.getFamilyId() == 0)
                return Optional.empty();

            FamilyEntity fam = familyRepository.getReferenceById(dto.getFamilyId());
            entity.setFamily(fam);

        } else if (to == AssignTo.PROPERTY) {

            if(dto.getPropertyId() == 0)
                return Optional.empty();

            PropertyEntity prop = propertyRepository.getReferenceById(dto.getPropertyId());
            entity.setProperty(prop);

        }

        return Optional.of(assignationRepository.save(entity));
    }




    public Optional<TaxAssignationEntity> updateTaxAssignation(long id, TaxAssignationUpdateDTO dto) {

        Optional<TaxAssignationEntity> assignation = assignationRepository.findById(id);
        if (assignation.isEmpty()) return Optional.empty();


        // Lo que estas a punto de ver es mierda seca

        if(dto.getAmount().isPresent())
            assignation.get().setAmount(dto.getAmount().get());

        if(dto.getExpiryDate().isPresent())
            assignation.get().setExpiryDate(dto.getExpiryDate().get());

        if(dto.getPaymentDate().isPresent())  {
            assignation.get().setAmount(dto.getAmount().get());
            assignation.get().setState("PAID");
        }

        if(dto.getInterest().isPresent())
            assignation.get().setInterest(dto.getInterest().get());

        if(dto.getSanction().isPresent())
            assignation.get().setSanction(dto.getSanction().get());

        return Optional.of(assignationRepository.save(assignation.get()));
    }
}
