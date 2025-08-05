package services;


import java.sql.SQLException;

public interface PriceAnalyticsService {
    void calculateDailyVolatility(String symbol) throws SQLException;

    void calculateDailyPriceChanges(String symbol) throws SQLException;

    void identifyPriceGaps(String Symbol) throws SQLException;

    void generateMovingAverages(String symbol,int period) throws SQLException;

    boolean symbolExists(String symbol) throws SQLException;
}
