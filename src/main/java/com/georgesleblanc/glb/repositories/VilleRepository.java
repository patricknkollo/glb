package com.georgesleblanc.glb.repositories;

import com.georgesleblanc.glb.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface VilleRepository extends JpaRepository<Ville, Long> {
}
