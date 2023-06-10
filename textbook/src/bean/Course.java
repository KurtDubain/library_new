package bean;


public class Course {

  private long id;
  private String coursename;
  private long teacherid;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getCoursename() {
    return coursename;
  }

  public void setCoursename(String coursename) {
    this.coursename = coursename;
  }


  public long getTeacherid() {
    return teacherid;
  }

  public void setTeacherid(long teacherid) {
    this.teacherid = teacherid;
  }

}
