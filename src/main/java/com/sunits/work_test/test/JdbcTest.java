package com.sunits.work_test.test;

import com.sunits.work_test.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author xieys
 * @description:
 * @create 2021/6/4 11:09
 */
public class JdbcTest {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt  = null;
        ResultSet rs = null;

        try {
            //1、获取连接
            conn = JDBCUtils.getConnection();
            //2、编写 sql
            String sql = "select * from emp where id = 1";
            //3、获取执行语句对象
            pstmt = conn.prepareStatement(sql);
            //4、设置参数
//            pstmt.setInt(1, 2);
            //5、查询
            rs = pstmt.executeQuery();
            //6、处理资源
            while(rs.next()){
                System.out.println(rs.getString(2)+"----"+ rs.getString("name"));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }finally{
            //7、释放资源
            JDBCUtils.release(conn, pstmt, rs);
        }
    }
}
