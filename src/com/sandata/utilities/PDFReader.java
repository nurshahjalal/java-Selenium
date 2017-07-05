package com.sandata.utilities;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.testng.Assert;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by vumvo on 3/15/2017.
 */
public class PDFReader {
    String url;
    PDDocument pdDoc = null;

    public static PDFReader open(String strURL) {
        return new PDFReader(strURL);
    }

    public PDFReader(String strURL) {
        try {
            System.out.println("Loading the PDF document from following source " + strURL);
            BufferedInputStream inputStream = new BufferedInputStream(new URL(strURL).openStream());
            this.pdDoc = PDDocument.load(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PDFReader verifyTextExistOnFirstPage(String expectedText) {
        PDFTextStripper stripper = null;
        try {
            stripper = new PDFTextStripper();
            stripper.setStartPage(1);
            stripper.setEndPage(1);
            String content = stripper.getText(this.pdDoc);
            System.out.println(content);
            Assert.assertTrue(content.contains(expectedText), expectedText + " cannot be found on the 1st page");
            return this;
        } catch (IOException e) {
            e.printStackTrace();
            return this;
        }
    }

    public String getTextExistOnFirstPage() {
        PDFTextStripper stripper = null;
        try {
            stripper = new PDFTextStripper();
            stripper.setStartPage(1);
            stripper.setEndPage(1);
            return stripper.getText(this.pdDoc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void close(){
        try {
            this.pdDoc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public PDFReader verifyTextOnSpecifiedPage(String expectedText, int PageNumber) {
		// TODO Auto-generated method stub
		
		        PDFTextStripper stripper = null;
		        try {
		            stripper = new PDFTextStripper();
		            stripper.setStartPage(PageNumber);
		            stripper.setEndPage(PageNumber);
		            String content = stripper.getText(this.pdDoc);
		            System.out.println(content);
		            Assert.assertTrue(content.contains(expectedText), expectedText + " cannot be found on the 1st page");
		            return this;
		        } catch (IOException e) {
		            e.printStackTrace();
		            return this;
		        }
		    }

}
