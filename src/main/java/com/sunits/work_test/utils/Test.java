package com.sunits.work_test.utils;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    private static final String SQLSERVER_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    // private static final String POSTGRESQL_DRIVER="org.postgresql.Driver";
    private static final String SQLSERVER_URL = "jdbc:sqlserver://111.198.2.202:65200;DatabaseName=MetHydroYL";


    public static void main(String[] args) throws Exception {
        int i = 1;
        int g=++i;
        int h=i++;
        int w=++i;

        System.out.println(i);
        System.out.println(g);
        System.out.println(h);
        System.out.println(w);
        String[] strings = {"I", "love", "you", "too"};
        Integer reduce = Arrays.stream(strings).reduce(0, (sum, str) -> sum + str.length(), (a, b) -> a + b);
        System.out.println(reduce);
        Map<String, Integer> map = Arrays.stream(strings).collect(Collectors.toMap(Function.identity(), String::length)); // (3)
        System.out.println(map);
        // 求单词长度之和
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        Integer lengthSum = stream.reduce(0,// 初始值　// (1)
        (sum, str) -> sum+str.length(), // 累加器 // (2)
                (a, b) -> a+b);// 部分和拼接器，并行执行时才会用到 // (3)
// int lengthSum = stream.mapToInt(str -> str.length()).sum();
        System.out.println(lengthSum);
/*
        String[] strArray = {"I", "love", "you", "too"};
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        System.out.println(Arrays.stream(strArray).reduce((s1, s2) -> s1.length() >= s2.length() ? s1 : s2).get());
        Optional<String> longest = stream.reduce((s1, s2) -> s1.length()>=s2.length() ? s1 : s2);
        System.out.println(longest.get());
        System.out.println(stream.max((s1, s2) -> s1.length() - s2.length()).get());
*/


        /*//阶乘的递推公式为：factorial(n)=n*factorial(n-1)，其中n为非负整数,且0!=1,1!=1
        Integer jie = jie(9);
        System.out.println(jie);
        //菲波那切数列 :Fib(n)=Fib(n-1)+Fib(n-2)
        Integer fie = fei(6);//递归
        System.out.println(fie);
        long feixun = feixun(6);
        System.out.println(feixun);

        Integer[] a ={1,3,23,432,2,3};
        Arrays.parallelSort(a);
        System.out.println(Arrays.toString(a));
        Integer[] v = {4,5,6};
        Integer[] f = new Integer[a.length+v.length];
        System.arraycopy(a, 0, f, 0, a.length);
        System.arraycopy(v, 0, f, a.length, v.length);
        Integer[] integers = ArrayUtils.addAll(a, v);

        String str = "wweqeqwe";

        for (int i = 0; i < a.length-1; i++) {
            int temp ;
            for (int j = i+1; j < a.length; j++) {
                if (a[i]<a[j]){
                    temp = a[j];
                    a[j] = a[i] ;
                    a[i] = temp;
                }
            }
        }
        int weq = str.compareTo("weq");
        System.out.println(weq);
       */
        /*try {
            Class.forName(SQLSERVER_DRIVER).newInstance();
            String url = SQLSERVER_URL;
            Connection con = DriverManager.getConnection(url,"ZJY","shuili0326");
            Statement st = con.createStatement();
            String sql = " select * from zkzs.table_1";
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public static Integer jie(Integer n) throws Exception {
        if (n<0){
            throw new Exception("参数不能位负数");
        }else if (n==1 || n== 0){
            return n;
        }else {
            return n*jie(n-1);
        }

    }

    public static Integer fei(Integer n) throws Exception {
        if (n<0){
            throw new Exception("参数不能为负");
        }else if (n ==1 || n==0){
            return n;
        }else{
            return fei(n-1)+ fei(n-2);
        }
    }
    public static long feixun(Integer n) throws Exception {
        long[] result = {0,1};
        if (n<2){
            return result[(int)n];
        }
        long fabzero = 0;
        long fabone = 1;
        long fabN = 0;
        for (int i=2;i<=n;i++){
            fabN = fabone+fabzero;
            fabzero = fabone;
            fabone = fabN;
        }
        return fabN;
    }

}