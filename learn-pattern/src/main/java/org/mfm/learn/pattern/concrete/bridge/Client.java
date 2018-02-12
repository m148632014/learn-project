package org.mfm.learn.pattern.concrete.bridge;

public class Client {
    public static void main(String args[]) {
        VideoFile vf;
        OperatingSystemVersion osType1 = new WindowsVersion();
        vf = new AVIFile();
        osType1.setVideoFile(vf);
        osType1.play("AVI");

        OperatingSystemVersion osType2 = new LinuxVersion();
        vf = new AVIFile();
        osType2.setVideoFile(vf);
        osType2.play("AVI");

        OperatingSystemVersion osType3 = new UnixVersion();
        vf = new WMVFile();
        osType3.setVideoFile(vf);
        osType3.play("WMV");
    }
}
