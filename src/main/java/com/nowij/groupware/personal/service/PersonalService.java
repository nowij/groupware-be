package com.nowij.groupware.personal.service;

import com.nowij.groupware.personal.dto.PersonalDto;

public interface PersonalService {
    PersonalDto selectInfo(String employeeId);
    PersonalDto saveInfo(PersonalDto dto);
    int savePwd(PersonalDto dto);
}
