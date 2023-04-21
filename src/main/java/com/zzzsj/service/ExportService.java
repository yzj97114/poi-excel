package com.zzzsj.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.zzzsj.common.utils.ListPageUtil;
import com.zzzsj.entity.Student;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.*;

@Slf4j
public class ExportService {



    static List<Student> list = new ArrayList<>();

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            8,    //核心线程数
            8, //最大线程数
            0,   //线程池中空闲线程等待新任务的最长时间
            TimeUnit.MILLISECONDS,  //等待时间的单位
            new LinkedBlockingQueue<>());  //任务队列，用于保存等待执行的任务



    static {
        for (int i = 0; i < 583422; i++) {
            Student student = new Student();
            student.setName("zhangsan");
            student.setAddress("1111");
            student.setGender("2");
            student.setId(String.valueOf(i));
            student.setPhone("888888");
            list.add(student);
        }
    }

    public static void main(String[] args) {
        long s1 = System.currentTimeMillis();
        exportSurfList2();
        long e1 = System.currentTimeMillis();
        System.out.println("单线程总耗时:" + (e1 - s1));

        long s = System.currentTimeMillis();
        exportSurfList1();
        long e = System.currentTimeMillis();
        System.out.println("多线程总耗时:" + (e - s));

    }

    /**
     * 单线程 大约执行12s
     *
     */
    public static void exportSurfList2(){
        //文件名
        String fileName = "test2.xlsx";
        try {
            //记录总数:实际中需要根据查询条件进行统计即可:一共多少条
            int totalCount=list.size();
            //每一个Sheet存放10w条数据
            int sheetDataRows = 100000;
            //每次写入的数据量5000,每页查询5000
            int writeDataRows = 5000;
            //计算需要的Sheet数量
            int sheetNum = totalCount % sheetDataRows == 0 ? (totalCount / sheetDataRows) : (totalCount / sheetDataRows + 1);
            //计算一般情况下每一个Sheet需要写入的次数(一般情况不包含最后一个sheet,因为最后一个sheet不确定会写入多少条数据)
            int oneSheetWriteCount = sheetDataRows / writeDataRows;
            //计算最后一个sheet需要写入的次数
            int lastSheetWriteCount = totalCount % sheetDataRows == 0 ? oneSheetWriteCount : (totalCount % sheetDataRows % writeDataRows == 0 ? (totalCount / sheetDataRows / writeDataRows) : (totalCount / sheetDataRows / writeDataRows + 1));
            //必须放到循环外，否则会刷新流
            ExcelWriter excelWriter = EasyExcel.write(fileName).build();
            //开始分批查询分次写入
            for (int i = 0; i < sheetNum; i++) {
                //创建Sheet
                WriteSheet sheet = new WriteSheet();
                sheet.setSheetName("Sheet"+i);
                sheet.setSheetNo(i);
                //循环写入次数: j的自增条件是当不是最后一个Sheet的时候写入次数为正常的每个Sheet写入的次数,如果是最后一个就需要使用计算的次数lastSheetWriteCount
                for (int j = 0; j < (i != sheetNum - 1 ? oneSheetWriteCount : lastSheetWriteCount); j++) {
                    //分页查询一次2000
                    List<Student> students = fetchDataByPage(j + 1 + oneSheetWriteCount * i, writeDataRows);
                    WriteSheet writeSheet = EasyExcel.writerSheet(i, "Sheet" + (i + 1)).head(Student.class)
                            .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).build();
                    excelWriter.write(students, writeSheet);
                }
            }
            excelWriter.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 多线程大概执行 6s
     */
    public static void exportSurfList1(){
        //文件名
        String fileName = "test1.xlsx";
        try {
            //记录总数:实际中需要根据查询条件进行统计即可:一共多少条
            int totalCount=list.size();
            //每一个Sheet存放10w条数据
            int sheetDataRows = 100000;
            //每次写入的数据量5000,每页查询5000
            int writeDataRows = 5000;
            //计算需要的Sheet数量
            int sheetNum = totalCount % sheetDataRows == 0 ? (totalCount / sheetDataRows) : (totalCount / sheetDataRows + 1);
            //计算一般情况下每一个Sheet需要写入的次数(一般情况不包含最后一个sheet,因为最后一个sheet不确定会写入多少条数据)
            int oneSheetWriteCount = sheetDataRows / writeDataRows;
            //计算最后一个sheet需要写入的次数
            int lastSheetWriteCount = totalCount % sheetDataRows == 0 ? oneSheetWriteCount : (totalCount % sheetDataRows % writeDataRows == 0 ? (totalCount / sheetDataRows / writeDataRows) : (totalCount / sheetDataRows / writeDataRows + 1));
            //必须放到循环外，否则会刷新流
            ExcelWriter excelWriter = EasyExcel.write(fileName).build();
            //开始分批查询分次写入
            List<Future<Void>> futures = new ArrayList<>();
            for (int i = 0; i < sheetNum; i++) {
                //创建Sheet
                WriteSheet sheet = new WriteSheet();
                sheet.setSheetName("Sheet"+i);
                sheet.setSheetNo(i);
                int sheetIndex = i;
                Future<Void> future = executor.submit(()->{
                    //循环写入次数: j的自增条件是当不是最后一个Sheet的时候写入次数为正常的每个Sheet写入的次数,如果是最后一个就需要使用计算的次数lastSheetWriteCount
                    for (int j = 0; j < (sheetIndex != sheetNum - 1 ? oneSheetWriteCount : lastSheetWriteCount); j++) {
                        //分页查询一次2000
                        List<Student> students = fetchDataByPage(j + 1 + oneSheetWriteCount * sheetIndex, writeDataRows);
                        WriteSheet writeSheet = EasyExcel.writerSheet(sheetIndex, "Sheet" + (sheetIndex + 1)).head(Student.class)
                                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).build();
                        synchronized (excelWriter) {
                            excelWriter.write(students, writeSheet);
                        }
                    }
                    return null;
                });
                futures.add(future);
            }
            for (Future<Void> future : futures) {
                future.get();
            }
            excelWriter.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            executor.shutdown();
        }
    }





    // 分页查询数据
    private static List<Student> fetchDataByPage(int pageNo, int pageSize) {
        // TODO: 调用外部服务分页查询数据
        ListPageUtil<Student> studentListPageUtil = new ListPageUtil<>(list, pageNo, pageSize);
        return studentListPageUtil.getData();
    }


}
