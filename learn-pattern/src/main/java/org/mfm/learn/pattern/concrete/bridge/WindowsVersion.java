package org.mfm.learn.pattern.concrete.bridge;

public class WindowsVersion extends OperatingSystemVersion {
    @Override
	public void play(String fileName) {
        String osType = "Windows播放";
        this.videoFile.decode(osType,fileName);
    }
}