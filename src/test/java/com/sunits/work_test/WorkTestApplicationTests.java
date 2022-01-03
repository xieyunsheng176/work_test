package com.sunits.work_test;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;

import com.sunits.work_test.entity.Dept;
import com.sunits.work_test.entity.Emp;
import com.sunits.work_test.service.EmpService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class WorkTestApplicationTests {

    @Autowired
    private EmpService empService;
    @Test
    void contextLoads() {
    }
    public static List<Emp> buildByDTO(Emp dto) { //这个dto是父流域
        List<Emp> empList = new ArrayList<>(); //代码是为了实现刚才那个单元格的样式，同一个区域编码的，直接给合并到一行，
        List<Dept> deptList = dto.getDept();//这个dept就是子流域
        Map<String,String>  map = new HashMap<>();//这个map我用来存储当子流域有重复编码的时候我给他拼接code用
        if (deptList != null) {//主要代码就是以下
            for (int i = 0; i <deptList.size() ; i++) {
                //遍历入参的子流域List
                //如果map存在则对他进行拼接，说明这个code重复了
                if (map.containsKey(deptList.get(i).getId())){
                    String id = deptList.get(i).getId();
                    List<Emp> collect = empList.stream().filter(f -> !f.getCode().equals(id)).collect(Collectors.toList());
                    Emp excel = new Emp();
                    //获取map对象里面的value，拼接进去
                    excel.setCode(map.get(deptList.get(i).getId())+","+ dto.getCode());
                    empList.add(excel);
                    //最后再把这个拼接好的code存到map里
                    map.put(deptList.get(i).getId(),excel.getCode());
                }else{
                    Emp excel = new Emp();
                    excel.setCode(dto.getCode());
                    empList.add(excel);
                }
                //将他的Id也就是编码存到map里
                map.put(deptList.get(i).getId(),deptList.get(i).getId());
            }
        } else {
            Emp excel = new Emp();
            excel.setCode(dto.getCode());
            empList.add(excel);
        }
        return empList;
    }

//     */
//    public static List<DivisionExcelData> buildByDTO(DivisionInfoDTO dto) {
//        List<DivisionExcelData> excelDataList = new ArrayList<>();
//        List<BasinInfoDTO> basinInfoList = dto.getBasinInfoList();
//        Map<String,String> map = new HashMap<>();
//        if (CollectionUtils.isEmpty(basinInfoList)) {
//            DivisionExcelData excel = new DivisionExcelData();
//            excel.setDivCode(showString(dto.getCode()));  // 分区编码
//            excel.setDivName(showString(dto.getName()));  // 分区名称
//            excel.setState(showString(dto.getState() == 1 ? "启用" : "停用"));
//            excelDataList.add(excel);
//
//        } else {
//            for (BasinInfoDTO basinInfo : basinInfoList) {
//                DivisionExcelData excel = new DivisionExcelData();
//                excel.setDivCode(showString(dto.getCode()));  // 分区编码
//                excel.setDivName(showString(dto.getName()));  // 分区名称
//                excel.setState(showString(dto.getState() == 1 ? "启用" : "停用"));
//                excel.setBasinName(showString(basinInfo.getName())); // 子流域名称
//                excelDataList.add(excel);
//            }
//        }
//        return excelDataList;
//    }

//
//    public static List<DivisionExcelData> buildByDTO(DivisionInfoDTO dto) {
//        List<DivisionExcelData> divisionExcelDatalist = Lists.newArrayList();
//        List<DivisionExcelData> excelDataList = new ArrayList<>();
//        List<BasinInfoDTO> basinInfoList = dto.getBasinInfoList();
//        Map<String, String> map = new HashMap<>();
//
//        DivisionExcelData excel = new DivisionExcelData();
//        excel.setDivCode(showString(dto.getCode()));  // 分区编码
//        excel.setState(showString(dto.getState() == 1 ? "启用" : "停用"));
//        excel.setBasinName(showString(basinInfo.getName())); // 子流域名称
//        excel.setDivName(showString(dto.getName()));  // 分区名称
//        String name = "";
//        for (BasinInfoDTO basinInfo : basinInfoList) {
//            name+=basinInfo.getName();
//        }
//
//        if (basinInfoList != null) {
//            for (int i = 0; i < basinInfoList.size(); i++) {
//                BasinInfoDTO basinInfoDTO = basinInfoList.get(i);
//                if (map.containsKey(basinInfoDTO.getDivCode())) {
//                    String mapKey = basinInfoDTO.getCode();
//                    List<DivisionExcelData> collect = excelDataList.stream().filter(f -> !f.getDivCode().equals(mapKey)).collect(Collectors.toList());
//                    excelDataList.clear();
//                    excelDataList.addAll(collect);
//                    DivisionExcelData excel = new DivisionExcelData();
//                    excel.setDivCode(showString(basinInfoDTO.getCode()));  // 分区编码
//                    excel.setDivName(map.get(mapKey) + "," + basinInfoDTO.getName());
//                    map.put(mapKey, excel.getDivName());
//                    excelDataList.add(excel);
//                } else {
//                    DivisionExcelData excel = new DivisionExcelData();
//                    excel.setDivCode(showString(basinInfoDTO.getCode()));  // 分区编码
//                    excel.setDivName(showString(basinInfoDTO.getName()));  // 分区名称
//                    excel.setState(showString(basinInfoDTO.getState() == 1 ? "启用" : "停用"));
//                    excel.setBasinName(showString(basinInfoDTO.getName())); // 子流域名称
//                    excelDataList.add(excel);
//                }
//            }
//        } else {
//            DivisionExcelData excel = new DivisionExcelData();
//            excel.setDivCode(showString(dto.getCode()));  // 分区编码
//            excel.setDivName(showString(dto.getName()));  // 分区名称
//            excel.setState(showString(dto.getState() == 1 ? "启用" : "停用"));
//            excel.setBasinName(showString(basinInfo.getName())); // 子流域名称
//            excelDataList.add(excel);
//        }
//        return excelDataList;
//    }
    @Test
    public void 测试ModifyTime() {
        Emp emp = new Emp();
        empService.update(emp, Wrappers.<Emp>lambdaUpdate()
                .in(Emp::getId, 1)
                .set(true,Emp::getDeleted, 1)
                .set(Emp::getGmtModified, LocalDate.now()));
        List<Emp> list = empService.list();
        System.out.println(list);
    }
    @Test
    public void 测试OrderModifyTime() {
        List<Emp> list = empService.list();
        List<Emp> collect = list.stream().sorted(Comparator.comparing(Emp::getGmtCreate)).collect(Collectors.toList());
        System.out.println(collect);
    }
    @Test
    public void 测试createTime() {
        Emp emp = new Emp();
        emp.setName("十日");
        empService.save(emp);

    }
    @Test
    public void 测试getById() {
        Emp emp = new Emp();
        emp.setName("十日");
        Emp byId = empService.getById(null);

        System.out.println(byId);

    }
    /**
     * 从大到小
     */
    public List<LocalDate> getDescDateList(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> result = new ArrayList<>();
        if(endDate.compareTo(startDate) < 0 ){
            return  result;
        }
        while (true){
            result.add(endDate);
            if(endDate.compareTo(startDate) <= 0){
                break;
            }
            endDate= endDate.plusDays(-1);
        }
        return result;
    }

    /**
     * 从小到大
     */
    public  List<LocalDateTime> getAscDateList(LocalDateTime startDate, LocalDateTime endDate) {
        List<LocalDateTime> result = new ArrayList<>();
        if(endDate.compareTo(startDate) < 0 ){
            return  result;
        }
        while (true){
            result.add(startDate);
            if(startDate.compareTo(endDate) >= 0){
                break;
            }
            startDate = startDate.plusDays(1);
        }
        return result;
    }
    @Test
    public void getEmpGroupByDate() {

        /*ocalDate startDate = LocalDate.parse("2021-10-31", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endDate = LocalDate.parse("2022-01-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Map<String, Object>>  map = empService.getEmpGroupByDate(startDate,endDate);
        System.out.println(map);*/
        /*//开始日期
        LocalDate startDate = LocalDate.parse("2021-10-31", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //结束日期
        LocalDate endDate = LocalDate.parse("2022-01-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //分组的数据
        List listInfos = workBeachDao.findCalendar(begin,finish);
        List listComments = workBeachDao.findCalendarC(begin,finish);
        List listMails = workBeachDao.findCalendarM(begin,finish);
        List listUsers = workBeachDao.findCalendarU(begin,finish);

        //时间区间内的所有值
        List<LocalDate> ascDateList = getAscDateList(startDate, endDate);
        //根据时间区间所有值遍历
        for (LocalDate localDate : ascDateList) {
            Optional<Map<String, Object>> listInfos = listInfos.stream().filter(f -> localDate.equals(LocalDate.parse(f.get("createDate").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))).findAny();
            Optional<Map<String, Object>> listComment = listComments.stream().filter(f -> localDate.equals(LocalDate.parse(f.get("createDate").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))).findAny();
            Optional<Map<String, Object>> listMail = listMails.stream().filter(f -> localDate.equals(LocalDate.parse(f.get("createDate").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))).findAny();
            Optional<Map<String, Object>> listUser = listUsers.stream().filter(f -> localDate.equals(LocalDate.parse(f.get("createDate").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))).findAny();
            //不存在该日期则设置值为0
            if (!listInfos.isPresent()){
                Map<String,Object> hashMap = new HashMap();
                hashMap.put("createDate",localDate);
                hashMap.put("count",0);
                listInfos.add(hashMap);
            }
            //不存在该日期则设置值为0
            if (!listComment.isPresent()){
                Map<String,Object> hashMap = new HashMap();
                hashMap.put("createDate",localDate);
                hashMap.put("count",0);
                listComments.add(hashMap);
            }
            //不存在该日期则设置值为0
            if (!listMail.isPresent()){
                Map<String,Object> hashMap = new HashMap();
                hashMap.put("createDate",localDate);
                hashMap.put("count",0);
                listMails.add(hashMap);
            }
            //不存在该日期则设置值为0
            if (!listUser.isPresent()){
                Map<String,Object> hashMap = new HashMap();
                hashMap.put("createDate",localDate);
                hashMap.put("count",0);
                listUsers.add(hashMap);
            }
        }*/
    }
}
