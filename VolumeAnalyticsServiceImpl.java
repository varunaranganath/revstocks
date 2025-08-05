package services;

import com.revature.Dao.VolumeAnalyticsDao;
import java.sql.SQLException;

public class VolumeAnalyticsServiceImpl implements VolumeAnalyticsService {

    private final VolumeAnalyticsDao volumeDao;

    public VolumeAnalyticsServiceImpl(VolumeAnalyticsDao volumeDao) {
        this.volumeDao = volumeDao;
    }



    @Override
    public void calculateVWAP(String symbol) throws SQLException {
        volumeDao.calculateVWAP(symbol);
    }

    @Override
    public void calculateDailyTurnover(String symbol) throws SQLException {
        volumeDao.calculateDailyTurnover(symbol);
    }

    @Override
    public void analyzeVolumeTrends(String symbol) throws SQLException {
        volumeDao.analyzeVolumeTrends(symbol);
    }

    @Override
    public void calculateDeliverableRatio(String symbol) throws SQLException {
        volumeDao.calculateDeliverableRatio(symbol);
    }
}