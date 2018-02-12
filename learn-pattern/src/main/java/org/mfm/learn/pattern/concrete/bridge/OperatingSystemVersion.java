package org.mfm.learn.pattern.concrete.bridge;

public abstract class OperatingSystemVersion {
	protected VideoFile videoFile;


	public abstract void play(String fileName);

	public void setVideoFile(VideoFile videoFile) {
		this.videoFile = videoFile;
	}

}
