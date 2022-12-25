package de.ng.cloud.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {
	
	public static void copyLocalResourceFileToFolder(String resourceFolder, String folder) throws IOException {
		copyLocalResourceFileToFolder(resourceFolder, resourceFolder.split("/")[resourceFolder.split("/").length - 1], new File(folder));
	}
	
	public static void copyLocalResourceFileToFolder(String resourceFolder, String resourceFileName, String folder) throws IOException {
		copyLocalResourceFileToFolder(resourceFolder, resourceFileName, new File(folder));
	}
	
	public static void copyLocalResourceFileToFolder(String resourceFolder, String resourceFileName, File folder) throws IOException {
		if(!folder.exists())
			folder.mkdirs();
		
		File newFile = new File(folder.getPath() + "/" + resourceFileName);
		
		if(!newFile.exists())
			newFile.createNewFile();
		
		try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceFolder)) {
			try (FileOutputStream outputStream = new FileOutputStream(newFile)) {
				
				if(inputStream == null)
					throw new FileNotFoundException("File not exist to copy: " + resourceFolder + " to " + folder.getPath());
				
				int read = 0;
				byte[] buffer = new byte[1024];
				
				while((read = inputStream.read(buffer, 0, buffer.length)) != -1)
					outputStream.write(buffer, 0, read);
				outputStream.close();
			}
			inputStream.close();
		}
	}

	public static void copyLocalResourceFolderToFolder(String resourceFile, String copyIn) throws IOException {
		copyLocalResourceFolderToFolder(resourceFile, new File(copyIn));
	}
	
	public static void copyLocalResourceFolderToFolder(String resourceFile, File copyIn) throws IOException {
		if(!copyIn.exists())
			copyIn.mkdirs();
		
		for(File file : new File(Thread.currentThread().getContextClassLoader().getResource(resourceFile).getPath()).listFiles())
			if(file.isDirectory())
				copyLocalResourceFolderToFolder(resourceFile + "/" + file.getName(), new File(copyIn.getPath() + "/" + file.getName()));
			else
				copyLocalResourceFileToFolder(resourceFile + "/" + file.getName(), file.getName(), copyIn);
	}

	public static void copyFolderToFolder(String folder, String copyIn) throws IOException {
		copyFolderToFolder(new File(folder), new File(copyIn));
	}
	
	public static void copyFolderToFolder(File folder, File copyIn) throws IOException {
		if(!folder.exists())
			throw new FileNotFoundException("Folder not exist to copy: " + folder.getPath() + " to " + copyIn.getPath());
		
		if(!copyIn.exists())
			copyIn.mkdirs();
		
		for(File file : folder.listFiles()) {
			try {
				if(file.isDirectory())
					copyFolderToFolder(file, new File(copyIn.getPath() + "/" + file.getName()));
				else
					copyFileToFolder(file, copyIn);
			} catch(IOException ex) {
				System.out.println("Failed to load file " + file.getPath());
				ex.printStackTrace();
			}
		}
	}
	
	public static void copyFileToFolder(String file, String folder) throws IOException {
		copyFileToFolder(new File(file), new File(folder));
	}
	
	public static void copyFileToFolder(File file, File folder) throws IOException {
		if(!file.exists())
			throw new FileNotFoundException("File not exist to copy: " + file.getPath() + " to " + folder.getPath());
		
		if(!folder.exists())
			folder.mkdirs();
		
		File newFile = new File(folder.getPath() + "/" + file.getName());
		
		if(!newFile.exists())
			newFile.createNewFile();
		
		try (FileInputStream inputStream = new FileInputStream(file)) {
			try (FileOutputStream outputStream = new FileOutputStream(newFile)) {
				int read = 0;
				byte[] buffer = new byte[1024];
				
				while((read = inputStream.read(buffer, 0, buffer.length)) != -1)
					outputStream.write(buffer, 0, read);
				outputStream.close();
			}
			inputStream.close();
		}
	}
}