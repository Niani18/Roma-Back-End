package com.javautn.roma.tax.service;

import com.javautn.roma.family.entity.FamilyEntity;
import com.javautn.roma.family.repository.FamilyRepository;
import com.javautn.roma.property.entity.PropertyEntity;
import com.javautn.roma.property.repository.PropertyRepository;
import com.javautn.roma.tax.dto.TaxAssignationFamilyCreateDTO;
import com.javautn.roma.tax.dto.TaxAssignationPropertyCreateDTO;
import com.javautn.roma.tax.dto.TaxAssignationUpdateDTO;
import com.javautn.roma.tax.entity.StateAsignation;
import com.javautn.roma.tax.entity.Target;
import com.javautn.roma.tax.entity.TaxAssignationEntity;
import com.javautn.roma.tax.entity.TaxEntity;
import com.javautn.roma.tax.repository.TaxAssignationRepository;
import com.javautn.roma.tax.repository.TaxRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaxAssignationService {

    private final TaxAssignationRepository assignationRepository;
    private final TaxRepository taxRepository;
    private final FamilyRepository familyRepository;
    private final PropertyRepository propertyRepository;

    public TaxAssignationService(
            TaxAssignationRepository assignationRepository,
            TaxRepository taxRepository,
            FamilyRepository familyRepository,
            PropertyRepository propertyRepository) {
        this.assignationRepository = assignationRepository;
        this.taxRepository = taxRepository;
        this.familyRepository = familyRepository;
        this.propertyRepository = propertyRepository;
    }

    public List<TaxAssignationEntity> getAll() {
        return assignationRepository.findAll();
    }

    public Optional<TaxAssignationEntity> getOne(long id) {
        return assignationRepository.findById(id);
    }

    public Optional<TaxAssignationEntity> createAssignationForFamily(TaxAssignationFamilyCreateDTO dto) {
        Optional<TaxEntity> tax = taxRepository.findById(dto.getTaxId());
        if (tax.isEmpty() || tax.get().getTarget() != Target.FAMILY) {
            return Optional.empty();
        }

        Optional<FamilyEntity> fam = familyRepository.findById(dto.getFamilyId());
        if (fam.isEmpty()) return Optional.empty();

        TaxAssignationEntity entity = new TaxAssignationEntity(
                dto.getAmount(),
                dto.getExpiryDate(),
                null,
                StateAsignation.PENDING,
                0,
                null,
                tax.get(),
                fam.get(),
                null
        );

        return Optional.of(assignationRepository.save(entity));
    }

    public Optional<TaxAssignationEntity> createAssignationForProperty(TaxAssignationPropertyCreateDTO dto) {
        Optional<TaxEntity> tax = taxRepository.findById(dto.getTaxId());
        if (tax.isEmpty() || tax.get().getTarget() != Target.PROPERTY) {
            return Optional.empty();
        }

        Optional<PropertyEntity> prop = propertyRepository.findById(dto.getPropertyId());
        if (prop.isEmpty()) return Optional.empty();

        TaxAssignationEntity entity = new TaxAssignationEntity(
                dto.getAmount(),
                dto.getExpiryDate(),
                null,
                StateAsignation.PENDING,
                0,
                null,
                tax.get(),
                null,
                prop.get()
        );

        return Optional.of(assignationRepository.save(entity));
    }




    public Optional<TaxAssignationEntity> updateTaxAssignation(long id, TaxAssignationUpdateDTO dto) {

        Optional<TaxAssignationEntity> assignation = assignationRepository.findById(id);
        if (assignation.isEmpty()) return Optional.empty();

        TaxAssignationEntity entity = assignation.get();

        if(dto.getPaymentDate() != null)  {
            entity.setPaymentDate(dto.getPaymentDate());
            entity.setState(StateAsignation.PAID);
        }

        if(dto.getInterest() != null) {
            entity.setInterest(dto.getInterest());
        }

        if(dto.getSanction() != null && assignation.get().getState() == StateAsignation.PENDING) {
            entity.setSanction(dto.getSanction());
            if (entity.getState() != StateAsignation.PAID && !dto.getSanction().isBlank()) {
                entity.setState(StateAsignation.SANCTIONED);
            }
        }

        return Optional.of(assignationRepository.save(entity));
    }
}
