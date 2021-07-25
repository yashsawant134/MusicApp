package com.example.musicapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.musicapp.MyLibrary.RecentPlayModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASENAME="User";
    private static Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASENAME, null, 2);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create Table RecentPlays (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,SongName TEXT,TrackId INTEGER,timing TEXT,Image TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP Table if exists RecentPlays");
        onCreate(db);
    }

    public void addInRecentPlays(String songname,String timing,String image,int trackId){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("SongName",songname);
        cv.put("TrackId",trackId);
        cv.put("timing",timing);
        cv.put("Image",image);

        long result=db.insert("RecentPlays",null,cv);

        if(result==-1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }
        else{
//            Toast.makeText(context,"Successful",Toast.LENGTH_SHORT).show();
        }
    }

    public List<RecentPlayModel> getRecentPlays(){

        List<RecentPlayModel> list=new ArrayList<RecentPlayModel>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from RecentPlays Order by id desc",null);

        if(cursor.moveToFirst()){
            do{
                RecentPlayModel recentPlayModel=new RecentPlayModel();
                recentPlayModel.setImage(cursor.getString(4));
                recentPlayModel.setSongname(cursor.getString(1));
                recentPlayModel.setTiming(cursor.getString(3));
                recentPlayModel.setSongid(cursor.getInt(0));
                recentPlayModel.setTrackid(cursor.getInt(2));
                list.add(recentPlayModel);
            }while (cursor.moveToNext());
        }
        return list;

    }


}
