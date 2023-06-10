package bean;


import java.sql.Timestamp;

public class Aftermarket {

  private String id;
  private long studentid;
  private long bookid;
  private String remarks;
  private java.sql.Timestamp pulltime;
  private String ornot;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public long getStudentid() {
    return studentid;
  }

  public void setStudentid(long studentid) {
    this.studentid = studentid;
  }

  public long getBookid() {
    return bookid;
  }

  public void setBookid(long bookid) {
    this.bookid = bookid;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public Timestamp getPulltime() {
    return pulltime;
  }

  public void setPulltime(java.sql.Timestamp pulltime) {
    this.pulltime = pulltime;
  }

  public String getOrNot() {
    return ornot;
  }

  public void setOrNot(String ornot) {
    this.ornot = ornot;
  }
}
