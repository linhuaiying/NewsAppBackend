package com.how2java.springboot.web;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.IllegalFormatWidthException;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.basic.BasicFormattedTextFieldUI;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifImageDirectory;
import com.drew.metadata.exif.ExifSubIFDDirectory;

import net.coobird.thumbnailator.Thumbnails;
  
//该注解表示返回数据给前端
@RestController
public class UploadController {

	//接收多个文件，并返回文件地址
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public List<String> upload(HttpServletRequest req, @RequestParam("file") MultipartFile[] files) {
    	    if(files.length == 0) return null;
    	    String fileName = "";
    	    List<String> pathList = new ArrayList<String>();
            try {
            	for(int i = 0; i < files.length; i++) {
            		 fileName = System.currentTimeMillis()+files[i].getOriginalFilename();
                     String destFileName=req.getServletContext().getRealPath("")+"uploaded"+File.separator+fileName;
                     String destFileName2=req.getServletContext().getRealPath("")+"imags"+File.separator+fileName;
                     File destFile = new File(destFileName);
                     destFile.getParentFile().mkdirs();
                     files[i].transferTo(destFile);
                     Thumbnails.of(destFileName) //原图地址
                     .scale(1) //保持原图
                     .outputQuality(1f) //保持质量
                     .rotate(getRotateAngleForPhoto(destFileName)) //旋转图片
                     .toFile(destFileName2); //目标地址

                     pathList.add("http://" + req.getServerName() + ":" + req.getServerPort() + "/" + "imags/" + fileName);
            	}
                 
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            //返回图片的网络地址给前端
            return pathList;
    }
  	 
  	/**
      * 获取图片正确显示需要旋转的角度（顺时针）
      * @return
  	 * @throws IOException 
      */
     public static int getRotateAngleForPhoto(String filePath) throws IOException{
         File file = new File(filePath);
         int angle = 0;
         Metadata metadata;
         try {
             metadata = JpegMetadataReader.readMetadata(file);
             // 取具体某一项的信息
             Iterable<ExifIFD0Directory> exifSubIFDDirectoryIterable = metadata.getDirectoriesOfType(ExifIFD0Directory.class);
             if (exifSubIFDDirectoryIterable != null) {
                 Iterator<ExifIFD0Directory> subIFDDirectoryIterator = exifSubIFDDirectoryIterable.iterator();
                 if (subIFDDirectoryIterator != null && subIFDDirectoryIterator.hasNext()) {
                	 ExifIFD0Directory dr = subIFDDirectoryIterator.next();
                     if (dr.containsTag(ExifIFD0Directory.TAG_ORIENTATION)) {
                    	 // Exif信息中方向　　
                         int orientation = dr.getInt(ExifIFD0Directory.TAG_ORIENTATION); 
                         // 原图片的方向信息
                         if(6 == orientation ){
                             //6本来要旋转90，但是Thumbnails会自动处理
                             angle = 0;
                         }else if( 3 == orientation){
                            //3旋转180
                             angle = 180;
                         }else if( 8 == orientation){
                            //8旋转90
                             angle = 270;
                         }
                        // System.out.println(orientation + "   " + dr.getDescription(ExifIFD0Directory.TAG_ORIENTATION));
                     }
                 }
             }
         } catch (JpegProcessingException e) {
             e.printStackTrace();
         } catch (MetadataException e) {
             e.printStackTrace();
         }
       //  System.out.println(angle);
         return angle;
     }
}