package leitorrss.cnbatalha.leitorrss.adapter;

import android.widget.TextView;

/**
 * Created by carlos on 30/10/16.
 */

public class ListViewItem {

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
