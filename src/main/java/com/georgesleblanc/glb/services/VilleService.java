package com.georgesleblanc.glb.services;

import com.georgesleblanc.glb.entities.Ville;
import com.georgesleblanc.glb.repositories.VilleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class VilleService {

    private Logger logger  = LoggerFactory.getLogger(VilleService.class);
    @Autowired
    private VilleRepository repository;

    public Ville saveVille(Ville ville){
        logger.info("employee saved!");
        return repository.save(ville);
    }

    public ResponseEntity<List<Ville>> findAllVille(){
        List<Ville> villes = repository.findAll();
        if(!villes.isEmpty()){
            logger.info("the list of villes is not empty");
            ResponseEntity<List<Ville>>responseEntity = new ResponseEntity<>(villes, HttpStatus.OK);
            return responseEntity;
        }
        logger.warn("the list of villes is empty!!!");
        return new ResponseEntity<>(villes, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Ville> updateVille(Ville ville, Long id){
        Optional<Ville> optionalVille = repository.findById(id);
        if(optionalVille.isPresent()){
            logger.info("the ville is present");
            optionalVille.get().setPaysname(ville.getPaysname());
            optionalVille.get().setName(ville.getName());
            optionalVille.get().setPaysid(ville.getPaysid());
            Ville savedVille = repository.save(optionalVille.get());
            return new ResponseEntity<>(savedVille, HttpStatus.OK);

        }
        logger.warn("the ville with the id {} doesn't exist !!!", id);
        return new ResponseEntity<>(ville, HttpStatus.NOT_FOUND);
    }

    public void removeVilleWithID(Long id){
        Optional<Ville>optionalville = repository.findById(id);
        if(optionalville.isPresent()){
            logger.info("the 'ville' with the id {} exist and has the following name= {}", id,
                    optionalville.get().getName());
            repository.deleteById(id);
        }
        else {
            logger.info("the 'Ville' with the id {} doesn't exist in the database");
        }

    }

    public void removeVilleWithID2(Long id){
            repository.deleteById(id);
    }
}
