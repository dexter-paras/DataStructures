/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.companies.arcesium.stockHolding;

//import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author paras.chawla
 * @version $Id: Result.java, v 0.1 2019-09-15 17:24 paras.chawla Exp $$
 */
public class Result {

    /**
     * Calculate Holding Value datewise
     * @param holdingDetails
     * @param holdingPriceDetails
     * @param date
     * @return
     */
    public static double calculateHoldingValue(HoldingDetails holdingDetails, HoldingPriceDetails holdingPriceDetails, String date) {
        List<Holding> holdingDateWise = filterHoldingByDate(holdingDetails, date);
        List<HoldingPrice> holdingPriceWise = filterHoldingPriceByDate(holdingPriceDetails, date);
        double holdingValue = 0;
        for (int i = 0; i < holdingPriceWise.size(); i++) {
            HoldingPrice holdingPrice = holdingPriceWise.get(i);
            for (int j = 0; j < holdingDateWise.size(); j++) {
                Holding holding = holdingDateWise.get(j);
                if (holding.getSecurity().equals(holdingPrice.getSecurity())) {
                    holdingValue += holding.getQuantity() * holdingPrice.getPrice();
                }
            }
        }
        return holdingValue;
    }

    /**
     * Filter Holding by Date
     * @param holdingDetails
     * @param date
     * @return
     */
    private static List<Holding> filterHoldingByDate(HoldingDetails holdingDetails, String date) {
        List<Holding> holdingDateWise = new ArrayList<>();
        List<Holding> listHolding = holdingDetails.getData();
        for (int i = 0; i < listHolding.size(); i++) {
            if (date.equals(listHolding.get(i).getDate())) {
                holdingDateWise.add(listHolding.get(i));
            }
        }
        return holdingDateWise;
    }

    /**
     * filter Holding Price by Date
     * @param holdingPriceDetails
     * @param date
     * @return
     */
    private static List<HoldingPrice> filterHoldingPriceByDate(HoldingPriceDetails holdingPriceDetails, String date) {
        List<HoldingPrice> holdingPriceWise = new ArrayList<>();
        List<HoldingPrice> listHoldingPrice = holdingPriceDetails.getData();
        for (int i = 0; i < listHoldingPrice.size(); i++) {
            if (date.equals(listHoldingPrice.get(i).getDate())) {
                holdingPriceWise.add(listHoldingPrice.get(i));
            }
        }
        return holdingPriceWise;
    }

    public static void main(String[] args) {
        Result result = new Result();
        HoldingDetails holdingDetails = result.fetchHoldingDetails();
        HoldingPriceDetails holdingPriceDetails = result.fetchHoldingPriceDetails();
        double holdingValue = calculateHoldingValue(holdingDetails, holdingPriceDetails, "20190506");
    }

    private HoldingDetails fetchHoldingDetails() {
        HoldingDetails holdingDetails = null;
        try {
            URL url = new URL("https://api.myjson.com/bins/1eleys");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            Scanner s = new Scanner(conn.getInputStream()).useDelimiter("\\A");
            String jsonString = s.hasNext() ? s.next() : "";

            //Gson g = new Gson();
            //holdingDetails = g.fromJson(jsonString, HoldingDetails.class);
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
        return holdingDetails;
    }

    /**
     * fetch Hodling Price details from given http url
     * @return
     */
    private HoldingPriceDetails fetchHoldingPriceDetails() {
        HoldingPriceDetails holdingPriceDetails = null;
        try {
            URL url = new URL("https://api.myjson.com/bins/vf9ac");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            Scanner s = new Scanner(conn.getInputStream()).useDelimiter("\\A");
            String jsonString = s.hasNext() ? s.next() : "";

            //Gson g = new Gson();
            //holdingPriceDetails = g.fromJson(jsonString, HoldingPriceDetails.class);
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
        return holdingPriceDetails;
    }
}