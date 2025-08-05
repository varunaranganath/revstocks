package model;
import java.time.LocalDate;

public class DailyMarketData {

    private String symbol;
    private LocalDate tradeDate;

    // Price Data
    private double openPrice;
    private double highPrice;
    private double lowPrice;
    private double closePrice;

    // Volume Data
    private long volume;
    private Long deliverableVolume;

    // Calculated Fields
    private Double volatilityPercent;
    private Double priceChangePercent;
    private Double priceGap;
    private Double movingAverage;
    private Double VWAP;
    private Double turnover;
    private Double volumeMA7Day;
    private Double deliverablePercent;

    // Getters and Setters
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public LocalDate getTradeDate() {
        return tradeDate;
    }
    public void setTradeDate(LocalDate tradeDate) {
        this.tradeDate = tradeDate;
    }

    public double getOpenPrice() {
        return openPrice;
    }
    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }
    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }
    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }
    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public long getVolume() {
        return volume;
    }
    public void setVolume(long volume) {
        this.volume = volume;
    }

    public Long getDeliverableVolume() {
        return deliverableVolume;
    }
    public void setDeliverableVolume(Long deliverableVolume) {
        this.deliverableVolume = deliverableVolume;
    }

    public Double getVolatilityPercent() {
        return volatilityPercent;
    }
    public void setVolatilityPercent(Double volatilityPercent) {
        this.volatilityPercent = volatilityPercent;
    }

    public Double getPriceChangePercent() {
        return priceChangePercent;
    }
    public void setPriceChangePercent(Double priceChangePercent) {
        this.priceChangePercent = priceChangePercent;
    }

    public Double getPriceGap() {
        return priceGap;
    }
    public void setPriceGap(Double priceGap) {
        this.priceGap = priceGap;
    }

    public Double getMovingAverage() {
        return movingAverage;
    }
    public void setMovingAverage(Double movingAverage) {
        this.movingAverage = movingAverage;
    }

    public Double getVWAP() {
        return VWAP;
    }
    public void setVWAP(Double VWAP) {
        this.VWAP = VWAP;
    }

    public Double getTurnover() {
        return turnover;
    }
    public void setTurnover(Double turnover) {
        this.turnover = turnover;
    }

    public Double getVolumeMA7Day() {
        return volumeMA7Day;
    }
    public void setVolumeMA7Day(Double volumeMA7Day) {
        this.volumeMA7Day = volumeMA7Day;
    }

    public Double getDeliverablePercent() {
        return deliverablePercent;
    }
    public void setDeliverablePercent(Double deliverablePercent) {
        this.deliverablePercent = deliverablePercent;
    }
}