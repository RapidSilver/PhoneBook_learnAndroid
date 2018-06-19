package ru.android.kira.phonebook;


import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;


public class MyAdapter extends BaseAdapter {

    Context cont;
    LayoutInflater inflater;
    ArrayList<Contact> objects;

    MyAdapter(Context context, ArrayList<Contact> contacts){
        cont = context;
        objects = contacts;
        inflater = (LayoutInflater) cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    Contact getContact(int position){
        return ((Contact)getItem(position));
    }



    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = inflater.inflate(R.layout.card, parent, false);
        }
        Contact c = getContact(position);

        ((TextView) view.findViewById(R.id.name)).setText(c.name);
        ((TextView) view.findViewById(R.id.family)).setText(c.family);
        ((TextView) view.findViewById(R.id.phone)).setText(c.phone);
        if (c.photo!=null){
            ((ImageView) view.findViewById(R.id.photo)).setImageURI(Uri.parse(c.photo));
        }


        return view;
    }
}
