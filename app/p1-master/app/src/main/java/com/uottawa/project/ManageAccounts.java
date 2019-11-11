package com.uottawa.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ManageAccounts extends AppCompatActivity {

    private RecyclerView view;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_accounts);

        view = (RecyclerView) findViewById((R.id.accounts));

        layout = new LinearLayoutManager(this);
        view.setLayoutManager(layout);

        //for testing
        ArrayList<Account> testAccounts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Account newP = new Patient("p"+i, "p"+i, "FirstP"+i,"LastP"+i, "p"+i+"@email.com");
            Account newE = new Employee("e"+i, "e"+i, "FirstE"+i,"LastE"+i, "e"+i+"@email.com");
            testAccounts.add(newP);
            testAccounts.add(newE);
        }
        //end of for testing

        adapter = new AccountAdapter(testAccounts);
        view.setAdapter(adapter);
    }

    public void onDone(View view) {
        Intent returnIntent = new Intent();

        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
