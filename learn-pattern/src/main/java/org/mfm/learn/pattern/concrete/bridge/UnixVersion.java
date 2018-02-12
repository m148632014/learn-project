package org.mfm.learn.pattern.concrete.bridge;

public class UnixVersion extends OperatingSystemVersion {
    @Override
	public void play(String fileName) {
        String osType = "Unix播放";
        this.videoFile.decode(osType,fileName);
    }

}