package shenkar.koruApps.PackChetApp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GroupsActivity extends AppCompatActivity {
    ListView groupsList;
    ArrayList<String> groups;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbRef ;
    ArrayAdapter <String> listAdapter;
    ProgressBar progressBar ;
    Button settingsBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        groupsList = (ListView) findViewById(R.id.groupsList);
        Button currButton = (Button) findViewById(R.id.chatsBt);
        currButton.setBackgroundResource(R.drawable.chatselected);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        groups = new ArrayList<>();
        dbRef = database.getReference().child("Courses");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                groups.removeAll(groups);
                for (DataSnapshot item : dataSnapshot.getChildren()){
                    groups.add(item.getKey().toString());
                }
                progressBar.setVisibility(View.INVISIBLE);
                updateList();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("someting went worng");
            }
        });

        final Intent intent = new Intent(this,Messeges.class);


        groupsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra("groupId",groups.get(position));

                Log.d("log:",groups.get(position));
                startActivity(intent);
            }
        });

    }

    private void updateList(){
        listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, groups);
        groupsList.setAdapter(listAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}
