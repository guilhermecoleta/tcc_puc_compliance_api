package puc.tcc.logistics.services;

import puc.tcc.logistics.resources.supply.SupplyRequest;
import puc.tcc.logistics.resources.supply.SupplyResponse;

public interface SupplyService {

    SupplyResponse create(final SupplyRequest supplyRequest);
}
