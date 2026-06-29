package com.javautn.roma.shared;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public abstract class EntitySpecifications {

    public static Pageable page(Integer page, Integer limit) {
        return PageRequest.of(page == null ? 0 : page, limit == null ? 20 : limit);
    }
}
