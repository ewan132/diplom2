package ru.netology.data;

import lombok.SneakyThrows;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper{
    private static final QueryRunner QUERY_RUNNER = new QueryRunner();
    private static final String url = System.getProperty("db.url");
    private static final String user = System.getProperty("db.user");
    private static final String password = System.getProperty("db.password");

    private static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,user,password);
    }

    @SneakyThrows
    public static  int getBuyStatus(){
        var sqlCode = "SELECT status from payment_entity pe order by created desc limit 1";
        var conn = getConnection();
        String s = QUERY_RUNNER.query(conn,sqlCode, new ScalarHandler<String>());
        int count = 0;
        if (s.equals("APPROVED")){
            count+=1;
        }
        return count;
    }

    @SneakyThrows
    public static int getCreditStatus(){
        var sqlCode = "SELECT status from credit_request_entity cre order by created desc limit 1";
        var conn = getConnection();
        String s = QUERY_RUNNER.query(conn, sqlCode, new ScalarHandler<String>());
        int count = 0;
        if (s.equals("APPROVED")){
            count+=1;
        }
        return count;
    }

    @SneakyThrows
    public static void cleanTable(){
        var conn = getConnection();
        QUERY_RUNNER.execute(conn, "DELETE FROM credit_request_entity ");
        QUERY_RUNNER.execute(conn, "DELETE FROM payment_entity");
        QUERY_RUNNER.execute(conn, "DELETE FROM order_entity");
    }

    @SneakyThrows
    public static Integer countPay(){
        var conn = getConnection();
        var sqlCode = "SELECT COUNT(*) FROM payment_entity pe";
        //QUERY_RUNNER.execute(conn, "SELECT COUNT(*) FROM payment_entity pe");
        return QUERY_RUNNER.query(conn, sqlCode, new ScalarHandler<Integer>());

    }


}



