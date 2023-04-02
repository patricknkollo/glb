package com.georgesleblanc.glb.servicesTests;

import com.georgesleblanc.glb.entities.Employee;
import com.georgesleblanc.glb.entities.Ville;
import com.georgesleblanc.glb.repositories.VilleRepository;
import com.georgesleblanc.glb.services.VilleService;
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

@SpringBootTest(classes = {VilleServiceTest.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VilleServiceTest {


    private Logger logger  = LoggerFactory.getLogger(VilleService.class);

    @InjectMocks
    private VilleService service;
    @Mock
    private VilleRepository repository;

    private Ville ville1 = new Ville();
    private Ville ville2 = new Ville();

    @BeforeEach
    void setUp(){
        ville1.setVilleid(1L);
        ville1.setPaysid(1L);
        ville1.setPaysname("deutschland");
        ville1.setName("Essen");

        ville2.setVilleid(2L);
        ville2.setPaysid(2L);
        ville2.setPaysname("Kamerun");
        ville2.setName("Douala");
    }

    @Test
    void  test_saveEmployee(){
        Mockito.when(repository.save(ville1)).thenReturn(ville2);
        Ville result = service.saveVille(ville1);
        Assertions.assertEquals(ville2, result);
    }

    @Test
    void test_findAllEmployees(){
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(ville1, ville2));
        ResponseEntity<List<Ville>> result = service.findAllVille();
        Assertions.assertEquals(Arrays.asList(ville1, ville2), result.getBody());
    }

    @Test
    void test_updateEmployee(){
        ResponseEntity<Ville> expected = new ResponseEntity<>(ville2, HttpStatus.OK);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(ville1));
        Mockito.when(repository.save(ville1)).thenReturn(ville2);
        ResponseEntity<Ville> result = service.updateVille(ville1, 1L);
        Assertions.assertEquals(expected, result);

    }

    @Test
    void test_removeEmployeeWithID(){

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(ville1));
        service.removeVilleWithID(1L);
        Mockito.verify(repository).deleteById(1L);

    }
}
