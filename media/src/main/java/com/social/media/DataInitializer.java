package com.social.media;

import com.social.media.models.Post;
import com.social.media.models.SocialProfile;
import com.social.media.models.SocialUser;
import com.social.media.models.SocialGroup;
import com.social.media.repositories.PostRepository;
import com.social.media.repositories.SocialGroupRepository;
import com.social.media.repositories.SocialProfileRepository;
import com.social.media.repositories.SocialUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    private final SocialUserRepository socialUserRepository;
    private final SocialGroupRepository socialGroupRepository;
    private final SocialProfileRepository socialProfileRepository;
    private final PostRepository postRepository;

    public DataInitializer(SocialUserRepository socialUserRepository, SocialUserRepository socialUserRepository1, SocialGroupRepository socialGroupRepository, SocialProfileRepository socialProfileRepository, PostRepository postRepository){

        this.socialUserRepository = socialUserRepository;
        this.socialGroupRepository = socialGroupRepository;
        this.socialProfileRepository = socialProfileRepository;
        this.postRepository = postRepository;
    }

    @Bean
    public CommandLineRunner initializeData(){
        return args -> {
            SocialUser User1 = new SocialUser();
            SocialUser User2 = new SocialUser();
            SocialUser User3 = new SocialUser();

            // save users to the database
            socialUserRepository.save(User1);
            socialUserRepository.save(User2);
            socialUserRepository.save(User3);


            SocialGroup Group1 = new SocialGroup();
            SocialGroup Group2 = new SocialGroup();

            Group1.getSocialUsers().add(User1);
            Group1.getSocialUsers().add(User2);

            Group2.getSocialUsers().add(User2);
            Group2.getSocialUsers().add(User3);

            socialGroupRepository.save(Group1);
            socialGroupRepository.save(Group2);

            User1.getGroups().add(Group1);
            User2.getGroups().add(Group1);
            User2.getGroups().add(Group2);
            User3.getGroups().add(Group2);

            socialUserRepository.save(User1);
            socialUserRepository.save(User2);
            socialUserRepository.save(User3);


            Post post1 = new Post();
            Post post2 = new Post();
            Post post3 = new Post();

            post1.setSocialUser(User1);
            post2.setSocialUser(User2);
            post3.setSocialUser(User3);

            postRepository.save(post1);
            postRepository.save(post2);
            postRepository.save(post3);

            SocialProfile profile1 = new SocialProfile();
            SocialProfile profile2 = new SocialProfile();
            SocialProfile profile3 = new SocialProfile();

            profile1.setUser(User1);
            profile2.setUser(User2);
            profile3.setUser(User3);

            socialProfileRepository.save(profile1);
            socialProfileRepository.save(profile2);
            socialProfileRepository.save(profile3);
        };
    }


}
