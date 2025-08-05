package com.revature.Dao;
import java.sql.*;

public class PriceAnalyticsDaoImpl implements PriceAnalyticsDao {

    private final Connection conn;

    public PriceAnalyticsDaoImpl(Connection connection) {
        this.conn = connection;
    }

    @Override
    public void calculateDailyVolatility(String symbol) throws SQLException {

        String query =
                    "SELECT symbol, trade_date,ROUND((high_price - low_price) / open_price * 100, 2) AS volatility_percent " +
                    "FROM daily_prices " +
                    "WHERE symbol = ? " +
                    "ORDER BY trade_date;";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, symbol);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("=== Daily Volatility for " + symbol + " ===");
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
    public void calculateDailyPriceChanges(String symbol) throws SQLException {
        String query =
                    "SELECT trade_date, ROUND(close_price - open_price, 2) AS price_change " +
                    "FROM daily_prices " +
                    "WHERE symbol = ? " +
                    "ORDER BY trade_date;"
                ;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, symbol);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("=== Daily Price Changes for " + symbol + " ===");
                ResultSetMetaData meta = rs.getMetaData();
                int colCount = meta.getColumnCount();
                boolean hasResults = false;

                while (rs.next()) {
                    hasResults = true;
                    for (int i = 1; i <= colCount; i++) {
                        System.out.print(meta.getColumnName(i) + ": " + rs.getString(i) + "\t");
                    }
                    System.out.println();
                }

                if (!hasResults) {
                    System.out.println("No data found for symbol: " + symbol);
                }
            }
        }
    }


    @Override
    public void identifyPriceGaps(String symbol) throws SQLException {
        String query =
                    "SELECT dp1.trade_date, dp1.open_price - dp0.close_price AS gap " +
                                "FROM daily_prices dp1 " +
                                "JOIN daily_prices dp0 ON dp1.trade_date = DATE_ADD(dp0.trade_date, INTERVAL 1 DAY) " +
                                "WHERE dp1.symbol = ? AND dp0.symbol = dp1.symbol " +
                                "ORDER BY dp1.trade_date;";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, symbol);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("Price Gaps Between Sessions for " + symbol);
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
    public void generateMovingAverages(String symbol, int period) throws SQLException {
        String query = String.format("""
                    SELECT trade_date,
                           ROUND(AVG(close_price) OVER (
                               PARTITION BY symbol
                               ORDER BY trade_date
                               ROWS BETWEEN %d PRECEDING AND CURRENT ROW
                           ), 2)
                            AS moving_avg_%d
                            FROM daily_prices
                            WHERE symbol = ?
                            ORDER BY trade_date;
                """, period - 1, period);

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, symbol);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("=== " + period + "-Day Moving Average for " + symbol + " ===");
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
    public boolean symbolExists(String symbol) throws SQLException {
        String query = "SELECT COUNT(*) FROM stocks WHERE symbol = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, symbol);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}





