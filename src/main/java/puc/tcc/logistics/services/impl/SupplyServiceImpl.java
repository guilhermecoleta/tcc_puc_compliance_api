package puc.tcc.logistics.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import puc.tcc.logistics.mapper.SupplyMapper;
import puc.tcc.logistics.persistence.repositories.SupplyRepository;
import puc.tcc.logistics.resources.supply.SupplyRequest;
import puc.tcc.logistics.resources.supply.SupplyResponse;
import puc.tcc.logistics.services.SupplyService;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class SupplyServiceImpl implements SupplyService {

    @Autowired
    private final SupplyMapper supplyMapper;

    @Autowired
    private SupplyRepository supplyRepository;

    @Override
    @Transactional
    public SupplyResponse create(final SupplyRequest supplyRequest) {
        var model = supplyMapper.toModel(supplyRequest);
        model = supplyRepository.save(model);
        return supplyMapper.toResponse(model);
    }

    @Override
    public Optional<SupplyResponse> findById(final Long id){
        var supply = supplyRepository.findById(id);
        return supply.map(supplyMapper::toResponse);
    }

    @Override
    public List<SupplyResponse> findAll() {
        List<SupplyResponse> items = new ArrayList<>();
        var supplies = supplyRepository.findAll();
        supplies.forEach((item) -> items.add(supplyMapper.toResponse(item)));
        return items;
    }
}
