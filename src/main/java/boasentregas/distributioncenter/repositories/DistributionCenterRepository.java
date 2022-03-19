package boasentregas.distributioncenter.repositories;

import boasentregas.distributioncenter.models.DistributionCenter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DistributionCenterRepository extends MongoRepository<DistributionCenter,String>,DistributionCenterCustomRepository {

}
