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

    public Optional<TaxEntity> getTaxById(final long id) {
        return taxRepository.findById(id);
    }

    public Optional<TaxEntity> createTax(final TaxCreateDTO dto) {
        final TaxEntity tax = taxRepository.saveAndFlush(dto.newTax());
        return Optional.of(tax);
    }

    public Optional<TaxEntity> updateTax(final long id, final TaxCreateDTO dto) {
        if (!taxRepository.existsById(id)) return Optional.empty();
        TaxEntity tax = dto.newTax();
        tax.setId(id);
        return Optional.of(taxRepository.saveAndFlush(tax));
    }

    public void deleteTax(final long id) {
        taxRepository.deleteById(id);
    }
}
