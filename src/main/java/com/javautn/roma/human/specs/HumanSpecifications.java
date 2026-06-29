package com.javautn.roma.human.specs;

import com.javautn.roma.human.entity.CitizenEntity;
import com.javautn.roma.shared.EntitySpecifications;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

// No lo parece, pero esto es el query builder
public abstract class HumanSpecifications extends EntitySpecifications {
    public static Specification<CitizenEntity> hasName(String name) {
        return (root, query, cb) -> name == null ? null : cb.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<CitizenEntity> isId(Long id) {
        return (root, query, cb) -> id == null ? null : cb.equal(root.get("id"), id);
    }

    public static JpaSort sortBy(List<String> columns) {
        return null;
    }
}
