package challenge.repositories;

import challenge.dto.SatelliteDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SatelliteRepository extends MongoRepository<SatelliteDTO, String> {

    SatelliteDTO findByName(String name);

    List<SatelliteDTO> findAll();

    @Override
    void deleteAll();
}
