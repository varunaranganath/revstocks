package main;

import com.revature.Dao.PriceAnalyticsDaoImpl;
import com.revature.Dao.VolumeAnalyticsDaoImpl;

import services.PriceAnalyticsService;
import services.PriceAnalyticsServiceImpl;
import services.VolumeAnalyticsService;
import services.VolumeAnalyticsServiceImpl;
import util.DbConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);





    public static void main(String[] args) {
            logger.info("Application started.");

            try (Scanner scanner = new Scanner(System.in);
                 Connection conn = DbConnector.getConnection()) {

                logger.info("Database connection established.");

                PriceAnalyticsService priceService = new PriceAnalyticsServiceImpl(new PriceAnalyticsDaoImpl(conn));
                VolumeAnalyticsService volumeService = new VolumeAnalyticsServiceImpl(new VolumeAnalyticsDaoImpl(conn));

                System.out.print("Enter company symbol (e.g., TCS, INFY): ");
                String companySymbol = scanner.nextLine();
                logger.info("User entered symbol");

                if (!priceService.symbolExists(companySymbol)) {
                    logger.warn("Symbol  not found in database.");
                    System.out.println("Symbol '" + companySymbol + "' not found in database.");
                    return;
                }

                System.out.println("\nChoose analysis type:");
                System.out.println("1.Price Analysis");
                System.out.println("2.Volume Analysis");
                System.out.println("3.Return");

                System.out.print("Enter your choice (1–3): ");
                int choice = scanner.nextInt();
                logger.info("User selected analysis type: " +choice);

                switch (choice) {
                    case 1 -> {
                        logger.info("Entering Price Analysis menu.");
                        System.out.println("""
                    === Price Analysis ===
                    a) Calculate Daily Volatility
                    b) Calculate Daily Price Changes
                    c) Identify Price Gaps
                    d) Generate Moving Averages
                    e) Back to Main Menu
                """);

                        System.out.print("Choose an option (a–e): ");
                        String subChoice = scanner.next().toLowerCase();
                        logger.info("User selected price analysis option:" + subChoice);

                        switch (subChoice) {
                            case "a" -> {
                                logger.info("Calculating daily volatility for " + companySymbol);
                                priceService.calculateDailyVolatility(companySymbol);
                            }
                            case "b" -> {
                                logger.info("Calculating daily price changes for " + companySymbol);
                                priceService.calculateDailyPriceChanges(companySymbol);
                            }
                            case "c" -> {
                                logger.info("Identifying price gaps for " + companySymbol);
                                priceService.identifyPriceGaps(companySymbol);
                            }
                            case "d" -> {
                                System.out.print("Enter moving average period : ");
                                int period = scanner.nextInt();
                                logger.info("Generating moving averages for with period" + companySymbol, +period);
                                priceService.generateMovingAverages(companySymbol, period);
                            }
                            case "e" -> logger.info("Returning to main menu from price analysis.");
                            default -> logger.warn("Invalid price analysis option:"+ subChoice);
                        }
                    }
                    case 2 -> {
                        logger.info("Entering Volume Analysis menu.");
                        System.out.println("""
                    === Volume Analysis ===
                    a) Volume Weighted Average Price
                    b) Daily Turnover
                    c) Analyze Volume Trends
                    d) Calculate Deliverable Ratio
                    e) Back to Main Menu
                """);

                        System.out.print("Choose an option (a–e): ");
                        String subChoice = scanner.next().toLowerCase();
                        logger.info("User selected volume analysis option:"+ subChoice);

                        switch (subChoice) {
                            case "a" -> {
                                logger.info("Calculating VWAP for " + companySymbol);
                                volumeService.calculateVWAP(companySymbol);
                            }
                            case "b" -> {
                                logger.info("Calculating daily turnover for " + companySymbol);
                                volumeService.calculateDailyTurnover(companySymbol);
                            }
                            case "c" -> {
                                logger.info("Analyzing volume trends for " + companySymbol);
                                volumeService.analyzeVolumeTrends(companySymbol);
                            }
                            case "d" -> {
                                logger.info("Calculating deliverable ratio for " + companySymbol);
                                volumeService.calculateDeliverableRatio(companySymbol);
                            }
                            case "e" -> logger.info("Returning to main menu from volume analysis.");
                            default -> logger.warn("Invalid volume analysis option:"+ subChoice);
                        }
                    }
                    default -> logger.info("User exited the application.");
                }

            } catch (SQLException e) {
                logger.error("Database connection failed:" + e.getMessage(), e);
                System.err.println("Database connection failed: " + e.getMessage());
            }

            logger.info("Application terminated.");
        }
    }

