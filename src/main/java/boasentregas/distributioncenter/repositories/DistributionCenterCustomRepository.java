package boasentregas.distributioncenter.repositories;

import boasentregas.distributioncenter.models.DistributionCenter;

import java.util.List;

public interface DistributionCenterCustomRepository {
    DistributionCenter findByName(String name);
    DistributionCenter findByZipCode(int zipcode);
}
