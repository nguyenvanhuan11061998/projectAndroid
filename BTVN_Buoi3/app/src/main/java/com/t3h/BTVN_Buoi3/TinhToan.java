package com.t3h.BTVN_Buoi3;

import java.util.Random;

public class TinhToan {
    Random random = new Random();
    private int TongSai(int x,int y){
        int Tong = x+y;
        int so = random.nextInt(20);
        Tong = Tong + so;
        return Tong;
    }

    public int TongRandom(int x, int y){
        int Tong = x+y;
        int Dungsai = random.nextInt(2);
        if(Dungsai == 0){
            return Tong;
        }else {
            Tong = TongSai(x,y);
            return Tong;
        }
    }

    public int TaoSo(){
        int so = random.nextInt(1000);
        return so;
    }

    public boolean KiemTra(int x, int y, int Tong){
        if(x + y == Tong){
            return true;
        }else{
            return false;
        }
    }

}
