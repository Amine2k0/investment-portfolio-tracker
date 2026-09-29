package io.github.amine2k0.portfolio.repository;

import io.github.amine2k0.portfolio.entity.Holding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HoldingRepository extends JpaRepository<Holding, Long> {

    Optional<Holding> findBySymbol(String symbol);

    boolean existsBySymbol(String symbol);
}
