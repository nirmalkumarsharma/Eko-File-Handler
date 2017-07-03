package org.eko.file.handler.selector;

import javax.swing.JFileChooser;

public class DestinationPathSelector {

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
