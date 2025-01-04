package net.soumya.JournalApp.services;

import net.soumya.JournalApp.entity.Users;
import net.soumya.JournalApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo; // Dependency injection for accessing user data from the database.

    /**
     * This method is invoked by Spring Security when a user tries to authenticate.
     * It loads the user's details (username, password, roles) from the database.
     *
     * @param username The username entered by the user.
     * @return UserDetails object containing username, password, and authorities (roles).
     * @throws UsernameNotFoundException If the user with the given username is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user entity from the repository using the username.
        Users user = userRepo.findByUsername(username);

        // If the user is not found, throw an exception.
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        // Log the username of the loaded user (for debugging purposes).
        System.out.println("Loaded user: " + user.getUsername());

        // Map the user's roles to SimpleGrantedAuthority objects required by Spring Security.
        // If the user has no roles, return an empty list of authorities.
        List<SimpleGrantedAuthority> authorities = (user.getRoles() != null && !user.getRoles().isEmpty())
                ? user.getRoles().stream() // Convert roles (Strings) into SimpleGrantedAuthority objects.
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()) // Collect as a list.
                : Collections.emptyList(); // If no roles, return an empty list.

        // Return a UserDetails implementation (provided by Spring Security) containing
        // the username, encoded password, and authorities (roles).
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), // Username.
                user.getPassword(), // Encoded password.
                authorities // Granted authorities (roles).
        );
    }
}
