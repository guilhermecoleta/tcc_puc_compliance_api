package puc.tcc.compliance.api.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import puc.tcc.compliance.api.persistence.domain.DocumentEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {

    List<DocumentEntity> findByCurrentTrue();

    List<DocumentEntity> findByNumberOrderByVersionDesc(@Param("number") String number);

    Optional<DocumentEntity> findFirstByNumberOrderByVersionDesc(@Param("number") String number);

}
