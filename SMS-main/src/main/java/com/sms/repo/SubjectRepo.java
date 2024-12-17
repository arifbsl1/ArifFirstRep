package com.sms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.entity.Subjects;
import java.util.List;
import java.util.Optional;


public interface SubjectRepo extends JpaRepository<Subjects, Integer>{

	Optional<Subjects> findBySbujectCode(String sbujectCode);
}
