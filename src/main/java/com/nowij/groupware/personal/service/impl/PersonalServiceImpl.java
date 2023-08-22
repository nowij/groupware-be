package com.nowij.groupware.personal.service.impl;

import com.nowij.groupware.employee.domain.EmployeeEntity;
import com.nowij.groupware.employee.repository.EmployeeRepository;
import com.nowij.groupware.personal.dto.PersonalDto;
import com.nowij.groupware.personal.service.PersonalService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class PersonalServiceImpl implements PersonalService {
    private EmployeeRepository repository;
    private PasswordEncoder encoder;
    public PersonalServiceImpl(EmployeeRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }
    @Override
    public PersonalDto selectInfo(String employeeId) {
        Optional<EmployeeEntity> optional = repository.findById(employeeId);
        if (optional.isPresent()) {
            return entityToDto(optional.get());
        }
        return null;
    }

    @Override
    public PersonalDto saveInfo(PersonalDto dto) {
        EmployeeEntity entity = repository.findByEmployeeId(dto.getEmployeeId()).get();
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
        // TODO 사진 업로드
        entityToDto(repository.save(entity));
        return dto;
    }

    @Override
    public int savePwd(PersonalDto dto) {
        Optional<EmployeeEntity> optional = repository.findById(dto.getEmployeeId());
        String newPwd = encoder.encode(dto.getNewPasswd());
        String oldPwd = dto.getUserPasswd();

        if (optional.isPresent()) {
            EmployeeEntity entity = optional.get();
            if (encoder.matches(oldPwd, entity.getPasswd())) {
                entity.setPasswd(newPwd);
                repository.save(entity);
                return 1;
            }
        }
        return 0;
    }

    private PersonalDto entityToDto(EmployeeEntity entity) {
        PersonalDto dto = new PersonalDto();
        dto.setEmployeeId(entity.getEmployeeId());
        dto.setUserName(entity.getName());
        dto.setUserPasswd(entity.getPasswd());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setAddress(entity.getAddress());
        dto.setImage(entity.getImage());
        dto.setDepartment(entity.getDepartment());
        dto.setPosition(entity.getPosition());
        return dto;
    }
}
