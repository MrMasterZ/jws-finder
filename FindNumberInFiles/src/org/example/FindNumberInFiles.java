package org.example;

public interface FindNumberInFiles {
	public Result findNumber(Integer number) throws Exception;

	public boolean findNumberInOneFile(String path);
}