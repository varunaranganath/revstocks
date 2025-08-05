package com.revature.Dao;
import java.sql.SQLException;

public interface PriceAnalyticsDao {
    void calculateDailyVolatility(String symbol) throws SQLException;
    void calculateDailyPriceChanges(String symbol) throws SQLException;
    void identifyPriceGaps(String symbol) throws SQLException;
    void generateMovingAverages(String symbol,int period) throws SQLException;
    boolean symbolExists(String symbol) throws SQLException;
}
