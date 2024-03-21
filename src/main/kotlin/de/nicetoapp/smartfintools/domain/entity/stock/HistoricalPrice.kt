package de.nicetoapp.smartfintools.domain.entity.stock

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "historical_prices")
data class HistoricalPrice(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id", nullable = false)
    val stock: Stock,

    @Column(name = "price_date", nullable = false)
    val priceDate: LocalDate,

    @Column(name = "closing_price", nullable = false)
    val closingPrice: BigDecimal
)
