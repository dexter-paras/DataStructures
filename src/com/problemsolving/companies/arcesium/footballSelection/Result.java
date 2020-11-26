/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.companies.arcesium.footballSelection;

/**
 * @author paras.chawla
 * @version $Id: Result.java, v 0.1 2020-05-10 13:49 paras.chawla Exp $$
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'getSelectionStatus' function below.
     *
     * The function is expected to return a 2D_STRING_ARRAY.
     * The function accepts 2D_STRING_ARRAY applications as parameter.
     */

    public static List<List<String>> getSelectionStatus(List<List<String>> applications) {

        List<Footballer> eligibleStriker = new ArrayList<>();
        List<Footballer> eligibleDefender = new ArrayList<>();
        List<Footballer> footballer = new ArrayList<>();

        for (List<String> list : applications) {
            Footballer player = new Footballer(list.get(0), Double.valueOf(list.get(1)),
                    Double.valueOf(list.get(2)), Integer.valueOf(list.get(3)), Integer.valueOf(list.get(4)));

            if (meetCriteria(player)) {
                if (player.isStriker) {
                    eligibleStriker.add(player);
                } else if (player.isDefender) {
                    eligibleDefender.add(player);
                }
            }
            footballer.add(player);
        }

        // Sort Strikers based on there scores
        eligibleStriker.sort(Comparator.comparingInt(Footballer::getScores).reversed());
        //Collections.sort(eligibleStriker, Comparator.comparingInt(Footballer::getScores).reversed());

        // Sort Defenders based on there defending capabilities
        eligibleDefender.sort(Comparator.comparingInt(Footballer::getDefends).reversed());
        //Collections.sort(eligibleDefender, Comparator.comparingInt(Footballer::getDefends).reversed());

        if (eligibleStriker.size() > eligibleDefender.size()) {
            // remove extra Strikers
            while (eligibleStriker.size() != eligibleDefender.size()) {
                eligibleStriker.get(eligibleStriker.size() - 1).setStriker(false);
                eligibleStriker.remove(eligibleStriker.size() - 1);
            }
        }

        if (eligibleStriker.size() < eligibleDefender.size()) {
            // remove extra Defenders
            while (eligibleDefender.size() != eligibleStriker.size()) {
                eligibleDefender.get(eligibleDefender.size() - 1).setDefender(false);
                eligibleDefender.remove(eligibleDefender.size() - 1);
            }
        }

        // Sort all footballers on basis of name

        Collections.sort(footballer, Comparator.comparing(Footballer::getName));

        List<String> result;
        List<List<String>> finalList = new ArrayList<>();
        for (Footballer player : footballer) {
            result = new ArrayList<>();
            result.add(player.name);
            if (player.isStriker) {
                result.add("SELECT");
                result.add("STRIKER");
            } else if (player.isDefender) {
                result.add("SELECT");
                result.add("DEFENDER");
            } else {
                result.add("REJECT");
                result.add("NA");
            }
            finalList.add(result);
        }
        return finalList;
    }

    public static boolean meetCriteria(Footballer player) {
        FitnessFactor fitnessFactor = new FitnessFactor();
        StrikerExperienceFactor strikerExperienceFactor = new StrikerExperienceFactor();
        DefenderExperienceFactor defenderExperienceFactor = new DefenderExperienceFactor();
        boolean isFit = player.getHeight() >= fitnessFactor.getMinHeight()
                && player.getBmi() <= fitnessFactor.getMaxBmi();

        boolean isStriker = player.getScores() >= strikerExperienceFactor.getMinGoalsScorer();
        player.setStriker(isStriker);

        boolean isDefender = player.getDefends() >= defenderExperienceFactor.getMinGoalsDefended();
        player.setDefender(isDefender);

        boolean isAllRounder = isStriker && isDefender;
        player.setAllRounder(isAllRounder);

        if (isFit && (isStriker || isDefender)) {
            return true;
        }
        return false;
    }

}

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int applicationsRows = Integer.parseInt(bufferedReader.readLine().trim());
        int applicationsColumns = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> applications = new ArrayList<>();

        IntStream.range(0, applicationsRows).forEach(i -> {
            try {
                applications.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<List<String>> result = Result.getSelectionStatus(applications);

        /*result.stream()
                .map(
                        r -> r.stream()
                                .collect(joining(" "))
                )
                .map(r -> r + "\n")
                .collect(toList())
                .forEach(e -> {
                    try {
                        //bufferedWriter.write(e);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
*/
        bufferedReader.close();
        //bufferedWriter.close();
    }
}