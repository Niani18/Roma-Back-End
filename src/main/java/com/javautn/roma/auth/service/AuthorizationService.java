package com.javautn.roma.auth.service;

import com.javautn.roma.auth.entity.Role;
import com.javautn.roma.auth.entity.UserEntity;
import com.javautn.roma.auth.repository.UserRepository;
import com.javautn.roma.familyRol.repository.FamilyRolRepository;
import com.javautn.roma.holding.entity.HoldingState;
import com.javautn.roma.holding.repository.HoldingRepository;
import com.javautn.roma.legalCase.repository.LegalCaseRepository;
import com.javautn.roma.sentence.repository.SentenceRepository;
import com.javautn.roma.tax.repository.TaxAssignationRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("authorizationService")
public class AuthorizationService {

    private final UserRepository userRepository;
    private final FamilyRolRepository familyRolRepository;
    private final HoldingRepository holdingRepository;
    private final TaxAssignationRepository taxAssignationRepository;
    private final LegalCaseRepository legalCaseRepository;
    private final SentenceRepository sentenceRepository;

    public AuthorizationService(
            UserRepository userRepository,
            FamilyRolRepository familyRolRepository,
            HoldingRepository holdingRepository,
            TaxAssignationRepository taxAssignationRepository,
            LegalCaseRepository legalCaseRepository,
            SentenceRepository sentenceRepository) {
        this.userRepository = userRepository;
        this.familyRolRepository = familyRolRepository;
        this.holdingRepository = holdingRepository;
        this.taxAssignationRepository = taxAssignationRepository;
        this.legalCaseRepository = legalCaseRepository;
        this.sentenceRepository = sentenceRepository;
    }

    @Transactional(readOnly = true)
    public boolean isCurrentCitizen(long citizenId) {
        Long currentCitizenId = currentCitizenId();
        return currentCitizenId != null && currentCitizenId == citizenId;
    }

    @Transactional(readOnly = true)
    public boolean isCurrentUserActive() {
        UserEntity user = currentUser();
        return user != null && user.isActive();
    }

    @Transactional(readOnly = true)
    public boolean canAccessFamily(long familyId) {
        Long currentCitizenId = currentCitizenId();
        if (currentCitizenId == null) {
            return false;
        }

        return familyRolRepository.findByCitizenId(currentCitizenId).stream()
                .anyMatch(role -> role.getDateOfUnjoining() == null && role.getFamily().getId() == familyId);
    }

    @Transactional(readOnly = true)
    public boolean canAccessProperty(long propertyId) {
        return holdingRepository.findByPropertyIdAndState(propertyId, HoldingState.ACTIVE).stream()
                .anyMatch(holding -> canAccessFamily(holding.getFamily().getId()));
    }

    @Transactional(readOnly = true)
    public boolean canAccessTaxAssignation(long taxAssignationId) {
        return taxAssignationRepository.findById(taxAssignationId)
                .map(assignation -> {
                    if (assignation.getFamily() != null) {
                        return canAccessFamily(assignation.getFamily().getId());
                    }
                    if (assignation.getProperty() != null) {
                        return canAccessProperty(assignation.getProperty().getId());
                    }
                    return false;
                })
                .orElse(false);
    }

    @Transactional(readOnly = true)
    public boolean canAccessLegalCase(long legalCaseId) {
        Long currentCitizenId = currentCitizenId();
        if (currentCitizenId == null) {
            return false;
        }

        return legalCaseRepository.findById(legalCaseId)
                .map(legalCase -> legalCase.getCitizen().getId() == currentCitizenId)
                .orElse(false);
    }

    @Transactional(readOnly = true)
    public boolean canAccessSentence(long sentenceId) {
        Long currentCitizenId = currentCitizenId();
        if (currentCitizenId == null) {
            return false;
        }

        return sentenceRepository.findById(sentenceId)
                .map(sentence -> sentence.getLegalCase().getCitizen().getId() == currentCitizenId)
                .orElse(false);
    }

    private Long currentCitizenId() {
        UserEntity user = currentUser();
        if (user == null || user.getRole() != Role.USER || user.getCitizen() == null || !user.isActive()) {
            return null;
        }
        return user.getCitizen().getId();
    }

    private UserEntity currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            return null;
        }

        try {
            long userId = Long.parseLong(authentication.getName());
            return userRepository.findById(userId).orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
