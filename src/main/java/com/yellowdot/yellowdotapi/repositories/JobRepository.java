package com.yellowdot.yellowdotapi.repositories;

import com.yellowdot.yellowdotapi.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}
