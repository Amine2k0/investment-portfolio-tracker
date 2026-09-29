package io.github.amine2k0.portfolio.repository;

import io.github.amine2k0.portfolio.entity.Holding;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class HoldingRepositoryTest {

    @Autowired
    private HoldingRepository repository;

    @Test
    void findBySymbolReturnsSavedHolding() {
        repository.saveAndFlush(new Holding("IAM", new BigDecimal("10"), new BigDecimal("98.50")));

        assertThat(repository.findBySymbol("IAM"))
                .hasValueSatisfying(h -> assertThat(h.getQuantity()).isEqualByComparingTo("10"));
        assertThat(repository.existsBySymbol("IAM")).isTrue();
        assertThat(repository.existsBySymbol("ATW")).isFalse();
    }

    @Test
    void savingDuplicateSymbolViolatesUniqueConstraint() {
        repository.saveAndFlush(new Holding("IAM", new BigDecimal("10"), new BigDecimal("98.50")));

        assertThatThrownBy(() ->
                repository.saveAndFlush(new Holding("IAM", new BigDecimal("5"), new BigDecimal("100.00"))))
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}
