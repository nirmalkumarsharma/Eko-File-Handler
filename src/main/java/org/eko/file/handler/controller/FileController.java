package org.eko.file.handler.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.io.FilenameUtils;
import org.eko.file.handler.selector.FilePathSelector;
import org.eko.file.handler.writer.ExcelFileWriter;

public class FileController {
	/* Driver function of the above program */
	public static void main(String[] args) throws IOException
	{
		FilePathSelector filePathSelector=new FilePathSelector(); //Selects the file
		Scanner inp=new Scanner(System.in);
		String fileNameAndPath=filePathSelector.getFilePath();
		System.out.println("File Selected : "+fileNameAndPath);
		
		String fileExtension=FilenameUtils.getExtension(fileNameAndPath);
		System.out.print("Enter the column extraction pattern : ");
		String extractionPattern=inp.nextLine(); //Inputs the column pattern
		ArrayList<Integer> extractionColumns=ExtractionPatternController.getExtractionColumns(extractionPattern); //Extracts the list of column from the given patter
		if(fileExtension.equals("xlsx"));
		{
			ExcelFileWriter excelFileWriter=new ExcelFileWriter();
			excelFileWriter.createFile(fileNameAndPath,extractionColumns); //CCreates the file
		}
		inp.close();
	}
}
