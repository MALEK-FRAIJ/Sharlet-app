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
@Table(name = "comments")
public class Comment extends GenericEntity<Long> {

    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "story_id")
    private Story story;

}
