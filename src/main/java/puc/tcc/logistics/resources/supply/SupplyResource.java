package puc.tcc.logistics.resources.supply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import puc.tcc.logistics.services.SupplyService;

@RestController()
public class SupplyResource {

    @Autowired
    private SupplyService supplyService;

    @PostMapping(value = "/supplies", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SupplyResponse> create(@RequestBody final SupplyRequest supplyRequest){

        return ResponseEntity.status(HttpStatus.CREATED).body(supplyService.create(supplyRequest));
    }
}
