package leitorrss.cnbatalha.leitorrss.model;

public class Item {

	public String title;
	public String link;
	public String pubDate;
	public String description;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		
		while ( title.contains("/t") )
			title.replace("/t", "");
		
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.title;
	}

}
