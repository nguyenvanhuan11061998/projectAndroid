package com.example.buoi72_file;

import android.os.Environment;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileManager {
    public  String path = Environment.getExternalStorageDirectory().getPath();

    public List<File> getFile(String path){                                                     //trả về một danh sách các File với path truyền vào
        File f = new File(path);
        return Arrays.asList(f.listFiles());
    }
}
