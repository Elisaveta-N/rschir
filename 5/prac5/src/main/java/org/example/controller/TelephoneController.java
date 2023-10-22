package org.example.controller;

import org.example.model.Telephone;
import org.example.service.TelephoneService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/telephones")
public class TelephoneController {
    private static TelephoneService telephoneService;

    @Autowired
    public TelephoneController(TelephoneService telephoneService) {
        this.telephoneService = telephoneService;
    }


    @GetMapping
    public ResponseEntity<List<Telephone>> list()
    {
        var telephones = telephoneService.readAll();
        return telephones != null
                ? new ResponseEntity<>(telephones, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    private ResponseEntity<Boolean> addTelephone(@RequestBody Telephone telephone)
    {
        return telephoneService.create(telephone) == true
                ? new ResponseEntity<>(true, HttpStatus.CREATED)
                : new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}")
    private ResponseEntity<Telephone> getOneTelephone(@PathVariable Long id) {
        var telephone =  telephoneService.read(id);
        return telephone != null && !telephone.isEmpty()
                ? new ResponseEntity<>(telephone.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable(name="id") long id, @RequestBody Telephone telephone) {
        var telephoneFromDbOpt = telephoneService.read(id);
        if(telephoneFromDbOpt.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        var telephoneFromDb = telephoneFromDbOpt.get();
        BeanUtils.copyProperties(telephone, telephoneFromDb, "id");
        return telephoneService.update(telephone, telephoneFromDb.getId()) == true
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return telephoneService.delete(id) == true
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
