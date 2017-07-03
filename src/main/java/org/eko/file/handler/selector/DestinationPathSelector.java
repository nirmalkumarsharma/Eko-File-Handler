package org.eko.file.handler.selector;

import javax.swing.JFileChooser;

/* Class to save the file */
public class DestinationPathSelector {

	/* A utility function to get destination pathname and filename to save */
	public static String getDestinationPath()
	{
		String destinationPath = null;
		JFileChooser fileChooser=new JFileChooser();
		if(fileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
		{
			destinationPath=fileChooser.getSelectedFile().getAbsolutePath();
		}
		
		return destinationPath;
	}
}
