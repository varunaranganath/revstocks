package com.revature.Dao;
import java.sql.*;
public class VolumeAnalyticsDaoImpl implements VolumeAnalyticsDao {
    private final Connection conn;
    public VolumeAnalyticsDaoImpl(Connection connection) {
        this.conn = connection;
    }
    @Override
    public void calculateVWAP(String symbol) throws SQLException {
        String query = """
                    SELECT trade_date,
                                       ROUND(SUM(close_price * volume) / SUM(volume), 2) AS vwap
                                FROM daily_prices
                                WHERE symbol = ?
                                GROUP BY trade_date
                                ORDER BY trade_date;
                """;
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, symbol);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("=== VWAP for " + symbol + " ===");
                ResultSetMetaData meta = rs.getMetaData();
                int colCount = meta.getColumnCount();
                while (rs.next()) {
                    for (int i = 1; i <= colCount; i++) {
                        System.out.print(meta.getColumnName(i) + ": " + rs.getString(i) + "\t");
                    }
                    System.out.println();
                }
            }
        }


    }
    @Override
    public void calculateDailyTurnover(String symbol) throws SQLException {
        String query = """
                   SELECT trade_date,ROUND(close_price * volume / 10000000, 2) AS turnover_crores
                    FROM daily_prices
                    WHERE symbol = ?
                    ORDER BY trade_date;
                
                """;
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, symbol);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("=== Daily Turnover for " + symbol + " ===");
                ResultSetMetaData meta = rs.getMetaData();
                int colCount = meta.getColumnCount();
                while (rs.next()) {
                    for (int i = 1; i <= colCount; i++) {
                        System.out.print(meta.getColumnName(i) + ": " + rs.getString(i) + "\t");
                    }
                    System.out.println();
                }
            }
        }

    }
    @Override
    public void analyzeVolumeTrends(String symbol) throws SQLException {
        String query = """
                     SELECT trade_date,
                                       volume,
                                       ROUND(AVG(volume) OVER (PARTITION BY symbol ORDER BY trade_date ROWS BETWEEN 4 PRECEDING AND CURRENT ROW), 2) AS avg_volume_5
                                FROM daily_prices
                                WHERE symbol = ?
                                ORDER BY trade_date;
                
                
               """;
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, symbol);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("=== Volume Trend Analysis for " + symbol + " ===");
                ResultSetMetaData meta = rs.getMetaData();
                int colCount = meta.getColumnCount();

                while (rs.next()) {
                    for (int i = 1; i <= colCount; i++) {
                        System.out.print(meta.getColumnName(i) + ": " + rs.getString(i) + "\t");
                    }
                    System.out.println();
                }
            }
        }
    }
    @Override
    public void calculateDeliverableRatio(String symbol) throws SQLException {
        String query = """
                    SELECT trade_date,
                                   ROUND(
                                       CASE WHEN volume > 0 THEN deliverable_volume / volume * 100 ELSE 0 END,
                                       2
                                   ) AS deliverable_ratio_percent
                            FROM daily_prices
                            WHERE symbol = ?
                            ORDER BY trade_date;
                
                """;
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, symbol);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("=== Deliverable Volume Ratio for " + symbol + " ===");
                System.out.printf( "Trade Date", "Deliverable Ratio (%)");
                System.out.println("---------------------------------------------");

                while (rs.next()) {
                    String date = rs.getString("trade_date");
                    String ratio = rs.getString("deliverable_ratio_percent");
                    System.out.printf("%-12s %-25s%n" ,"date", "ratio", date, ratio);

                }
                    System.out.println();
                }
            }
        }
    }


