package puc.tcc.logistics.persistence.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import puc.tcc.logistics.persistence.domain.ProductEntity;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("FROM ProductEntity p")
    Page<ProductEntity> search(Pageable pageable);
}
