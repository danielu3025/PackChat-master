package shenkar.koruApps.PackChetApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        groupsList = (ListView) findViewById(R.id.groupsList);
        groups = new ArrayList<>();
        dbRef = database.getReference().child("Courses");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                groups.removeAll(groups);
                for (DataSnapshot item : dataSnapshot.getChildren()){
                    groups.add(item.getKey().toString());
                }
                updateList();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        final Intent intent = new Intent(this,Messeges.class);

        dbRef.getRef().child("Course 4").getRef()
                .push()
                .setValue(new ChatMessage("new Group",
                        "test",
                        "test")
                );

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
}
