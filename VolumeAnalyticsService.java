package services;

import java.sql.SQLException;

public interface VolumeAnalyticsService {


    void calculateVWAP(String symbol) throws SQLException;

    void calculateDailyTurnover(String symbol) throws SQLException;
    void analyzeVolumeTrends(String symbol) throws SQLException;
    void calculateDeliverableRatio(String symbol) throws SQLException;
}