package puc.tcc.logistics.services;

import puc.tcc.logistics.resources.supply.SupplyRequest;
import puc.tcc.logistics.resources.supply.SupplyResponse;

import java.util.Optional;

public interface SupplyService {

    SupplyResponse create(final SupplyRequest supplyRequest);

    Optional<SupplyResponse> findById(Long id);
}
