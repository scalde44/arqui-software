package edu.co.usbcali.arqui.ejercicio.service;

import edu.co.usbcali.arqui.ejercicio.domain.dto.UserDto;

public interface UserService {
    UserDto getById(String id);

    void save(UserDto user);
}
