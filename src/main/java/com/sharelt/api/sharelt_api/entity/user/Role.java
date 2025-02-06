package com.sharelt.api.sharelt_api.entity.user;

import com.sharelt.api.sharelt_api.entity.audit.GenericEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role extends GenericEntity<Long> {

    @Enumerated(EnumType.STRING)
    private RoleType name;

}
