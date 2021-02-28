package puc.tcc.logistics.services;


import puc.tcc.logistics.resources.supplier.SupplierRequest;
import puc.tcc.logistics.resources.supplier.SupplierResponse;

import java.util.List;
import java.util.Optional;

public interface SupplierService {
    SupplierResponse saveOrUpdate(final SupplierRequest supplierRequest);

    Optional<SupplierResponse> findById(final Long id);

    List<SupplierResponse> findAll();

    void delete(final Long id);
}
