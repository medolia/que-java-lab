package com.medolia.demo.pattern.facade;

import java.io.File;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public class Client {
    public static void main(String[] args) {
        VideoConversionFacade converter = new VideoConversionFacade();
        File mp4Video = converter.convertVideo("youtubevideo.ogg", "mp4");
        // ...
    }
}
