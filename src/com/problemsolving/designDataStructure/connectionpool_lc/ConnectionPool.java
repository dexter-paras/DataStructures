/*
*
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
package com.problemsolving.designDataStructure.connectionpool_lc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

*
 * @author paras.chawla
 * @version $Id: ConnectionPool.java, v 0.1 2021-02-06 8:11 AM paras.chawla Exp $$ https://leetcode
 * .com/discuss/general-discussion/1050178/design-a-thread-safe-connection-pool-java
 * <p>
 * public interface ConnectionPool { */
/** Returns a connection from this pool if it is available or throws SQLException when no connection
 * available
 * @return connection from this pool
 * @throws SQLException thrown when connection is not available
    Connection getConnection()throws SQLException,InterruptedException;

            *
             * Returns a connection from this pool if it is available
             * otherwise waits for no more than timeout milliseconds to get a connection
             * @param timeout timeout in milliseconds
             * @return connection from this pool is it becomes available within timeout milliseconds
             * @throws SQLException thrown when connection is not available
            Connection getConnection(long timeout)throws SQLException,InterruptedException;

            *
             * Returns connection to the pool
             * @param connection connection to be returned to the pool
            void releaseConnection(Connection connection);
            }
    Instance of the connection pool should accept integer variable maximumPoolSize and should not create more than maximumPoolSize
    connections to the database.
        Solution:

        First, let use define main instance variables and constructor:

public class ConnectionPoolImpl implements ConnectionPool {
    private Driver            driver;
    private String            jdbcUrl;
    private String            username;
    private String            password;
    private int               maximumPoolSize;
    private int               size;
    private Queue<Connection> connections;

    public ConnectionPoolImpl(String driverClassName, String jdbcUrl,
                              String username, String password, int maximumPoolSize)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c = Class.forName(driverClassName);
        this.driver = (Driver) c.newInstance();

        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        this.maximumPoolSize = maximumPoolSize;
        this.size = 0;
        this.connections = new LinkedList<>();
    }

    @Override
    public Connection getConnection() throws SQLException, InterruptedException {
        // todo: implement getConnection()
    }

    @Override
    public Connection getConnection(long timeout) throws SQLException, InterruptedException {
        // todo: implement getConnection(long timeout)
    }

    @Override
    public void releaseConnection(Connection connection) {
        // todo: implement releaseConnection(Connection connection)
    }
}
jdbcUrl,username and password are used to create a new connection.driverClassName will be used to instantiate an instance of Driver.size
and maximumPoolSize represent current number of connections created by pool and maximum number of connections correspondingly.
        We will use LinkedList to store the list of connections.

        From problem description we know that the pool should be thread safe.There are several options on how to achieve thread safety –
        one of them is to use lock or synchronized section to allow only one thread to modify list of connections at any point of time.

        Let us use synchronized section and this as monitor object.

        When releasing a connection,
        we need to add it to the queue and let threads that are in WAITING state know that a connection has been added to the queue:

@Override
public void releaseConnection(Connection connection){
synchronized (this){
        connections.offer(connection);
        this.notifyAll();
        }
        }
        Implementation of getConnection()method is also simple – it will just call overloaded method with timeout parameter set to 0:

@Override
public Connection getConnection()throws SQLException,InterruptedException{
        return getConnection(0);
        }
        Now let us implement getConnection(long timeout)method.There are 3cases:

        a connection is available in the pool.We can return this connection
        no connections are available in the pool,size<maximumPoolSize.We can create a new connection and return it to calling code
        no connections are available in the pool and size==maximumPoolSize.We need to wait until a connection becomes available.
        Let us implement cases 1and 3first.We define timestamp as the moment in the future when we stop waiting for connections to be
        added to the queue.In synchronized section we check if the queue is empty,and if it is we will wait either for timeout or for
        releaseConnection method to signal that there is a new connection in the queue:

@Override
public Connection getConnection(long timeout)throws SQLException,InterruptedException{
        long timestamp=System.currentTimeMillis()+timeout;

synchronized (this){
        while(connections.isEmpty()){
        this.wait(Math.max(timestamp-System.currentTimeMillis(),1));

        if(timestamp<=System.currentTimeMillis()){
        throw new SQLException("Connection not available");
        }
        }

        return connections.poll();
        }

        // todo: implement getConnection(long timeout)
        }
        If connections queue is not empty,
        the code will poll a connection and return it.Otherwise the thread will wait either for timeout or to be activated by
        releaseConnection.

        Now we can handle case 2 – when there are no connections in the pool,but we can create a new connection

@Override
public Connection getConnection(long timeout)throws SQLException,InterruptedException{
        long timestamp=System.currentTimeMillis()+timeout;

        boolean createNewConnection=false;

synchronized (this){
        while(connections.isEmpty()){
        if(size<maximumPoolSize){
        size++;
        createNewConnection=true;
        break;
        }else{
        this.wait(Math.max(timestamp-System.currentTimeMillis(),1));

        if(timestamp<=System.currentTimeMillis()){
        throw new SQLException("Connection not available");
        }
        }
        }

        if(!createNewConnection){
        return connections.poll();
        }
        }

        return createNewConnection();
        }
        Here if there are no connections in the pool,but maximumPoolSize is not reached,we increment size variable,
        set createNewConnection variable to true and create new connection outside of synchronized block.

        The last method to implement is createNewConnection:

private Connection createNewConnection()throws SQLException{
        try{
        java.util.Properties info=new java.util.Properties();
        info.put("user",username);
        info.put("password",password);

        return driver.connect(jdbcUrl,info);
        }catch(Throwable t){
synchronized (this){
        size--;
        this.notifyAll();
        }
        t.printStackTrace();
        throw new SQLException("Connection not available",t);
        }
        }
        The method uses the instance of driver

class to create a connection.DriverManager can be used instead,but its methods are synchronized,so it is preferable to use Driver

class to create connections.
        If the method fails to create a connection,size variable is decreased in synchronized section,and waiting threads are notified.

        Final code:
        ConnectionPool.java:

        import java.sql.Connection;
        import java.sql.SQLException;

public interface ConnectionPool {
    /**
     * Returns a connection from this pool if it is available
     * or throws SQLException when no connection available
     * @return connection from this pool
     * @throws SQLException thrown when connection is not available

    Connection getConnection() throws SQLException, InterruptedException;

    /**
     * Returns a connection from this pool if it is available
     * otherwise waits for no more than timeout milliseconds to get a connection
     * @param timeout timeout in milliseconds
     * @return connection from this pool is it becomes available within timeout milliseconds
     * @throws SQLException thrown when connection is not available

    Connection getConnection(long timeout) throws SQLException, InterruptedException;

    /**
     * Returns connection to the pool
     * @param connection connection to be returned to the pool

    void releaseConnection(Connection connection);
}
ConnectionPoolImpl.java:

        import java.sql.Connection;
        import java.sql.Driver;
        import java.sql.SQLException;
        import java.util.LinkedList;
        import java.util.Queue;

public class ConnectionPoolImpl implements ConnectionPool {
    private Driver            driver;
    private String            jdbcUrl;
    private String            username;
    private String            password;
    private int               maximumPoolSize;
    private int               size;
    private Queue<Connection> connections;

    public ConnectionPoolImpl(String driverClassName, String jdbcUrl,
                              String username, String password, int maximumPoolSize)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c = Class.forName(driverClassName);
        this.driver = (Driver) c.newInstance();

        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        this.maximumPoolSize = maximumPoolSize;
        this.size = 0;
        this.connections = new LinkedList<>();
    }

    @Override
    public Connection getConnection() throws SQLException, InterruptedException {
        return getConnection(0);
    }

    private Connection createNewConnection() throws SQLException {
        try {
            java.util.Properties info = new java.util.Properties();
            info.put("user", username);
            info.put("password", password);

            return driver.connect(jdbcUrl, info);
        } catch (Throwable t) {
            synchronized (this) {
                size--;
                this.notifyAll();
            }
            t.printStackTrace();
            throw new SQLException("Connection not available", t);
        }
    }

    @Override
    public Connection getConnection(long timeout) throws SQLException, InterruptedException {
        long timestamp = System.currentTimeMillis() + timeout;

        boolean createNewConnection = false;

        synchronized (this) {
            while (connections.isEmpty()) {
                if (size < maximumPoolSize) {
                    size++;
                    createNewConnection = true;
                    break;
                } else {
                    this.wait(Math.max(timestamp - System.currentTimeMillis(), 1));

                    if (timestamp <= System.currentTimeMillis()) {
                        throw new SQLException("Connection not available");
                    }
                }
            }

            if (!createNewConnection) {
                return connections.poll();
            }
        }

        return createNewConnection();
    }

    @Override
    public void releaseConnection(Connection connection) {
        synchronized (this) {
            connections.offer(connection);
            this.notifyAll();
        }
    }
}
*/
