package shenkar.koruApps.PackChetApp;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    private FirebaseListAdapter<ChatMessage> adapter;
    private ListView listView;
    private EditText input;
    private FloatingActionButton fab;
    private String loggedInUserName = "";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbRef ;


    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_chat, container, false);
        Button currButton = (Button) view.findViewById(R.id.chatsBt);
        listView = (ListView) view.findViewById(R.id.list);
        fab = (FloatingActionButton)  view.findViewById(R.id.fab);
        input = (EditText) view.findViewById(R.id.input);
        EditText name = (EditText) view.findViewById(R.id.groupName);
        dbRef = database.getReference().child("Courses");
        dbRef = dbRef.child("Java");
        return view;
    }
    
}
