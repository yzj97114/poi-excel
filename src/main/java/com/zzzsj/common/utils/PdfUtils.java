package com.zzzsj.common.utils;

import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.zzzsj.common.enums.ErrorCode;
import com.zzzsj.common.exception.BizException;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangpeng on 2018/02/01.
 */
public class PdfUtils {
    /**
     *  利用模板生成pdf
     */
    public static void pdfout(Map<String,Object> o, HttpServletResponse response) {
        // 模板路径
        String templatePath = "/Users/yyyzj/Desktop/aaa.pdf";
        // 生成的新文件路径
        String newPDFPath = "/Users/yyyzj/Desktop/bbb.pdf";

        PdfReader reader;
        //FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        OutputStream out = null;
        try {
            //中文字体
            BaseFont bfChinese = BaseFont.createFont( "STSongStd-Light" ,"UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
            Font font = new Font(bfChinese, 12,Font.NORMAL);

            // 输出流
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + URLEncoder.encode("bbb" + ".pdf", "UTF-8"));
            out = new BufferedOutputStream(response.getOutputStream());
            /**
             * 读取pdf模板
             */
            reader = new PdfReader(templatePath);
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            //文字类的内容处理
            Map<String,String> datemap = (Map<String,String>)o.get("datemap");
            for(String key : datemap.keySet()){
                String value = datemap.get(key);
                form.setField(key,value);
            }
            //图片类的内容处理
            Map<String,String> imgmap = (Map<String,String>)o.get("imgmap");
            for(String key : imgmap.keySet()) {
                String value = imgmap.get(key);
                String imgpath = value;
                int pageNo = form.getFieldPositions(key).get(0).page;
                Rectangle signRect = form.getFieldPositions(key).get(0).position;
                float x = signRect.getLeft();
                float y = signRect.getBottom();
                //根据路径读取图片
                Image image = Image.getInstance(imgpath);
                //获取图片页面
                PdfContentByte under = stamper.getOverContent(pageNo);
                //图片大小自适应
                image.scaleToFit(signRect.getWidth(), signRect.getHeight());
                //添加图片
                image.setAbsolutePosition(x, y);
                under.addImage(image);
            }
            /**
             * 如果为false，生成的PDF文件可以编辑，如果为true，生成的PDF文件不可以编辑
             */
            stamper.setFormFlattening(true);
            stamper.close();
            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();

        } catch (IOException e) {
            throw new BizException(ErrorCode.SYS_EXCEPTION.getCode(), ErrorCode.SYS_EXCEPTION.getMsg());
        } catch (DocumentException e) {
            throw new BizException(ErrorCode.SYS_EXCEPTION.getCode(), ErrorCode.SYS_EXCEPTION.getMsg());
        }

    }


}