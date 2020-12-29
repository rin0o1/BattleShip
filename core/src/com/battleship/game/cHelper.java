package com.battleship.game;

import java.io.File;
import java.util.ArrayList;

public class cHelper {

    public static ArrayList<String> getFilesFromFolderPath(
            String folderPath
    )
    {
        File folder= new File(folderPath);
        if (!folder.exists()){return null;}
        ArrayList<String> fileNames= new ArrayList<>();

        for (File f : folder.listFiles()) {
            fileNames.add(f.getPath());
        }
        return fileNames;
    }

}
