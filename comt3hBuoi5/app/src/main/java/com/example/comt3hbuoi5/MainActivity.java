package com.example.comt3hbuoi5;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FaceAdapter.FaceItemListener {

    private ArrayList<face> data;
    private FaceAdapter adapter;
    private RecyclerView lvFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initView() {
        lvFace = findViewById(R.id.lv_face);
        adapter = new FaceAdapter(this, data);
        lvFace.setAdapter(adapter);
        adapter.setListener(this);
    }

    private void initData() {
        data = new ArrayList<>();
        data.add(new face(R.drawable.facesmile, "smile"));
        data.add(new face(R.drawable.faceglasses, "glasses"));
        data.add(new face(R.drawable.facesad, "sad"));
        data.add(new face(R.drawable.facesmilebig, "smilebig"));
        data.add(new face(R.drawable.imoticon, "imoticon"));
        data.add(new face(R.drawable.facesmile, "smile"));
        data.add(new face(R.drawable.faceglasses, "glasses"));
        data.add(new face(R.drawable.facesad, "sad"));
        data.add(new face(R.drawable.facesmilebig, "smilebig"));
        data.add(new face(R.drawable.imoticon, "imoticon"));
        data.add(new face(R.drawable.facesmile, "smile"));
        data.add(new face(R.drawable.faceglasses, "glasses"));
        data.add(new face(R.drawable.facesad, "sad"));
        data.add(new face(R.drawable.facesmilebig, "smilebig"));
        data.add(new face(R.drawable.imoticon, "imoticon"));
        data.add(new face(R.drawable.facesmile, "smile"));
        data.add(new face(R.drawable.faceglasses, "glasses"));
        data.add(new face(R.drawable.facesad, "sad"));
        data.add(new face(R.drawable.facesmilebig, "smilebig"));
        data.add(new face(R.drawable.imoticon, "imoticon"));
    }

    @Override
    public void onClick(int position) {
        Snackbar.make(lvFace, data.get(position).getName(), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(final int position) {
        new AlertDialog.Builder(this).setTitle("Delete").setMessage("Do you want delete")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                data.remove(position);
                adapter.notifyDataSetChanged();                                                         //data thay đổi , gọi vào đây để cập nhật lại data
            }
        }).setCancelable(true).show();         //setCancelable
    }
}