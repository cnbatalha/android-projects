package leitorrss.cnbatalha.leitorrss.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

import leitorrss.cnbatalha.leitorrss.R;
import leitorrss.cnbatalha.leitorrss.model.Item;

public class AdapterListView extends BaseAdapter {

	private List<Item> Items;
	private LayoutInflater mInflater;
	private HoldListViewItem listViewItem;

    public static class HoldListViewItem {

        private TextView tvDescricao;
        private TextView tvData;

        public TextView getTvData() {
            return tvData;
        }

        public void setTvData(TextView tvData) {
            this.tvData = tvData;
        }

        public TextView getTvDescricao() {
            return tvDescricao;
        }

        public void setTvDescricao(TextView tvDescricao) {
            this.tvDescricao = tvDescricao;
        }
    }


    public void add(Item item)
	{
		Items.add(item);
	}

	public AdapterListView(Context context, List<Item> items) {
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
			convertView = mInflater.inflate(R.layout.list_news, null);

			// cria um item de suporte para n�o precisarmos sempre
			// inflar as mesmas informacoes
			listViewItem = new HoldListViewItem();
			listViewItem.setTvDescricao( ((TextView) convertView.findViewById(R.id.tvDescricao)) );
			listViewItem.setTvData( ((TextView) convertView.findViewById(R.id.tvData)) );
			// listViewItem.imgIcon = ((ImageView)
			// view.findViewById(R.id.imagemview));
            // TODO: review layout
			txtView = (TextView) convertView.findViewById(R.id.action_settings);
			// define os itens na view;
			convertView.setTag(listViewItem);
		} else { // se a view j� existe pega os itens.
            listViewItem = (HoldListViewItem) convertView.getTag();
		}

		Item itm = (Item) Items.get(position);
		// txtView.setText(itm.title);

        listViewItem.getTvDescricao().setText(itm.getTitle());
        listViewItem.getTvData().setText(itm.getPubDate());
		return convertView;
	}
}
