/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.designDataStructure.connection_pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author paras.chawla
 * @version $Id: ConnectionPool.java, v 0.1 2020-12-14 14:33 paras.chawla Exp $$
 * <p>
 * Step 1- Create HikariConfig giving properties in hibernate.xml
 * Step 2 - Create HikariDataSource from HikariConfig
 * Step 3 -HikariDataSource consist of HikariPool
 *
 * https://codereview.stackexchange.com/questions/40005/connection-pool-implementation?rq=1
 * https://github.com/sharmaak/crashlabs/blob/master/MinimalConnectionPool/com/amitcodes/dbcp/ConnectionPool.java
 */
public class ConnectionPool {

    // Thread-safe Queue with initial Pool size already cached during load-on-startup
    private BlockingQueue<Connection> pool;

    // Maximum connetions which can be created and cached in queue if required
    private int maxPoolSize;

    // Actual Initial Pool size given by client to create and cached in BlockingQueue
    private int initialPoolSize;

    // Number of Connections Generated so far
    private int currentPoolSize;

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    // 1. Load Driver class
    // 2. Create Connection up to initialPoolSize and add into pool
    public ConnectionPool(int maxPoolSize, int initialPoolSize, String url, String userName,
                          String password, String driverClassName) throws ClassNotFoundException, SQLException {

        if ((initialPoolSize > maxPoolSize) || initialPoolSize < 1 || maxPoolSize < 1) {
            throw new IllegalArgumentException("Invalid pool size parameters");
        }

        // default max Pool size is 10
        this.maxPoolSize = maxPoolSize > 0 ? maxPoolSize : 10;
        this.initialPoolSize = initialPoolSize;
        this.dbUrl = url;
        this.dbPassword = password;
        this.dbUser = userName;

        // Can have max connections upto MaxPoolSize
        this.pool = new LinkedBlockingQueue<Connection>(maxPoolSize);

        initPooledConnections(driverClassName);
    }

    private void initPooledConnections(String driverClassName) throws ClassNotFoundException, SQLException {

        // 1. Attempt to load driver class
        Class.forName(driverClassName);

        // 2. create connections and add into pool
        for (int i = 0; i < initialPoolSize; i++) {
            openAndPoolConnection();
        }
    }

    // Thread-safe Connection creation and adding into Pool
    private synchronized void openAndPoolConnection() throws SQLException {

        // base check
        if (currentPoolSize == maxPoolSize) {
            return;
        }

        Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        pool.offer(connection);
        currentPoolSize++;
    }

    private Connection borrowConnection() throws SQLException, InterruptedException {

        // if there is scope of new connection creation is still possible
        if (pool.peek() == null && currentPoolSize < maxPoolSize) {
            openAndPoolConnection();
        }

        return pool.take();
    }

    private Connection borrowConnection(long timeoutInMillis) throws SQLException, InterruptedException {

        Instant startInstant = Instant.now();

        do {
            // if there is scope of new connection creation is still possible
            if (pool.peek() == null && currentPoolSize < maxPoolSize) {
                openAndPoolConnection();
            }

            return pool.take();
        } while (Duration.between(startInstant, Instant.now()).toMillis() < timeoutInMillis);
    }

    public void surrenderConnection(Connection conn) {
        pool.offer(conn); // offer() as we do not want to go beyond capacity
    }

}