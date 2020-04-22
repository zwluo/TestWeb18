package com.example.TestWeb18.controller;

import com.example.TestWeb18.util.MultiPdf2One;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HelloWorldController
{
    private static Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @RequestMapping("/cutPdf")
    @ResponseBody
    public void mergePdf(HttpServletRequest request) throws Exception {
        //System.out.println("mergePdf");
        String fileDestination = "abc合并后.pdf";
        String filePath = "C:\\Users\\or\\Desktop\\新建文件夹\\";
        List<String> srcFiles = new ArrayList<String>();
        srcFiles.add("C:\\Users\\or\\Desktop\\新建文件夹\\CS217868528_14941406.pdf");
        srcFiles.add("C:\\Users\\or\\Desktop\\新建文件夹\\CS218146639_14942489.pdf");
        System.out.println(Thread.currentThread().getName());
        MultiPdf2One.createUnitedPdfByItext(fileDestination, filePath ,srcFiles);

    }

    @RequestMapping("/cutPdf2")
    @ResponseBody
    public void mergePdf2(HttpServletRequest request) throws Exception {
        String fileDestination = "abc合并后2.pdf";
        String filePath = "C:\\Users\\or\\Desktop\\新建文件夹\\";
        List<String> srcFiles = new ArrayList<String>();
        /*srcFiles.add("C:\\Users\\or\\Desktop\\新建文件夹\\CS217868528_14941407.pdf");
        srcFiles.add("C:\\Users\\or\\Desktop\\新建文件夹\\CS217868528_14941408.pdf");*/
        srcFiles.add("C:\\Users\\or\\Desktop\\新建文件夹\\CS217868528_14941406.pdf");
        srcFiles.add("C:\\Users\\or\\Desktop\\新建文件夹\\CS218146639_14942489.pdf");
        MultiPdf2One.createUnitedPdfByItext(fileDestination, filePath ,srcFiles);

    }

    @RequestMapping(value = "/getInfo")
    @ResponseBody
    public String getInfo(HttpServletRequest request) throws Exception {
        String fileDestination = "第19回決済システムフォーラムの議事の概要 <br/> Wie geht es dir";

        logger.info("bac");
        return fileDestination;

    }

    @RequestMapping(value = "/updateBatchTrayCodeAndTrayNum", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getPalletInfo(String batchCode, String orderCode, String trayCode, String trayNum, HttpServletRequest request, String postValue, String apiKey) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 500);
        map.put("message", "success");
        map.put("status", 1);

        return map;
    }

}
