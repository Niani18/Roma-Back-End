package com.javautn.roma.tax.service;

import com.javautn.roma.tax.dto.TaxCreateDTO;
import com.javautn.roma.tax.entity.TaxEntity;
import com.javautn.roma.tax.repository.TaxRepository;
import com.javautn.roma.shared.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxService {

    private final TaxRepository taxRepository;

    public TaxService(final TaxRepository taxRepository) {
        this.taxRepository = taxRepository;
    }

    public List<TaxEntity> getAllTaxes() {
        return taxRepository.findAll();
    }

    public TaxEntity getTaxById(final long id) {
        return taxRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tax not found with id " + id));
    }

    public TaxEntity createTax(final TaxCreateDTO dto) {
        return taxRepository.save(dto.newTax());
    }

    public TaxEntity updateTax(final long id, final TaxCreateDTO dto) {
        if (!taxRepository.existsById(id)) {
            throw new NotFoundException("Tax not found with id " + id);
        }
        TaxEntity tax = dto.newTax();
        tax.setId(id);
        return taxRepository.saveAndFlush(tax);
    }

    public TaxEntity deleteTax(final long id) {
        TaxEntity tax = getTaxById(id);
        taxRepository.deleteById(id);
        return tax;
    }
}
