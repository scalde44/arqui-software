package edu.co.usbcali.arqui.ejercicio.service;

import edu.co.usbcali.arqui.ejercicio.domain.dto.UserDto;
import edu.co.usbcali.arqui.ejercicio.domain.model.User;
import edu.co.usbcali.arqui.ejercicio.repository.H2DBUserRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private final H2DBUserRepositoryAdapter userRepositoryAdapter;

    @Override
    public UserDto getById(String id) {
        var user = this.userRepositoryAdapter.getById(id);
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }

    @Override
    public void save(UserDto user) {
        if (!isValidEmail(user.getEmail())) {
            throw new InvalidParameterException("Email no es correcto");
        }
        if (Objects.isNull(user.getName()) || user.getName().isBlank()) {
            throw new InvalidParameterException("Nombre no puede ser nulo o vacío");
        }
        var userToSave = new User(user.getName(), user.getEmail());
        this.userRepositoryAdapter.save(userToSave);
    }

    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
}
