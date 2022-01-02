package com.sunits.work_test.test;

import com.sunits.work_test.annotation.Info;
import com.sunits.work_test.entity.Person;

import java.io.File;

public class AnnotationTest {
    public static void main1(String[] args) {
        try {
            //获取Person的Class对象
            Person person = Person.builder().build();
            Class clazz = person.getClass();
            //判断person对象上是否有Info注解
            if (clazz.isAnnotationPresent(Info.class)) {
                System.out.println("Person类上配置了Info注解！");
                //获取该对象上Info类型的注解
                Info infoAnno = (Info) clazz.getAnnotation(Info.class);
                System.out.println("person.name :" + infoAnno.value() + ",person.isDelete:" + infoAnno.isDelete());
            } else {
                System.out.println("Person类上没有配置Info注解！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过文件路径直接修改文件名
     *
     * @param filePath    需要修改的文件的完整路径
     * @param newFileName 需要修改的文件的名称
     * @return
     */
    private static String FixFileName(String filePath, String newFileName) {
        File f = new File(filePath);
        if (!f.exists()) { // 判断原文件是否存在（防止文件名冲突）
            return null;
        }
        newFileName = newFileName.trim();
        if ("".equals(newFileName) || newFileName == null) // 文件名不能为空
            return null;
        String newFilePath = null;
        if (f.isDirectory()) { // 判断是否为文件夹
            newFilePath = filePath.substring(0, filePath.lastIndexOf("/")) + "/" + newFileName;
        } else {
            newFilePath = filePath.substring(0, filePath.lastIndexOf("/")) + "/" + newFileName;
        }
        File nf = new File(newFilePath);
        try {
            f.renameTo(nf); // 修改文件名
        } catch (Exception err) {
            err.printStackTrace();
            return null;
        }
        return newFilePath;
    }

    public static void main(String[] args) {
        /*String filePath = "F:\\2021QGLOUFENG-R-TW\\2021年全国楼凤信息合集（附带联系方式）R\\安徽省";
        File file = new File(filePath);
        File[] fileArray= file.listFiles();
        File fileName = null;
        for(int i =0;i<fileArray.length;i++){
            fileName = fileArray[i];
            System.out.println(fileName.getName().substring(0,3)+".rar");;
            FixFileName(filePath+"/"+fileName.getName(),fileName.getName().substring(0,3)+".rar");
        }*/
        if (1==2){
            System.out.println("52552");
        }{
            System.out.println("aaa");
        }
    }
}