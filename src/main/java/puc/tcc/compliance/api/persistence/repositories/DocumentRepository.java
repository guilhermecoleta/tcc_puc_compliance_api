package puc.tcc.compliance.api.persistence.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import puc.tcc.compliance.api.persistence.domain.DocumentEntity;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {

    @Query("FROM DocumentEntity d")
    Page<DocumentEntity> search(Pageable pageable);
}
