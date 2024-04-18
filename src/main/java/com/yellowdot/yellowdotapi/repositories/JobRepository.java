package com.yellowdot.yellowdotapi.repositories;

import com.yellowdot.yellowdotapi.entities.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Vacancy, Long> {
}
