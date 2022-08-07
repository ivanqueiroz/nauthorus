package dev.ivanqueiroz.nauthorus.infrastructure.adapters.config;

import dev.ivanqueiroz.nauthorus.application.ports.output.LoadRatesOutputPort;
import dev.ivanqueiroz.nauthorus.application.ports.output.TransactionOutputPort;
import dev.ivanqueiroz.nauthorus.domain.service.TransactionService;
import dev.ivanqueiroz.nauthorus.infrastructure.adapters.output.persistence.TransactionPersistenceAdapter;
import dev.ivanqueiroz.nauthorus.infrastructure.adapters.output.persistence.mapper.TransactionPersistenceMapper;
import dev.ivanqueiroz.nauthorus.infrastructure.adapters.output.persistence.repository.TransactionRepository;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;

@Factory
public class BeanFactory {

    @Bean
    @Singleton
    public TransactionPersistenceAdapter transactionPersistenceAdapter(TransactionRepository transactionRepository,
                                                                       TransactionPersistenceMapper transactionPersistenceMapper) {
        return new TransactionPersistenceAdapter(transactionRepository, transactionPersistenceMapper);
    }

    @Bean
    @Singleton
    public TransactionService transactionService(TransactionOutputPort transactionOutputPort,
                                                 LoadRatesOutputPort loadRatesOutputPort) {
        return new TransactionService(transactionOutputPort, loadRatesOutputPort);
    }
}
