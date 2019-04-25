package com.example.buoi13;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.IntDef;

import com.example.buoi13.Model.Song;

import java.util.ArrayList;

public class MediaManager implements MediaPlayer.OnCompletionListener {

    @IntDef({NEXT,PREV})                                                                    //ràng buộc giá trị
    @interface INCREATE{
    }


    public static final int NEXT =1;
    public static final int PREV =-1;
    private MediaPlayer player;
    private ArrayList<Song> arrSong;
    private Context context;
    private int currentIndex;

    public MediaManager(ArrayList<Song> arrSong, Context context) {
        this.arrSong = arrSong;
        this.context = context;
    }


    public void start(){                                                                               //nếu có đối tượng player thì chạy
        if (player != null){
            player.start();
        }
    }

    public void stop(){                                                                             //nếu có đối tượng player thì stop
        if (player != null){
            player.stop();
        }
    }

    public void pause(){                                                                            //nếu có đối tượng player thì tạm dừng
        if (player != null){
            player.pause();
        }
    }

    public void release(){                                                                          //thoát ra
        if (player != null){
            player.release();
        }
    }

    public void seek(int position){                                                                 //tua nhạc đến vị trí posstion
        if (player != null){
            player.seekTo(position);
        }
    }

    public void loop(boolean isLooping){                                                            //lặp lại
        if (player != null){
            player.setLooping(isLooping);
        }
    }

    public int getDuration(){                                                                       //giá trị thời gian max của bài hát, giá trị max của seek Bar
        if (player != null){
            return player.getDuration();
        }else {
            return 0;
        }
    }


    public int getCurrentPossition(){                                                               //vị trí hiện tại của seek bar, vị trí hiện tại đang play
        if (player != null){
            return player.getCurrentPosition();
        }
        return 0;
    }

    public void create(int index){                                                                  //khởi tạo media player để mở bài hát khi click vào bài hát
        currentIndex = index;
        release();                                                                                  //giải phóng bài hát hiện tại đang hát để mở bài hát mới
        String data = arrSong.get(index).getData();
        player = MediaPlayer.create(context, Uri.parse(data));
        start();
        player.setOnCompletionListener(this);                                                       //lắng nghe sự kiện để hết bài tự động sang bài mới
    }


    public void changeSong(@INCREATE int increate){                                                           //increate: 1 or -1: tăng giảm index để next bài hát
        currentIndex += increate;
        if (currentIndex >= arrSong.size()){
            currentIndex = 0;
        }else if (currentIndex <0){
            currentIndex = arrSong.size()-1;
        }
        create(currentIndex);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {                                                      //gọi vào khi mediaplayer phát xong, thực hiện chuyển bài mới
        changeSong(NEXT);                                                                           //chuyển sang bài tiếp theo
    }
}
