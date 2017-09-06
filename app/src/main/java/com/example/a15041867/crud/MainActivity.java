package com.example.a15041867.crud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    TextView post1,post2,post3;
    ListView lvPost;
    ArrayList<Post> alPost;
    ArrayAdapter aaPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper db = new DBHelper(MainActivity.this);
        lvPost = (ListView)findViewById(R.id.listViewPost);
        alPost = new ArrayList<Post>();
        alPost = db.getPostTitle();
        aaPost = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,alPost);
        db.close();
        lvPost.setAdapter(aaPost);
        aaPost.notifyDataSetChanged();

        lvPost.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(MainActivity.this,
                        MainActivity2.class);
                Post data = alPost.get(position);
                i.putExtra("data",data);
                startActivity(i);
            }
        });

//        post1 = (TextView)findViewById(R.id.post1);
//        post2 = (TextView)findViewById(R.id.post2);
//        post3 = (TextView)findViewById(R.id.post3);
//
//        post1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
//                startActivity(intent);
//            }
//        });
//
//        post2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
//                startActivity(intent);
//            }
//        });
//
//
//        post3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
//                startActivity(intent);
//            }
//        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper db = new DBHelper(MainActivity.this);
        alPost = new ArrayList<Post>();
        alPost = db.getPostTitle();
        aaPost = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,alPost);
        db.close();
        lvPost.setAdapter(aaPost);
        aaPost.notifyDataSetChanged();
    }
}
