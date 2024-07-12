package co.edu.usbcali.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.demo.domain.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer>{

}
