package com.isaachome.blog.security;

import com.isaachome.blog.entity.Role;
import com.isaachome.blog.entity.User;
import com.isaachome.blog.exception.ResourceNotFoundException;
import com.isaachome.blog.repos.RoleRepo;
import com.isaachome.blog.repos.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepo userRepo;
    private RoleRepo roleRepo;

    public CustomUserDetailsService(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
       User user= userRepo.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with username or email:"+usernameOrEmail));


        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                mapRolesToAuthority(user.getRoles()));
    }
    private Collection< ? extends GrantedAuthority> mapRolesToAuthority(Set<Role> roles){
      return   roles.stream().map((role)->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
