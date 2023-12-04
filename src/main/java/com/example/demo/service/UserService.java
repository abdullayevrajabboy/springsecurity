package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserAuthentication(AbstractAuthenticationToken authenticationToken){
        Map<String,Object> attributes;

        if (authenticationToken instanceof OAuth2AuthenticationToken){
            attributes = ((OAuth2AuthenticationToken) authenticationToken).getPrincipal().getAttributes();
        }else {
            throw new IllegalArgumentException("Error: ");
        }
        return getUser(attributes);
    }



    private User getUser(Map<String,Object> attributes){
        User user = new User();

        if (attributes.get("given_name") != null) {
            user.setFirstName((String) attributes.get("given_name"));
        }
        if (attributes.get("family_name") != null) {
            user.setLastName((String) attributes.get("family_name"));
        }
        if (attributes.get("locale") != null) {
            user.setLangKey((String) attributes.get("locale"));
        }
        if (attributes.get("email_verified") != null) {
            user.setActivated((Boolean) attributes.get("email_verified"));
        }
        if (attributes.get("email") != null) {
            user.setEmail((String) attributes.get("email"));
        }
        if (attributes.get("picture") != null) {
            user.setImageUrl((String) attributes.get("picture"));
        }
        User savedUser = userRepository.save(user);
        return savedUser;
    }


}
