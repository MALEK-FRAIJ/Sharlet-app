package com.sharelt.api.sharelt_api.entity.post;

import com.sharelt.api.sharelt_api.entity.audit.GenericEntity;
import com.sharelt.api.sharelt_api.entity.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "likes")
public class Like extends GenericEntity<Long> {


    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "story_id")
    private Story story;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
