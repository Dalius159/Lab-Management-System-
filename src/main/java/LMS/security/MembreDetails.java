package LMS.security;

import java.util.List;
import java.util.stream.Collectors;

import LMS.model.Membre;
import LMS.model.Role;
import LMS.repository.MembreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembreDetails  implements UserDetailsService  {

    private final MembreRepository membreRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Membre membre = membreRepository.findByEmail(email);

        if (membre != null) {
            return new org.springframework.security.core.userdetails.User(membre.getEmail(),
                    membre.getPassword(),
                    mapRolesToAuthorities(membre.getRoles()));
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private List<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        List<? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }
}

