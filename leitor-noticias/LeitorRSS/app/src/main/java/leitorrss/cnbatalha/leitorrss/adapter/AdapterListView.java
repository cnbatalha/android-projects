package leitorrss.cnbatalha.leitorrss.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.leitorrss.cnbatalha.R;
import com.leitorrss.model.cnbatalha.item;

import java.util.List;

public class AdapterListView extends BaseAdapter {

	private List<item> Items;
	private LayoutInflater mInflater;

	public AdapterListView(Context context, List<item> items) {
		super();
		this.Items = items;

		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Items.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return Items.get(arg0);
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

		// se a view estiver nula (nunca criada), inflamos o layout nela.
		if (convertView == null) {
			// infla o layout para podermos pegar as views
			convertView = mInflater.inflate(R.layout.item_listview, null);
			// cria um item de suporte para n�o precisarmos sempre
			// inflar as mesmas informacoes
			// itemHolder = new ItemSuporte();
			// itemHolder.txtTitle = ((TextView) view.findViewById(R.id.text));
			// itemHolder.imgIcon = ((ImageView)
			// view.findViewById(R.id.imagemview));
			txtView = (TextView) convertView.findViewById(R.id.txtItem);
			// define os itens na view;
			convertView.setTag(txtView);
		} else { // se a view j� existe pega os itens.
			txtView = (TextView) convertView.getTag();
		}

		item itm = (item) Items.get(position);
		txtView.setText(itm.title);
		
		return convertView;
	}
}
