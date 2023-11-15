package com.example.myapplication.bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Contact> contactlist;
    public ContactAdapter(Context context, int layout, ArrayList<Contact> contactlist){
        this.contactlist = contactlist;
        this.context = context;
        this.layout = layout;
    }
    @Override
    public int getCount() {
        return contactlist.size();
    }

    @Override
    public Object getItem(int position) {
        return contactlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(layout, null);

            viewHolder.tvId = (TextView) view.findViewById(R.id.tvId);
            viewHolder.tvName = (TextView) view.findViewById(R.id.tvName);
            viewHolder.tvGender = (TextView) view.findViewById(R.id.tvGender);
            viewHolder.tvAddress = (TextView) view.findViewById(R.id.tvAddress);
            viewHolder.tvEmail = (TextView) view.findViewById(R.id.tvEmail);
            viewHolder.tvMobile = (TextView) view.findViewById(R.id.tvMobile);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        Contact contact = contactlist.get(position);
        viewHolder.tvId.setText(contact.getId());
        viewHolder.tvName.setText(contact.getName());
        viewHolder.tvGender.setText(contact.getGender());
        viewHolder.tvAddress.setText(contact.getAddress());
        viewHolder.tvEmail.setText(contact.getEmail());
        viewHolder.tvMobile.setText(contact.getMobile());

        return view;
    }

    private class ViewHolder{
        TextView tvId, tvName, tvEmail, tvGender, tvAddress, tvMobile, tvHome, tvOffice;
    }
}
