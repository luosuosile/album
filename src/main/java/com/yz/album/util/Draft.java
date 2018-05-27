package com.yz.album.util;


import org.jooq.util.GenerationTool;

import java.io.*;

public class Draft {

    public static void main(String[] args) {
        codeGen();
    }

    public static void codeGen() {
        try {
            GenerationTool.generate(getXmlString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static String getXmlString() {
        String xmlString;
        byte[] strBuffer = null;
        int flen = 0;
        File xmlfile = new File("C:\\IdeaWorkSpace\\album\\src\\main\\resources\\jooq-codegen.xml");
        try {
            InputStream in = new FileInputStream(xmlfile);
            flen = (int)xmlfile.length();
            strBuffer = new byte[flen];
            in.read(strBuffer, 0, flen);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        xmlString = new   String(strBuffer);      //构建String时，可用byte[]类型，
        return xmlString;

    }
}
