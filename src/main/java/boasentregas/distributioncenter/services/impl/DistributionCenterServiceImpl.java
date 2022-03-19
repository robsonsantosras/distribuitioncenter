package boasentregas.distributioncenter.services.impl;

import boasentregas.distributioncenter.models.Address;
import boasentregas.distributioncenter.models.DistributionCenter;
import boasentregas.distributioncenter.repositories.DistributionCenterRepository;
import boasentregas.distributioncenter.services.DistributionCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistributionCenterServiceImpl implements DistributionCenterService {

    @Autowired
    private DistributionCenterRepository distributionCenterRepository;

    @Override
    public void save(DistributionCenter distributionCenter)
    {
        distributionCenterRepository.save(distributionCenter);
    }

    @Override
    public List<DistributionCenter> findAll()
    {
        return distributionCenterRepository.findAll();
    }

    @Override
    public DistributionCenter findByZipCode(int zipCode)
    {
        return distributionCenterRepository.findByZipCode(zipCode);
    }

    @Override
    public DistributionCenter findByName(String name)
    {
        return distributionCenterRepository.findByName(name);
    }
}
