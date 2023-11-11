package com.marjane.Repositories;

import com.marjane.models.Centre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CentreRepository extends JpaRepository<Centre, UUID> {
}
