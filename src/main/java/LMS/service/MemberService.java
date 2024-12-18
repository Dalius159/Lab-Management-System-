package LMS.service;

import java.util.ArrayList;
import java.util.List;

import LMS.model.Member;
import LMS.model.Role;
import LMS.repository.MemberRepository;
import LMS.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void saveUser( List<String> selectedRoles , Member user) {

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

        memberRepository.save(user);
    }
    private Role role(String element) {
        Role role = new Role();
        role.setName(element);
        return roleRepository.save(role);
    }
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Transactional
    public void updateUser(Member user , List<String> selectedRoles , String pass) {

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


        memberRepository.save(user);
    }

    public List<Member> findAllUsers() {
        List<Member> users = memberRepository.findAll();
        return users;
    }

    public void deleteUserById(Long id) {
        memberRepository.deleteById(id);
    }

}
