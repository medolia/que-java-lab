package com.medolia.demo.pattern.facade.lib;

import java.io.File;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public class AudioMixer {
    public File fix(VideoFile result){
        System.out.println("AudioMixer: fixing audio...");
        return new File("tmp");
    }
}
