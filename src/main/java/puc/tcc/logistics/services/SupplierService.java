package puc.tcc.logistics.services;


import org.springframework.data.domain.Page;
import puc.tcc.logistics.exception.LogisticsException;
import puc.tcc.logistics.persistence.domain.SupplierEntity;
import puc.tcc.logistics.resources.supplier.SupplierRequest;
import puc.tcc.logistics.resources.supplier.SupplierResponse;

import java.util.List;
import java.util.Optional;

public interface SupplierService {
    SupplierResponse saveOrUpdate(final SupplierRequest supplierRequest) throws LogisticsException;

    Page<SupplierEntity> findAll(int page, int size);

    Optional<SupplierResponse> findById(final Long id);

    List<SupplierResponse> findAll();

    void delete(final Long id) throws LogisticsException;
}
