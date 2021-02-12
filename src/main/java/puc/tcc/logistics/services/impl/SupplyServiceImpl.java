package puc.tcc.logistics.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import puc.tcc.logistics.mapper.SupplyMapper;
import puc.tcc.logistics.persistence.domain.SupplyEntity;
import puc.tcc.logistics.persistence.repositories.SupplyRepository;
import puc.tcc.logistics.resources.supply.SupplyRequest;
import puc.tcc.logistics.resources.supply.SupplyResponse;
import puc.tcc.logistics.services.SupplyService;

@Service
@RequiredArgsConstructor
public class SupplyServiceImpl implements SupplyService {

    @Autowired
    private final SupplyMapper supplyMapper;

    @Autowired
    private SupplyRepository supplyRepository;

    @Override
    public SupplyResponse create(SupplyRequest supplyRequest) {
        var model = supplyMapper.toModel(supplyRequest);
        return supplyMapper.toResponse(model);
    }
}
