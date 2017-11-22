/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.user;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import resource.FileDirectoriesManager;

/**
 *
 * @author jvm
 */
public class FilesList {

    public FilesList() {
    }
    
    public List<String> listFileNames(String pathToApp){
        List<String> imagesList = new ArrayList<>();
        File dir = new File(pathToApp+File.separator
                +"resources"+File.separator+
                    FileDirectoriesManager.getProperty("files"));
        File[] arrFiles = dir.listFiles();
        for (File arrFile : arrFiles) {
            imagesList.add(arrFile.getName());
        }
        return imagesList;
    }
}
