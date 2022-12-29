package org.binar.userservice.service;


import org.binar.userservice.model.Users;
import org.binar.userservice.model.UsersDetailsImpl;
import org.binar.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
       Users user = userRepository.findByUsername(username).orElseGet(null);
        return UsersDetailsImpl.build(user);
    }


}
