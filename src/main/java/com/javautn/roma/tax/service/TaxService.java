package com.javautn.roma.tax.service;

import com.javautn.roma.tax.dto.TaxCreateDTO;
import com.javautn.roma.tax.dto.TaxResponseDTO;
import com.javautn.roma.tax.entity.TaxEntity;
import com.javautn.roma.tax.repository.TaxRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaxService {

    private final TaxRepository taxRepository;

    public TaxService(final TaxRepository taxRepository) {
        this.taxRepository = taxRepository;
    }

    public List<TaxEntity> getAllTaxes() {
        return new ArrayList<TaxEntity>();
    }

    public Optional<TaxResponseDTO> getTaxById(final long id) {
        final Optional<TaxEntity> tax = taxRepository.findById(id);
        return tax.isPresent() ? Optional.of(TaxResponseDTO.fromTax(tax.get())) : Optional.empty();
    }

    public Optional<TaxResponseDTO> createTax(final TaxCreateDTO dto) {
        final TaxEntity tax = dto.newTax();
        return Optional.of(TaxResponseDTO.fromTax(taxRepository.saveAndFlush(tax)));
    }

    public Optional<TaxResponseDTO> updateTax(final long id, final TaxCreateDTO dto) {
        TaxEntity tax = taxRepository.getReferenceById(id);
        if (tax.getId() != id) return Optional.empty();
        tax = dto.newTax();
        tax.setId(id);
        return Optional.of(TaxResponseDTO.fromTax(taxRepository.saveAndFlush(tax)));
    }

    public Optional<TaxResponseDTO> deleteTax(final long id) {
        final Optional<TaxEntity> tax = taxRepository.findById(id);
        if (!tax.isPresent()) return Optional.empty();
        taxRepository.deleteById(id);
        return Optional.of(TaxResponseDTO.fromTax(tax.get()));
    }
}
