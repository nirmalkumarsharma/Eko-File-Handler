package org.eko.file.handler.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class ExtractionPatternController
{
	public static ArrayList<Integer> getExtractionColumns(String extractionPatternWithSpace)
	{
		String extractionPattern=extractionPatternWithSpace.replaceAll("\\s", "");
		StringTokenizer columnRanges=new StringTokenizer(extractionPattern, " ,");
		ArrayList<String> ranges=new ArrayList<String>();
		while(columnRanges.hasMoreTokens())
		{
			ranges.add(columnRanges.nextToken());
		}
		ArrayList<Integer> finalColumns=new ArrayList<Integer>();
		Iterator<String> rangeIterator=ranges.iterator();
		while(rangeIterator.hasNext())
		{
			String columnRange=(String) rangeIterator.next();
			if(columnRange.length()==1)
			{
				finalColumns.add(Integer.parseInt(columnRange)-1);
			}
			if(columnRange.length()==3)
			{
				int start=Integer.parseInt(String.valueOf(columnRange.charAt(0)));
				int end=Integer.parseInt(String.valueOf(columnRange.charAt(2)));
				int i=start;
				while(i<=end)
				{
					finalColumns.add(i++ -1);
				}
			}
		}
		return finalColumns;
	}
}
