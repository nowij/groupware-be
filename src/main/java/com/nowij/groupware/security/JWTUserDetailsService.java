package com.nowij.groupware.security;

import com.nowij.groupware.model.DepartmentEntity;
import com.nowij.groupware.model.EmployeeEntity;
import com.nowij.groupware.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JWTUserDetailsService implements UserDetailsService {

    private EmployeeRepository repository;

    @Autowired
    public JWTUserDetailsService(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeEntity employee = repository.findByEmployeeId(username)
                .orElseThrow(() -> new UsernameNotFoundException("Employee ID not found"));

        return new User(employee.getEmployeeId(), employee.getPasswd(), mapRolesToAuthorities(employee.getDepartments()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<DepartmentEntity> departments) {
        return departments
                .stream()
                .map(department -> new SimpleGrantedAuthority(department.getDeptCode()))
                .collect(Collectors.toList());
    }
}
