package dev.ivanqueiroz.nauthorus.infrastructure.adapters.output.persistence.repository;

import dev.ivanqueiroz.nauthorus.infrastructure.adapters.output.persistence.entity.TransactionEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findByUserId(Long userId);

}
