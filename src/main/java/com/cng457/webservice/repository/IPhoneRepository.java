package com.cng457.webservice.repository;

import com.cng457.webservice.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPhoneRepository extends JpaRepository<Phone, Long> {
}
