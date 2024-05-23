package com.inmar.automation.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
	
	
	public static void delete_File(String downloadPath)
	{
		File path = new File(downloadPath); 
	    File[] files = path.listFiles();
	    for (File file : files) {
	        System.out.println("Deleted filename :"+ file.getName());
	        file.delete();
	    }
	} 
	/* Get the latest file from a specific directory*/
	public static File getLatestFilefromDir(String dirPath){
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	        return null;
	    }
	
	    File lastModifiedFile = files[0];
	    for (int i = 1; i < files.length; i++) {
	       if (lastModifiedFile.lastModified() < files[i].lastModified()) {
	           lastModifiedFile = files[i];
	       }
	    }
	    return lastModifiedFile;
	}
	
	public static  boolean isFileDownloaded_Ext(String dirPath, String ext){
		boolean flag=false;
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	    	System.out.println("No Files");
	        flag = false;
	    }
	    
	    for (int i = 0; i < files.length; i++) {
	    	if(files[i].getName().contains(ext)) {
	    		System.out.println("Yes");
	    		System.out.println(files[i].getName().contains(ext));
	    		flag=true;
	    	}
	    }
	    return flag;
	}
	
	public static boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
	    File dir = new File(downloadPath);
	    File[] dir_contents = dir.listFiles(); 
	  	    
	    for (int i = 0; i < dir_contents.length; i++) {
	        if (dir_contents[i].getName().equals(fileName))
	            return flag=true;
	            }

	    return flag;
	}


	
}
