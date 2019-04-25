package com.example.comt3hbuoi7;

import android.os.Environment;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileManager {
    public String path = Environment.getExternalStorageDirectory().getPath()  ;                        //lấy dữ liệu từ bộ nhớ ngoài

    public List<File>  getFile(String path){
        File f = new File(path);
        return Arrays.asList(f.listFiles());                                                            //trả về toàn bộ file với path truyền vào
    }
}
