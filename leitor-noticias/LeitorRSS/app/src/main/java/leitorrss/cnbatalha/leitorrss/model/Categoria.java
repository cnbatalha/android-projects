package leitorrss.cnbatalha.leitorrss.model;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "categoria")
public class Categoria extends Model {

	public Categoria() {

			}

	public Categoria(String titulo, String url) {

		this.titulo = titulo;
		this.url = url;
	}


    @Column(name = "titulo")
    public String titulo;
    @Column(name = "url")
	public String url;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.titulo;
	}
	// protected Encoding encoding = Encoding.GetEncoding("iso-8859-1");

	/*
	 * public Encoding Encoding { get { return encoding; } set { encoding =
	 * value; } }
	 */

}
