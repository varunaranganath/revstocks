package com.revature.Dao;

import java.sql.SQLException;

public interface VolumeAnalyticsDao {
    void calculateVWAP(String symbol) throws SQLException;


    void calculateDailyTurnover(String symbol) throws SQLException;

    void analyzeVolumeTrends(String symbol) throws SQLException;
    void calculateDeliverableRatio(String symbol) throws SQLException;

}
