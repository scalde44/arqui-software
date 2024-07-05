package edu.co.usbcali.arqui.ejercicio.repository;

import edu.co.usbcali.arqui.ejercicio.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface H2DBUserRepository extends JpaRepository<User, String> {
}
