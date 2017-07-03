package org.eko.file.handler.selector;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/* Class to to read a source file */
public class FilePathSelector
{
	
	/*A utility function to select and open the source file*/
	public String getFilePath()
	{
		JFileChooser fileChooser=new JFileChooser();
		
		FileFilter filter=new FileNameExtensionFilter("Data Sheet","xlsx"); //Filters the xlsx file
		fileChooser.setFileFilter(filter);
		if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
		{
			return fileChooser.getSelectedFile().getAbsolutePath();
		}
		return "";
	}
}
