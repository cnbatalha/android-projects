package leitorrss.cnbatalha.leitorrss.database;

/**
 * Created by Carlos on 31/10/2014.
 */
public class RssDAO {

    final public static String TB_NAME = "RSS";

    // fields
    final public  static  String _ID = "ID";
    final public  static  String URL_END = "URL_END";
    final public  static  String DESCRICAO = "DESCRICAO";

    final static String[] columns = { _ID, URL_END, DESCRICAO };

    final public static String CREATE_CMD =
            "CREATE TABLE url_rss ("
                    + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + URL_END + " TEXT NOT NULL,"
                    + DESCRICAO + " TEXT NOT NULL)";

    private String id;
    private String enderecoURL;
    private String descricao;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnderecoURL() {
        return enderecoURL;
    }

    public void setEnderecoURL(String enderecoURL) {
        this.enderecoURL = enderecoURL;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
