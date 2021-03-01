package puc.tcc.logistics.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import puc.tcc.logistics.mapper.SupplierMapper;
import puc.tcc.logistics.persistence.repositories.SupplierRepository;
import puc.tcc.logistics.resources.supplier.SupplierRequest;
import puc.tcc.logistics.resources.supplier.SupplierResponse;
import puc.tcc.logistics.services.SupplierService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private final SupplierMapper supplierMapper;

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    @Transactional
    public SupplierResponse saveOrUpdate(final SupplierRequest supplierRequest) {
        var model = supplierMapper.toModel(supplierRequest);
        model = supplierRepository.save(model);
        return supplierMapper.toResponse(model);
    }

    @Override
    public Optional<SupplierResponse> findById(final Long id){
        var supplier = supplierRepository.findById(id);
        return supplier.map(supplierMapper::toResponse);
    }

    @Override
    public List<SupplierResponse> findAll() {
        List<SupplierResponse> items = new ArrayList<>();
        var suppliers = supplierRepository.findAll();
        suppliers.forEach((item) -> items.add(supplierMapper.toResponse(item)));
        return items;
    }

    @Override
    @Transactional
    public void delete(final Long id){
        supplierRepository.deleteById(id);
    }
}
