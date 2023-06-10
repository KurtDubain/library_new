package bean;


public class Bookmarket {

  private long id;
  private String categoryid;
  private String bookname;
  private long hits;
  private String cover;
  private double price;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getCategoryid() {
    return categoryid;
  }

  public void setCategoryid(String categoryid) {
    this.categoryid = categoryid;
  }


  public String getBookname() {
    return bookname;
  }

  public void setBookname(String bookname) {
    this.bookname = bookname;
  }


  public long getHits() {
    return hits;
  }

  public void setHits(long hits) {
    this.hits = hits;
  }


  public String getCover() {
    return cover;
  }

  public void setCover(String cover) {
    this.cover = cover;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

}
