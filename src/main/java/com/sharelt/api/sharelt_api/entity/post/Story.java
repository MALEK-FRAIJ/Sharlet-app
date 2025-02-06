package com.sharelt.api.sharelt_api.entity.post;

import java.time.LocalDateTime;

import com.sharelt.api.sharelt_api.entity.audit.GenericEntity;
import com.sharelt.api.sharelt_api.entity.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "stories")
public class Story extends GenericEntity<Long> {

    private String caption;
    private String imageUrl;
    private LocalDateTime expiryTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
