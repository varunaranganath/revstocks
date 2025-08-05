package services;

import com.revature.Dao.PriceAnalyticsDao;
import com.revature.Dao.PriceAnalyticsDaoImpl;

import java.sql.SQLException;

public class PriceAnalyticsServiceImpl implements PriceAnalyticsService {

    private final PriceAnalyticsDao priceDao;

    public PriceAnalyticsServiceImpl(PriceAnalyticsDaoImpl priceDao) {
        this.priceDao = priceDao;
    }


    @Override
    public void calculateDailyVolatility(String symbol) throws SQLException {
        priceDao.calculateDailyVolatility(symbol);
    }


    @Override
    public void calculateDailyPriceChanges( String symbol) throws SQLException {
        priceDao.calculateDailyPriceChanges(symbol);
    }

    @Override
    public void identifyPriceGaps(String symbol) throws SQLException {
        priceDao.identifyPriceGaps(symbol);
    }

    @Override
    public void generateMovingAverages(String symbol,int period) throws SQLException {
        priceDao.generateMovingAverages(symbol,period);
    }

    @Override
    public boolean symbolExists(String symbol) throws SQLException {
        return priceDao.symbolExists(symbol);
    }
}