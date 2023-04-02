package com.georgesleblanc.glb.controllers;

import com.georgesleblanc.glb.entities.Pays;
import com.georgesleblanc.glb.entities.Ville;
import com.georgesleblanc.glb.services.PaysService;
import com.georgesleblanc.glb.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Component
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/ville")
public class VilleController {

    @Autowired
    private VilleService service;

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public @ResponseBody Ville saveTown(@RequestBody Ville ville){
        return service.saveVille(ville);
    }

    @RequestMapping(path = "/villes", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<Ville>> findAllTowns(){
        return  service.findAllVille();
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<Ville> updateTown(@RequestBody Ville ville,@PathVariable Long id){
        return service.updateVille(ville, id);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public void removeTownWithID(@RequestParam Long id){
        service.removeVilleWithID(id);

    }
}
