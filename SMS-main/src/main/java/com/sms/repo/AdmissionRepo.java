package com.sms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.entity.Admission;

public interface AdmissionRepo extends JpaRepository<Admission, Integer>{

}
