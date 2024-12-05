package LMS.service;

import java.util.ArrayList;
import java.util.List;

import LMS.model.Membre;
import LMS.model.Role;
import LMS.repository.MembreRepository;
import LMS.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MembreService {
    private final MembreRepository membreRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void saveUser( List<String> selectedRoles , Membre user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        System.out.println(selectedRoles);
        List<Role> roles = new ArrayList<>();

        for (String element : selectedRoles) {
            Role  role = roleRepository.findByName(element);
            if (role == null) {
                role = role(element);
            }
            roles.add(role) ;
        }
        System.out.println("roless" + roles);
        user.setRoles(roles);

        membreRepository.save(user);
    }
    private Role role(String element) {
        Role role = new Role();
        role.setName(element);
        return roleRepository.save(role);
    }
    public Membre findByEmail(String email) {
        return membreRepository.findByEmail(email);
    }

    public Membre findById(Long id) {
        return membreRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Transactional
    public void updateUser(Membre user , List<String> selectedRoles , String pass) {

        if ( pass.equals(user.getPassword() )) {
            user.setPassword(user.getPassword());
            System.out.println("hello");
        }

        else{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            System.out.println("hi");
        }


        List<Role> roles = new ArrayList<>();
        for (String element : selectedRoles) {
            Role  role = roleRepository.findByName(element);
            if (role == null) {
                role = role(element);
            }
            roles.add(role) ;
        }

        user.setRoles(roles);


        membreRepository.save(user);
    }

    public List<Membre> findAllUsers() {
        List<Membre> users = membreRepository.findAll();
        return users;
    }

    public void deleteUserById(Long id) {
        membreRepository.deleteById(id);
    }

}
