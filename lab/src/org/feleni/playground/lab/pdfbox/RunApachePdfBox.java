package org.feleni.playground.lab.pdfbox;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdfparser.PDFStreamParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.font.PDFont;

import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.apache.pdfbox.util.Matrix;


public class RunApachePdfBox {

		  private static Logger LOG = LogManager.getLogger(RunApachePdfBox.class);

	public static void main(String[] args)  {

		try {
			LOG.info("START: " + RunApachePdfBox.class.getSimpleName());
			test();
			LOG.info("END  : " + RunApachePdfBox.class.getSimpleName());
		} catch (Exception e) {
			LOG.error(e,e);
		}
	
	}

	private static void test() throws Exception{
		   String text = getText(new File("C:\\Users\\Michael\\dev\\softwarekanzlei\\ws\\work\\data\\privat\\kündigung\\Kündigung ADAC.pdf"));
		
		
	}
	
	static String getText(File pdfFile) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {
	    PDDocument doc = PDDocument.load(pdfFile);
   
	    anaylse(doc);
	    
	    PDFTextStripper stripper = new PDFTextStripper();
	    String result =  stripper.getText(doc);
	    

	    System.out.println(result);
	    
	    System.out.println(doc.getNumberOfPages());
	    
	    //getCharactersByArticle()
	    
	  //getCharactersListMapping()

	    
	
	    
	    for(Field f: stripper.getClass().getDeclaredFields()){
	    	System.out.println(f.getName() );
	    }
	    
	    
	    
	    
	    System.out.println(stripper.getClass().getDeclaredField("charactersByArticle").getType());
	    System.out.println(		stripper.getClass().getDeclaredField("characterListMapping").getType());
	    
	    for(int i=0; i < doc.getNumberOfPages();i++){

		    Method method = stripper.getClass().getDeclaredMethod("getCharactersByArticle");
		    method.setAccessible(true);
		    Object r = method.invoke(stripper);
	    	stripper.setSortByPosition(true);
	    
	    	stripper.setStartPage(i);
	    	stripper.setEndPage(i);
	    	result = stripper.getText(doc);
	    	System.out.println(result);
	    	
	    	
	    	
	    	
	    	   //System.out.println( i + " :" +stripper.getTotalCharCnt());


	    	ArrayList v = (ArrayList) r;
	    	   
	    	   for(Object o : v){
	    		   System.out.println(o.getClass());
	    		   ArrayList al = (ArrayList) o;
	    		   
	    		   for(Object o2: al){
	    			   TextPosition tp = (TextPosition) o2;
	    			   System.out.println(tp.toString());
	    			   
	    		   }
	    		   
	    	   }
	    }
	    
	    
	    return result;
	}

	private static void anaylse(PDDocument doc) throws IOException {
		doc.getPages().getCount();
		System.out.println(doc.getPages().getCount());
		
		
		PDPage page =doc.getPage(0);
		
		Iterator<PDStream> iter = page.getContentStreams();
		
		while (iter.hasNext()){
			PDStream s=iter.next();
			s.getLength();
			
		    PDFStreamParser parser = new PDFStreamParser(s);
		    System.out.println(parser.getClass());
		    
		}
		
	}

}
