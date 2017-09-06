package com.example.a15041867.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.PipedOutputStream;
import java.util.ArrayList;

/**
 * Created by 15056201 on 18/5/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "post.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_POST = "post";
    private static final String POST_ID = "post_id";
    private static final String POST_TITLE = "post_title";
    private static final String POST_DESC = "post_desc";

    private static final String TABLE_COMMENT = "comment";
    private static final String COMMENT_ID = "comment_id";
    private static final String COMMENT_DESC = "comment_desc";
    private static final String COMMENT_POST_ID = "comment_post_id";


    String desc;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createPostTableSql = "CREATE TABLE " + TABLE_POST + "("
                + POST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + POST_TITLE + " TEXT," + POST_DESC + " TEXT)";
        db.execSQL(createPostTableSql);
        Log.i("info", "created tables");

        String createCommentTableSql = "CREATE TABLE " + TABLE_COMMENT + "("
                + COMMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COMMENT_DESC + " TEXT," + COMMENT_POST_ID + " INTEGER)";
        db.execSQL(createCommentTableSql);
        Log.i("info", "created tables");

        ContentValues values = new ContentValues();
        values.put(POST_TITLE, "Post 1");
        values.put(POST_DESC,"Post 1 desc");
        db.insert(TABLE_POST, null, values);

        ContentValues values2 = new ContentValues();
        values2.put(POST_TITLE, "Post 2");
        values2.put(POST_DESC,"Post 2 desc");
        db.insert(TABLE_POST, null, values2);

        ContentValues values3 = new ContentValues();
        values3.put(POST_TITLE, "Post 3");
        values3.put(POST_DESC,"Post 3 desc");
        db.insert(TABLE_POST, null, values3);

        ContentValues values4 = new ContentValues();
        values4.put(COMMENT_DESC, "Comment 1");
        values4.put(COMMENT_POST_ID,1);
        db.insert(TABLE_COMMENT, null, values4);

        ContentValues values5 = new ContentValues();
        values5.put(COMMENT_DESC, "Comment 2");
        values5.put(COMMENT_POST_ID,2);
        db.insert(TABLE_COMMENT, null, values5);

        ContentValues values6 = new ContentValues();
        values6.put(COMMENT_DESC, "Comment 3");
        values6.put(COMMENT_POST_ID,3);
        db.insert(TABLE_COMMENT, null, values6);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENT);
        onCreate(db);
    }



    public long insertComment(String commentDesc,int postId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COMMENT_DESC, commentDesc);
        values.put(COMMENT_POST_ID, postId);
        long result = db.insert(TABLE_COMMENT, null, values);
        if (result == -1){
            Log.d("DBHelper", "Insert failed");
        }
        db.close();
        Log.d("SQL Insert",""+ result); //id returned, shouldn’t be -1
        return result;
    }

    public ArrayList<Comment> getCommentDesc(int commentPostId) {
        //TODO return records in Java objects
        ArrayList<Comment> commentsList = new ArrayList<Comment>();
        String selectQuery = "SELECT " + COMMENT_ID + " ," + COMMENT_DESC + " ," + COMMENT_POST_ID  + " FROM " + TABLE_COMMENT + " WHERE " + COMMENT_POST_ID + "=" + commentPostId ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int comment_id = cursor.getInt(0);
                String comment_desc = cursor.getString(1);
                int comment_post_id = cursor.getInt(2);
                Comment comment = new Comment(comment_id,comment_desc,comment_post_id);
                commentsList.add(comment);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return commentsList;
    }

    public ArrayList<Post> getPostTitle() {
        //TODO return records in Java objects
        ArrayList<Post> titleList = new ArrayList<Post>();
        String selectQuery = "SELECT " + POST_ID +"," + POST_TITLE  + " ," + POST_DESC + " FROM " + TABLE_POST;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String desc = cursor.getString(2);
                Post post = new Post(id,title,desc);
                titleList.add(post);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return titleList;
    }

    public String getPostDesc(int id) {
        //TODO return records in Java objects
//        ArrayList<Post> titleList = new ArrayList<Post>();
        String selectQuery = "SELECT " + POST_DESC + " FROM " + TABLE_POST + " WHERE " + POST_ID + "=" + id ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                desc = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return desc;
    }


    public int updatePost(Post post){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(POST_TITLE, post.getTitle());
        values.put(POST_DESC, post.getDesc());
        String condition = POST_ID + "= ?";
        String[] args = {String.valueOf(post.getId())};
        int result = db.update(TABLE_POST, values, condition, args);
        db.close();
        return result;
    }

    public int deletePost(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = POST_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_POST, condition, args);
        db.close();
        return result;
    }
//
//    public ArrayList<Song> get5Songs() {
//        ArrayList<Song> songs = new ArrayList<Song>();
//
//        String selectQuery = "SELECT " + COLUMN_ID + ","
//                + COLUMN_TITLE + "," + COLUMN_SINGERS + "," + COLUMN_YEAR
//                + "," + COLUMN_STARS + " FROM " + TABLE_SONG + "" +
//                " WHERE " + COLUMN_STARS + " = 5";
//
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.rawQuery(selectQuery,null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                int id = cursor.getInt(0);
//                String title = cursor.getString(1);
//                String singers = cursor.getString(2);
//                Integer years = Integer.valueOf(cursor.getString(3));
//                Integer stars = Integer.valueOf((cursor.getString(4)));
//                Song obj = new Song(id,title,singers,years,stars);
//                songs.add(obj);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return songs;
//    }





}
