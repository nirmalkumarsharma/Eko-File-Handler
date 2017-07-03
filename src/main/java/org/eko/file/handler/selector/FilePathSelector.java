package org.eko.file.handler.selector;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FilePathSelector
{
	public String getFilePath()
	{
		JFileChooser fileChooser=new JFileChooser();
		FileFilter filter=new FileNameExtensionFilter("Data Sheet", "txt","xlsx");
		fileChooser.setFileFilter(filter);
		if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
		{
			return fileChooser.getSelectedFile().getAbsolutePath();
		}
		return "";
	}
}
