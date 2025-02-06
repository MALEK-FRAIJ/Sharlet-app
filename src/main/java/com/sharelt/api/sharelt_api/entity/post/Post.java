package com.sharelt.api.sharelt_api.entity.post;



import com.sharelt.api.sharelt_api.entity.audit.GenericEntity;
import com.sharelt.api.sharelt_api.entity.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post extends GenericEntity<Long> {

    private String description;
    private String imagePath;
    private String location;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
