package com.qding.api.controllers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by qd on 2017/3/25.
 */
@Controller
@RequestMapping("/dynamicImage")
public class DynamicImageController {

    /**
     * 生成二维码动态图片
     * @param request
     * @param response
     * @param content
     * @param w
     * @param h
     */
    @RequestMapping(value = "qrCodeImage",method= RequestMethod.GET)
    public void qrCodeImage(HttpServletRequest request,HttpServletResponse response,
                            @RequestParam(value = "content", required = false) String content,
                            @RequestParam(value = "w", defaultValue = "300", required = false) Integer w,
                            @RequestParam(value = "h", defaultValue = "300", required = false) Integer h){

        if(content==null || content.equals("")){
            return;
        }
        ServletOutputStream stream = null;
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        try {
            stream = response.getOutputStream();
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix matrix = writer.encode(content,
                    BarcodeFormat.QR_CODE, w, h, hints);
            MatrixToImageWriter.writeToStream(matrix, "JPG", stream);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (stream != null) {
                try {
                    stream.flush();
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }
}
