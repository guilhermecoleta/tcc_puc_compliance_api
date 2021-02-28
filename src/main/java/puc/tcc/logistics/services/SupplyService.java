package puc.tcc.logistics.services;

import puc.tcc.logistics.resources.supply.SupplyRequest;
import puc.tcc.logistics.resources.supply.SupplyResponse;

import java.util.List;
import java.util.Optional;

public interface SupplyService {

    SupplyResponse saveOrUpdate(final SupplyRequest supplyRequest);

    Optional<SupplyResponse> findById(final Long id);

    List<SupplyResponse> findAll();

    void delete(final Long id);
}
