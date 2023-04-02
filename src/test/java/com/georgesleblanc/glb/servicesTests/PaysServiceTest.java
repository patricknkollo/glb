package com.georgesleblanc.glb.servicesTests;

import com.georgesleblanc.glb.entities.Pays;
import com.georgesleblanc.glb.repositories.PaysRepository;
import com.georgesleblanc.glb.services.PaysService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = {PaysServiceTest.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaysServiceTest {


    private Logger logger  = LoggerFactory.getLogger(PaysService.class);

    @InjectMocks
    private PaysService service;
    @Mock
    private PaysRepository repository;

    private Pays pays1 = new Pays();
    private Pays pays2 = new Pays();

    @BeforeEach
    void setUp(){
        pays1.setHbts(80000000);
        pays1.setName("deutschland");
        pays1.setPaysid(1L);

        pays2.setHbts(30000000);
        pays2.setName("Kamerun");
        pays2.setPaysid(2L);
    }

    @Test
    void  test_saveEmployee(){
        Mockito.when(repository.save(pays1)).thenReturn(pays2);
        Pays result = service.saveCountry(pays1);
        Assertions.assertEquals(pays2, result);
    }

    @Test
    void test_findAllEmployees(){
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(pays1, pays2));
        ResponseEntity<List<Pays>> result = service.findAllCountry();
        Assertions.assertEquals(Arrays.asList(pays1, pays2), result.getBody());
    }

    @Test
    void test_updateEmployee(){
        ResponseEntity<Pays> expected = new ResponseEntity<>(pays2, HttpStatus.OK);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(pays1));
        Mockito.when(repository.save(pays1)).thenReturn(pays2);
        ResponseEntity<Pays> result = service.updateCountry(pays1, 1L);
        Assertions.assertEquals(expected, result);

    }

    @Test
    void test_removeEmployeeWithID(){

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(pays1));
        service.removeCountryWithID(1L);
        Mockito.verify(repository).deleteById(1L);

    }
}
