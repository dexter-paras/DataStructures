/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.multithreading.part2_policehacker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author paras.chawla
 * @version $Id: PoliceHackerVaultCase.java, v 0.1 2020-08-21 00:30 paras.chawla Exp $$
 */

/*
 * 1. Vault as an object having password keeping secret information
 * 2. Hacker threads t1,t2,t3 guessing vault password using bruteforce and any thread guessing correct password breaks
 * 3. Police thread going to rescue by counting 10 sec, police going to come and countdown
 */
public class PoliceHackerVaultCase {

    static final int MAX_PASSWORD = 9999;

    // 1. Vault class having password keeping secret information
    private static class Vault {

        private int password;

        public Vault(int password) {
            this.password = password;
        }

        public boolean isCorrectPassword(int guess) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {

            }
            return this.password == guess;
        }

    }

    // 2. Abstract Hacker Thread access to vault object
    private static abstract class HackerThread extends Thread {

        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public void start() {
            System.out.println("Starting thread " + this.getName());
            super.start();
        }

    }

    // 3.1 Creating multiple hacker threads,one is Ascending Hacker Thread that has access to vault object
    private static class AscendingHackerThread extends HackerThread {

        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = 0; guess < MAX_PASSWORD; guess++) {
                if (vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + "hacked vault successfully by guessing password as " + guess);
                    System.exit(0);
                }
            }
        }

    }

    // 3.2 Creating multiple hacker threads, generating password in desc order
    private static class DescendingHackerThread extends HackerThread {

        public DescendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = MAX_PASSWORD; guess > 0; guess--) {
                if (vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " hacked vault successfully by guessing password as " + guess);
                    System.exit(0);
                }
            }
        }
    }

    //4. Police thread extends Thread directly, wait for 10 sec and catch hackers if they couln't hack
    private static class PoliceThread extends Thread {

        public PoliceThread() {
            this.setName(this.getClass().getSimpleName());
        }

        @Override
        public void run() {
            for (int i = 10; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                System.out.println(i);
            }
            System.out.println("Game over for you hackers");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Random random = new Random();

        // Generating random password and giving it to vault
        int randomPassword = random.nextInt(MAX_PASSWORD);
        System.out.println("Random password of vault is " + randomPassword);
        Vault vault = new Vault(randomPassword);

        List<Thread> list = new ArrayList<>();
        list.add(new AscendingHackerThread(vault));// Hackers trying to brute-force by trying password in asc order
        list.add(new DescendingHackerThread(vault));// Hackers trying to brute-force by trying password in desc order
        list.add(new PoliceThread());

        for (Thread thread : list) {
            thread.start();
        }
    }

}
