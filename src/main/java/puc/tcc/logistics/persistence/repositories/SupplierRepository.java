package puc.tcc.logistics.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import puc.tcc.logistics.persistence.domain.SupplierEntity;

@Repository
public interface SupplierRepository extends CrudRepository<SupplierEntity, Long> {
}
