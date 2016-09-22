package com.example.carlos.agenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.agenda.model.Contact;

import java.util.List;

/**
 * Created by Carlos on 15/09/2014.
 */
public class AdapterContato extends BaseAdapter {

    private List<Contact> contacts;
    private LayoutInflater mInflater;

    public AdapterContato(Context context, List<Contact> items) {
        super();
        this.contacts = items;

        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return contacts.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return contacts.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // ItemSuporte itemHolder;
        TextView txtView;
        TextView txtNome;

        // se a view estiver nula (nunca criada), inflamos o layout nela.
        if (convertView == null) {
            // infla o layout para podermos pegar as views
            convertView = mInflater.inflate(R.layout.list_layout, null);
            // view.findViewById(R.id.imagemview));
            txtView = (TextView) convertView.findViewById(R.id.lvTxId);
            txtNome = (TextView) convertView.findViewById(R.id.lvTxName);

            // define os itens na view;
            convertView.setTag(txtView);
        } else { // se a view já existe pega os itens.
            txtView = (TextView) convertView.getTag();
            txtNome = (TextView) convertView.getTag();
        }

        Contact itm = (Contact) contacts.get(position);
        txtView.setText(itm.getId());
        txtNome.setText(itm.getNome());

        return convertView;
    }
}



