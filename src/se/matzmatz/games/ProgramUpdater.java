package se.matzmatz.games;

import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;

public class ProgramUpdater{
	
	private static final String REPO_USER = "ITNano";
	private static final String REPO_NAME = "GameHub";
		
	private static final String currentVersion = "0.0.0.0";
	
    public static boolean checkForUpdates(){
    	String latestVersion = getLatestVersion();
    	return latestVersion != null && !latestVersion.equals(currentVersion);
    }
    
    public static void doUpdate() {
    	String latestVersion = getLatestVersion();
    	System.out.println("Updating to version "+latestVersion);
    	try {
    		String url = "https://github.com/"+REPO_USER+"/"+REPO_NAME+"/archive/"+latestVersion+".zip";
    		FileOutputStream fs = new FileOutputStream(latestVersion+".zip");
    		fs.getChannel().transferFrom(Channels.newChannel(new URL(url).openStream()), 0, Long.MAX_VALUE);
    		fs.close();
    		unzip(latestVersion+".zip", "../"+latestVersion+"/");
    		new File(latestVersion+".zip").delete();
    		// TODO start new runnable file and close this instance
    	}catch(IOException ioe) {
    		System.out.println("There was an error while updating! O.o");
    		ioe.printStackTrace();
    	}
    }
    
    private static String getLatestVersion() {
        String path = "https://github.com/"+REPO_USER+"/"+REPO_NAME+"/releases/latest";
        try{
            String data = readStringFromURL(path);
            int start = data.indexOf("release-header");
            if(start >= 0){
                start = data.indexOf("<a", start);
                start = data.indexOf("\">", start)+2;
                int end = data.indexOf("</a>", start);
                return data.substring(start, end);
            }
        }catch(IOException ioe){}
        return null;
    }

    private static String readStringFromURL(String requestURL) throws IOException{
        try (Scanner scanner = new Scanner(new URL(requestURL).openStream(), StandardCharsets.UTF_8.toString())){
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }
    
    private static void unzip(String zipFilePath, String destDir) {
    	File dir = new File(destDir);
    	// create output directory if it doesn't exist
    	if(!dir.exists()) dir.mkdirs();
    	FileInputStream fis;
    	//buffer for read and write data to file
    	byte[] buffer = new byte[1024];
    	try {
    		fis = new FileInputStream(zipFilePath);
    		ZipInputStream zis = new ZipInputStream(fis);
    		ZipEntry ze = zis.getNextEntry();
    		while(ze != null){
    			String fileName = ze.getName();
    			File newFile = new File(destDir + File.separator + fileName);
    			if(!ze.isDirectory()) {
	    			System.out.println("Unzipping to "+newFile.getAbsolutePath());
	    			//create directories for sub directories in zip
	    			new File(newFile.getParent()).mkdirs();
	    			FileOutputStream fos = new FileOutputStream(newFile);
	    			int len;
	    			while ((len = zis.read(buffer)) > 0) {
	    				fos.write(buffer, 0, len);
	    			}
	    			fos.close();
    			}else {
    				newFile.mkdirs();
    			}
    			//close this ZipEntry
    			zis.closeEntry();
    			ze = zis.getNextEntry();
    		}
    		//close last ZipEntry
    		zis.closeEntry();
    		zis.close();
    		fis.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
        
    }
}