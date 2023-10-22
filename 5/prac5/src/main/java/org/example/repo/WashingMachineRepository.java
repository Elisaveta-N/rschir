package org.example.repo;


import org.example.model.WashingMachine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WashingMachineRepository extends JpaRepository<WashingMachine, Long> {
}
