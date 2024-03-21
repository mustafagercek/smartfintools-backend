package de.nicetoapp.smartfintools.domain.entity.stock

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
data class Stock(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "ticker_symbol", nullable = false, unique = true)
    val tickerSymbol: String,

    @Column(name = "company_name", nullable = false)
    val companyName: String,

    @Column(name = "current_price")
    val currentPrice: BigDecimal? = null,

    @Column(name = "market_cap")
    val marketCap: BigDecimal? = null,

    @Column(name = "sector")
    val sector: String? = null
)
