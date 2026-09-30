package io.github.amine2k0.portfolio.dto;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * A holding as returned by the API, including values derived in the service layer.
 * All amounts are in MAD.
 * <p>
 * When no price has been stored for the symbol yet, {@code currentPrice}, {@code priceUpdatedAt},
 * {@code actualValue}, {@code profitLoss}, {@code profitLossPercent} and {@code weight} are
 * {@code null}: the value is unknown, and returning 0 would falsely report a total loss.
 */
public record HoldingResponse(
        Long id,
        String symbol,
        BigDecimal quantity,
        BigDecimal averageBuyPrice,
        BigDecimal invested,
        BigDecimal currentPrice,
        Instant priceUpdatedAt,
        BigDecimal actualValue,
        BigDecimal profitLoss,
        BigDecimal profitLossPercent,
        BigDecimal weight
) {
}
