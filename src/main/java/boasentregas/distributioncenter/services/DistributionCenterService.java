package boasentregas.distributioncenter.services;

import boasentregas.distributioncenter.models.DistributionCenter;

import java.util.List;

public interface DistributionCenterService {

    void save(DistributionCenter distributionCenter);

    List<DistributionCenter> findAll();

    DistributionCenter findByZipCode(int zipCode);

    DistributionCenter findByName(String name);

}
