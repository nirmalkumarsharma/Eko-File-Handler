package org.eko.file.handler.writer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eko.file.handler.selector.DestinationPathSelector;

public class ExcelFileWriter
{
	public void createFile(String fileNameAndPath, ArrayList<Integer> extractionColumns) throws IOException
	{
		FileInputStream fileInputStream=new FileInputStream(fileNameAndPath);
		XSSFWorkbook workbookRead=new XSSFWorkbook(fileInputStream);
		XSSFWorkbook workbookWrite=new XSSFWorkbook();
		Sheet inputSheet=workbookRead.getSheetAt(0);
		XSSFSheet sheet=workbookWrite.createSheet();
		Iterator<Row> readIterator=inputSheet.iterator();
		int rowNum=0;
		while(readIterator.hasNext())
		{
			int readColNum=0;
			int writeColNum=0;
			Row currReadRow=readIterator.next();
			Row currWriteRow=sheet.createRow(rowNum++);
			Iterator<Cell> readCellIterator=currReadRow.iterator();
			while (readCellIterator.hasNext())
			{
				Cell currReadCell=(Cell) readCellIterator.next();
				
				if(extractionColumns.contains(readColNum++))
				{
					Cell currWriteCell=currWriteRow.createCell(writeColNum++);
					
					if(currReadCell.getCellTypeEnum()==CellType.STRING)
					{
						String stringInp=currReadCell.getStringCellValue();
						String stringOut="";
						
						String regex = "([a-zA-Z0-9\\/-]*)(NEFT|RTGS|IMPS|UPI)(\\/|\\-*)([a-zA-Z0-9\\/|\\-]*)";
						
						if(Pattern.matches(regex,stringInp))
						{
							Pattern pattern = Pattern.compile(regex);
							Matcher matcher = pattern.matcher(stringInp);
							if(matcher.find())
							{
								stringOut=matcher.group(1)+matcher.group(4);
							}
							currWriteCell.setCellValue(stringOut);
						}
						else
						currWriteCell.setCellValue(currReadCell.getStringCellValue());
					}
					else
					if(currReadCell.getCellTypeEnum()==CellType.NUMERIC)
					{
						if(DateUtil.isCellDateFormatted(currReadCell))
						{
							CellStyle cellStyle = workbookWrite.createCellStyle();
							CreationHelper createHelper = workbookWrite.getCreationHelper();
							cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/YYYY"));
							currWriteCell.setCellStyle(cellStyle);
							Date date=currReadCell.getDateCellValue();
							currWriteCell.setCellValue(date);
						}
						else
						currWriteCell.setCellValue(currReadCell.getNumericCellValue());
					}
				}
			}
		}
		String destinationPathAndName=DestinationPathSelector.getDestinationPath();
		String destFileExt=FilenameUtils.getExtension(destinationPathAndName);
		if(destFileExt.equals(null)||destFileExt.equals(""))
		{
			destinationPathAndName+=".xlsx";
		}
		FileOutputStream fileOutputStream=new FileOutputStream(destinationPathAndName);
		workbookWrite.write(fileOutputStream);
		workbookWrite.close();
		workbookRead.close();
		System.out.println("Writing file to : "+destinationPathAndName);
	}
}
