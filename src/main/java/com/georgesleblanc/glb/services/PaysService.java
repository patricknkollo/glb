package com.georgesleblanc.glb.services;

import com.georgesleblanc.glb.entities.Pays;
import com.georgesleblanc.glb.repositories.PaysRepository;
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
public class PaysService {

    private Logger logger  = LoggerFactory.getLogger(PaysService.class);
    @Autowired
    private PaysRepository repository;

    public Pays saveCountry(Pays pays){
        logger.info("country saved!");
        return repository.save(pays);
    }

    public ResponseEntity<List<Pays>> findAllCountry(){
        List<Pays> countries = repository.findAll();
        if(!countries.isEmpty()){
            logger.info("the list of countries is not empty");
            ResponseEntity<List<Pays>>responseEntity = new ResponseEntity<>(countries, HttpStatus.OK);
            return responseEntity;
        }
        logger.warn("the list of countries is empty!!!");
        return new ResponseEntity<>(countries, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Pays> updateCountry(Pays country, Long id){
        Optional<Pays> optionalCountry = repository.findById(id);
        if(optionalCountry.isPresent()){
            logger.info("the country is present");
            optionalCountry.get().setHbts(country.getHbts());
            optionalCountry.get().setName(country.getName());
            Pays savedCountry = repository.save(optionalCountry.get());
            return new ResponseEntity<>(savedCountry, HttpStatus.OK);

        }
        logger.warn("the country with the id {} doesn't exist !!!", id);
        return new ResponseEntity<>(country, HttpStatus.NOT_FOUND);
    }

    public void removeCountryWithID(Long id){
        Optional<Pays>optionalEmployee = repository.findById(id);
        if(optionalEmployee.isPresent()){
            logger.info("the country with the id {} exist and has the following name= {}", id,
                    optionalEmployee.get().getName());
            repository.deleteById(id);
        }
        logger.info("the country with the id {} doesn't exist in the database");

    }
}
