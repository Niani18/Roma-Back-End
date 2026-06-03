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
import com.javautn.roma.shared.exception.BadRequestException;
import com.javautn.roma.shared.exception.NotFoundException;
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

    public TaxAssignationEntity getOne(long id) {
        return assignationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tax assignation not found with id " + id));
    }

    public TaxAssignationEntity createAssignationForFamily(TaxAssignationFamilyCreateDTO dto) {
        TaxEntity tax = taxRepository.findById(dto.getTaxId())
                .orElseThrow(() -> new NotFoundException("Tax not found with id " + dto.getTaxId()));
        if (tax.getTarget() != Target.FAMILY) {
            throw new BadRequestException("Tax with id " + dto.getTaxId() + " is not assigned to FAMILY target");
        }

        FamilyEntity fam = familyRepository.findById(dto.getFamilyId())
                .orElseThrow(() -> new NotFoundException("Family not found with id " + dto.getFamilyId()));

        TaxAssignationEntity entity = new TaxAssignationEntity(
                dto.getAmount(),
                dto.getExpiryDate(),
                null,
                StateAsignation.PENDING,
                0,
                null,
                tax,
                fam,
                null
        );

        return assignationRepository.save(entity);
    }

    public TaxAssignationEntity createAssignationForProperty(TaxAssignationPropertyCreateDTO dto) {
        TaxEntity tax = taxRepository.findById(dto.getTaxId())
                .orElseThrow(() -> new NotFoundException("Tax not found with id " + dto.getTaxId()));
        if (tax.getTarget() != Target.PROPERTY) {
            throw new BadRequestException("Tax with id " + dto.getTaxId() + " is not assigned to PROPERTY target");
        }

        PropertyEntity prop = propertyRepository.findById(dto.getPropertyId())
                .orElseThrow(() -> new NotFoundException("Property not found with id " + dto.getPropertyId()));

        TaxAssignationEntity entity = new TaxAssignationEntity(
                dto.getAmount(),
                dto.getExpiryDate(),
                null,
                StateAsignation.PENDING,
                0,
                null,
                tax,
                null,
                prop
        );

        return assignationRepository.save(entity);
    }




    public TaxAssignationEntity updateTaxAssignation(long id, TaxAssignationUpdateDTO dto) {

        TaxAssignationEntity entity = assignationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tax assignation not found with id " + id));

        if (dto.getPaymentDate() == null && dto.getInterest() == null && dto.getSanction() == null) {
            throw new BadRequestException("At least one field must be provided to update tax assignation");
        }

        if(dto.getPaymentDate() != null)  {
            if (entity.getState() == StateAsignation.PAID) {
                throw new BadRequestException("The tax is already paid");
            }
            entity.setPaymentDate(dto.getPaymentDate());
            entity.setState(StateAsignation.PAID);
        }

        if(dto.getInterest() != null) {
            if (entity.getState() != StateAsignation.PENDING) {
                throw new BadRequestException("Sanction can only be updated while tax assignation is PENDING");
            }
            entity.setInterest(dto.getInterest());
        }

        if(dto.getSanction() != null) {
            if (entity.getState() != StateAsignation.PENDING) {
                throw new BadRequestException("Sanction can only be updated while tax assignation is PENDING");
            }
            entity.setSanction(dto.getSanction());
            if (entity.getState() != StateAsignation.PAID && !dto.getSanction().isBlank()) {
                entity.setState(StateAsignation.SANCTIONED);
            }
        }

        return assignationRepository.save(entity);
    }
}
