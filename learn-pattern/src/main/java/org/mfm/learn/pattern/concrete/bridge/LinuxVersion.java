package org.mfm.learn.pattern.concrete.bridge;

public class LinuxVersion extends OperatingSystemVersion {

	@Override
	public void play(String fileName) {
        String osType = "Linux播放";
        this.videoFile.decode(osType, fileName);
    }

}
