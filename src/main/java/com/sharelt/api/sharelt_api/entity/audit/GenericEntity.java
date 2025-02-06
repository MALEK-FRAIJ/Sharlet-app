package com.sharelt.api.sharelt_api.entity.audit;



import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public abstract class GenericEntity<ID> extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

}