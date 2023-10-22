package org.example.controller;

import org.example.model.WashingMachine;
import org.example.service.WashingMachineService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/washing_machines")
public class WashingMachineController {
    private static WashingMachineService washingMachineService;

    @Autowired
    public WashingMachineController(WashingMachineService washingMachineService) {
        this.washingMachineService = washingMachineService;
    }


    @GetMapping
    public ResponseEntity<List<WashingMachine>> list()
    {
        var washingMachines = washingMachineService.readAll();
        return washingMachines != null
                ? new ResponseEntity<>(washingMachines, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    private ResponseEntity<Boolean> addWashingMachine(@RequestBody WashingMachine washingMachine)
    {
        return washingMachineService.create(washingMachine) == true
                ? new ResponseEntity<>(true, HttpStatus.CREATED)
                : new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}")
    private ResponseEntity<WashingMachine> getOneWashingMachine(@PathVariable Long id) {
        var washingMachine =  washingMachineService.read(id);
        return washingMachine != null && !washingMachine.isEmpty()
                ? new ResponseEntity<>(washingMachine.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable(name="id") long id, @RequestBody WashingMachine washingMachine) {
        var washingMachineFromDbOpt = washingMachineService.read(id);
        if(washingMachineFromDbOpt.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        var washingMachineFromDb = washingMachineFromDbOpt.get();
        BeanUtils.copyProperties(washingMachine, washingMachineFromDb, "id");
        return washingMachineService.update(washingMachine, washingMachineFromDb.getId()) == true
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return washingMachineService.delete(id) == true
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
