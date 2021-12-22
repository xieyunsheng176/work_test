package com.sunits.work_test.utils;


import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;


/**
 * 工具类
 * 提供获取连接及释放资源的方法
 * @author lenovo
 *
 */


public class JDBCUtil {

    /**
     * 获取连接方法
     * @return
     */

    public static Connection getConnection(){

        Connection conn = null;

        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");



            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:3306;DatabaseName=emp","root","root");



        } catch (Exception e) {



            e.printStackTrace();

        }





        return conn;

    }

    /**
     * 释放资源方法
     * @param conn
     * @param pstmt
     * @param rs
     */

    public static void release(Connection conn, PreparedStatement pstmt, ResultSet rs){

        if(rs != null){

            try {

                rs.close();

            } catch (SQLException e) {



                e.printStackTrace();

            }

        }

        if(pstmt != null){

            try {

                pstmt.close();

            } catch (SQLException e) {



                e.printStackTrace();

            }

        }

        if(conn != null){

            try {

                conn.close();

            } catch (SQLException e) {



                e.printStackTrace();

            }

        }

    }


}

