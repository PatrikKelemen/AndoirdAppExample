package com.uottawa.project;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {

    private ArrayList<Account> accounts;

    public static class AccountViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public AccountViewHolder(TextView v) {
            super(v);
            textView = v;
        }

    }

    public AccountAdapter(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public AccountAdapter.AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.accounts_text_view, parent, false);
        AccountViewHolder vh = new AccountViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(AccountViewHolder holder, int position) {
        holder.textView.setText(this.accounts.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return this.accounts.size();
    }
}
