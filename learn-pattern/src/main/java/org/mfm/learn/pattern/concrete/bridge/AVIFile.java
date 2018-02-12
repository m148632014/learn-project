package org.mfm.learn.pattern.concrete.bridge;

public class AVIFile implements VideoFile {
	public void decode(String osType, String fileName) {
		System.out.println("操作系统："+osType+"文件名称："+fileName);
	}
}