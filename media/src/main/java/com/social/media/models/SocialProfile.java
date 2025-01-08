package com.social.media.models;

import jakarta.persistence.*;

@Entity
public class SocialProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name="user_id")
    private SocialUser user;
}
