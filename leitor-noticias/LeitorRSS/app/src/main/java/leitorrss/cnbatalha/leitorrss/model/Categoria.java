package leitorrss.cnbatalha.leitorrss.model;

public class Categoria {

	public Categoria() {
			}

	public Categoria(String titulo, String url) {

		super();
		this.titulo = titulo;
		this.url = url;
	}

	public String titulo;

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
