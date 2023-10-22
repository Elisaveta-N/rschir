package com.example.jwttestapp.repo;


import com.example.jwttestapp.model.WashingMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WashingMachineRepository extends JpaRepository<WashingMachine, Long> {
}
