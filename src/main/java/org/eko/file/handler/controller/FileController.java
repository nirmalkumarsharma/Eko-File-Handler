package org.eko.file.handler.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.io.FilenameUtils;
import org.eko.file.handler.selector.FilePathSelector;
import org.eko.file.handler.writer.ExcelFileWriter;

public class FileController {
	
	public static void main(String[] args) throws IOException
	{
		FilePathSelector filePathSelector=new FilePathSelector();
		Scanner inp=new Scanner(System.in);
		String fileNameAndPath=filePathSelector.getFilePath();
		System.out.println("File Selected : "+fileNameAndPath);
		
		String fileExtension=FilenameUtils.getExtension(fileNameAndPath);
		System.out.print("Enter the column extraction pattern : ");
		String extractionPattern=inp.nextLine();
		ArrayList<Integer> extractionColumns=ExtractionPatternController.getExtractionColumns(extractionPattern);
		if(fileExtension.equals("xlsx"));
		{
			ExcelFileWriter excelFileWriter=new ExcelFileWriter();
			excelFileWriter.createFile(fileNameAndPath,extractionColumns);
		}
		inp.close();
	}
}
