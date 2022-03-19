package boasentregas.distributioncenter.repositories.impl;

import boasentregas.distributioncenter.models.DistributionCenter;
import boasentregas.distributioncenter.repositories.DistributionCenterCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DistributionCenterCustomRepositoryImpl implements DistributionCenterCustomRepository {

    @Autowired
    MongoTemplate mongoTemplate;


    private static final String FIELD = "address.zipcode";

    @Override
    public DistributionCenter findByName(String name)
    {
        String field = "name";
        Query query = new Query();
        List<Criteria> criteria = new ArrayList<>();
        criteria.add(Criteria.where(field).is(name) );
        query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        return mongoTemplate.findOne(query, DistributionCenter.class);
    }

    @Override
    public DistributionCenter findByZipCode(int zipcode)
    {
        int smaller=0,larger=0;

        Query query = new Query().with(Sort.by(Sort.Direction.DESC,FIELD));
        List<Criteria> criteria = findByZipCodeL(zipcode);
        query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        DistributionCenter distributionCenter = mongoTemplate.findOne(query, DistributionCenter.class);

        query = new Query().with(Sort.by(Sort.Direction.ASC,FIELD));
        criteria = findByZipCodeG(zipcode);
        query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        DistributionCenter distributionCenter1 = mongoTemplate.findOne(query, DistributionCenter.class);


        if (distributionCenter != null)
            smaller = zipcode - distributionCenter.getAddress().getZipcode();
        else
            smaller = Integer.MAX_VALUE;

        if (distributionCenter1 != null)
            larger =  distributionCenter1.getAddress().getZipcode() - zipcode ;
        else
            larger = Integer.MAX_VALUE;


        if (larger < smaller)
            return distributionCenter1;
        else
            return distributionCenter;
    }


    private List<Criteria> findByZipCodeL(int zipcode)
    {
        final List<Criteria> criteria = new ArrayList<>();
        criteria.add(Criteria.where(FIELD).lte(zipcode) );
        return criteria;
    }


    private List<Criteria> findByZipCodeG(int zipcode)
    {
        final List<Criteria> criteria = new ArrayList<>();
        criteria.add(Criteria.where(FIELD).gte(zipcode) );
        return criteria;
    }

}
