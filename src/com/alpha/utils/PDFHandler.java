/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.utils;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;

/**
 *
 * @author raiiz
 */
public class PDFHandler {

    private final static String URL = "http://api.html2pdfrocket.com/pdf";
    private final static String APIKEY = "a1e41309-bf0b-4684-b53f-853a93036917";

    /**
     * Stores given HTML String or URL to Storage with given filename
     *
     * @param value URL or HTML add quote if you have spaces. use single quotes
     * instead of double
     * @param filename
     */
    public void getFile(String value, String filename) {
        // Validate parameters
        if (value == null || value.length() < 1) {
            return;
        }
        if (filename == null || filename.length() < 1) {
            return;
        }
        //Encode
        value = Util.encodeUrl(value);

        String fullPathToFile = FileSystemStorage.getInstance().getAppHomePath() + filename;
        Util.downloadUrlToFileSystemInBackground(URL + "?apikey=" + APIKEY + "&value=" + value, fullPathToFile);
    }

}
