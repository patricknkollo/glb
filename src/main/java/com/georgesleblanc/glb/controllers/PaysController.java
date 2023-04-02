package com.georgesleblanc.glb.controllers;

import com.georgesleblanc.glb.entities.Pays;
import com.georgesleblanc.glb.services.PaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Component
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/pays")
public class PaysController {


    @Autowired
    private PaysService service;

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public @ResponseBody Pays saveCountry(@RequestBody Pays country){
        return service.saveCountry(country);
    }

    @RequestMapping(path = "/countries", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<Pays>> findAllCountries(){
        return  service.findAllCountry();
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<Pays> updateCountry(@RequestBody Pays pays,@PathVariable Long id){
        return service.updateCountry(pays, id);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public void removeCountryWithID(@RequestParam Long id){
        service.removeCountryWithID(id);

    }
}
