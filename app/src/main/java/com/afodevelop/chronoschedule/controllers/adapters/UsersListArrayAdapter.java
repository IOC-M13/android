package com.afodevelop.chronoschedule.controllers.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.afodevelop.chronoschedule.R;
import com.afodevelop.chronoschedule.controllers.fragments.UsersFragment;

import java.util.ArrayList;

/**
 * Created by alex on 10/03/16.
 */
public class UsersListArrayAdapter extends ArrayAdapter<String> {

    // CLASS-WIDE VARIABLES
    Context context;
    UsersFragment parentFragment;
    int layoutResourceId;
    String user;
    ArrayList<String> data = new ArrayList<String>();

    // CONSTRUCTOR
    public UsersListArrayAdapter(Context context, int layoutResourceId,
                             ArrayList<String> data, UsersFragment parentFragment) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        this.parentFragment = parentFragment;
    }

    // LOGIC
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View row = convertView;
        UserHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new UserHolder();
            holder.btnDelete = (ImageButton) row.findViewById(R.id.users_shift_deleteitem);
            holder.btnEdit = (ImageButton) row.findViewById(R.id.users_shift_edititem);
            holder.itemName = (TextView) row.findViewById(R.id.users_shift_itemname);
            row.setTag(holder);
        } else {
            holder = (UserHolder) row.getTag();
        }

        user = data.get(position);
        holder.itemName.setText(user);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.editUser(user);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.deleteUser(user);
            }
        });

        return row;
    }

    static class UserHolder {
        ImageButton btnDelete;
        ImageButton btnEdit;
        TextView itemName;
    }
}
