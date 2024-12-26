
package com.nhat.spring.service;

import com.nhat.spring.Repository.MemberRepository;
import com.nhat.spring.Repository.RoleRepository;
import com.nhat.spring.model.Member;
import com.nhat.spring.model.Role;
import java.util.ArrayList;
import java.util.List;
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

    @Transactional
    public void saveUserWithDefaultRole(Member user) {
        // Mã hóa mật khẩu
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Gán role mặc định là VISITOR
        Role visitorRole = roleRepository.findByName("VISITOR");
        if (visitorRole == null) {
            visitorRole = new Role();
            visitorRole.setName("VISITOR");
            roleRepository.save(visitorRole);
        }

        List<Role> roles = new ArrayList<>();
        roles.add(visitorRole);
        user.setRoles(roles);

        // Lưu user vào database
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
