package io.github.amine2k0.portfolio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

/**
 * A stock position in the portfolio. Stores only user-entered facts;
 * derived values (invested, market value, P&L, weight) are computed in the service layer.
 * All amounts are in MAD (single-currency portfolio for now).
 */
@Entity
@Table(name = "holdings")
public class Holding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String symbol;

    @Column(nullable = false, precision = 19, scale = 6)
    private BigDecimal quantity;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal averageBuyPrice;

    /** Required by JPA; not for application code. */
    protected Holding() {
    }

    public Holding(String symbol, BigDecimal quantity, BigDecimal averageBuyPrice) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.averageBuyPrice = averageBuyPrice;
    }

    public Long getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getAverageBuyPrice() {
        return averageBuyPrice;
    }

    public void updatePosition(BigDecimal quantity, BigDecimal averageBuyPrice) {
        this.quantity = quantity;
        this.averageBuyPrice = averageBuyPrice;
    }
}
