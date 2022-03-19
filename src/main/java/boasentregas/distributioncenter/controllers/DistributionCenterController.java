package boasentregas.distributioncenter.controllers;

import boasentregas.distributioncenter.models.DistributionCenter;
import boasentregas.distributioncenter.services.DistributionCenterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("distributioncenter/v1")
public class DistributionCenterController {

    private final DistributionCenterService distributionCenterService;

    @GetMapping("/distributioncenters")
    public ResponseEntity<List<DistributionCenter>> Get()
    {
        List<DistributionCenter> distributionCenter = distributionCenterService.findAll();

        return new ResponseEntity<>(distributionCenter, HttpStatus.OK);
    }

    @GetMapping("/distributioncenters/name/{name}")
    public ResponseEntity<DistributionCenter> findByZipName(@PathVariable("name") String name)
    {
        DistributionCenter distributionCenter = distributionCenterService.findByName(name);
        if (distributionCenter == null)
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(distributionCenter, HttpStatus.OK);
    }


    @GetMapping("/distributioncenters/{zipcode}")
    public ResponseEntity<DistributionCenter> findByZipCode(@PathVariable int zipcode)
    {
        DistributionCenter distributionCenter = distributionCenterService.findByZipCode(zipcode);
        return new ResponseEntity<>(distributionCenter, HttpStatus.OK);
    }


    @PostMapping("/distributioncenters")
    public ResponseEntity<DistributionCenter> post(@RequestBody DistributionCenter distributionCenter)
    {
        distributionCenterService.save(distributionCenter);
        return new ResponseEntity<>(distributionCenter, HttpStatus.CREATED);
    }


}
