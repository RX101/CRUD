package com.example.a15041867.crud;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    TextView tv;
    Post post;
    Button btnComment, btnUpdate, btnDelete;
    EditText etUpdate, etCComment;
    DBHelper db = new DBHelper(MainActivity2.this);
    ListView lvComment;
    ArrayList<Comment> alComment;
    ArrayAdapter aaComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv = (TextView)findViewById(R.id.tv);
        btnComment = (Button)findViewById(R.id.btnComment);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        etUpdate = (EditText)findViewById(R.id.etUpdate);
        etCComment = (EditText)findViewById(R.id.etComment);
        lvComment = (ListView)findViewById(R.id.lvComment);
        Intent i = getIntent();
        post = (Post) i.getSerializableExtra("data");
        tv.setText(db.getPostDesc(post.getId()));

        alComment = new ArrayList<Comment>();
        alComment = db.getCommentDesc(post.getId());
        aaComment = new ArrayAdapter(MainActivity2.this,android.R.layout.simple_list_item_1,alComment);
        lvComment.setAdapter(aaComment);
        aaComment.notifyDataSetChanged();
        db.close();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db2 = new DBHelper(MainActivity2.this);
                String desc = etUpdate.getText().toString();
                if(desc.equals("")){
                    etUpdate.setError("The description cannot be nil.");
                }else {
                    post.setDesc(desc);
                    db2.updatePost(post);
                    tv.setText(db2.getPostDesc(post.getId()));
                    etUpdate.setText("");
                    db2.close();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db3 = new DBHelper(MainActivity2.this);
                db.deletePost(post.getId());
                tv.setText(db3.getPostDesc(post.getId()));
                db3.close();
                finish();
            }
        });


        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(MainActivity2.this);
                String comment  = etCComment.getText().toString();
                db.insertComment(comment,post.getId());
                db.close();
                etCComment.setText("");
                alComment = db.getCommentDesc(post.getId());
                aaComment = new ArrayAdapter(MainActivity2.this,android.R.layout.simple_list_item_1,alComment);
                lvComment.setAdapter(aaComment);
                aaComment.notifyDataSetChanged();
            }
        });
    }

}
