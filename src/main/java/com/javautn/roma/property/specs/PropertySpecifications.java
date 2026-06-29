package com.javautn.roma.property.specs;

import com.javautn.roma.property.entity.PropertyEntity;
import com.javautn.roma.shared.EntitySpecifications;
import org.springframework.data.jpa.domain.Specification;

public class PropertySpecifications extends EntitySpecifications {
    public static Specification<PropertyEntity> isId(Long id) {
        return (root, query, cq) -> id == null ? null : cq.equal(root.get("id"), id);
    }

    public static Specification<PropertyEntity> belongsTo(Long familyId) {
        return (root, query, qb) -> null;
    }
}
