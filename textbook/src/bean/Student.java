package bean;


public class Student {

  private long id;
  private String password;
  private String name;
  private String phone;
  private long majorid;
  private String idcard;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public long getMajorid() {
    return majorid;
  }

  public void setMajorid(long majorid) {
    this.majorid = majorid;
  }

  public String getIdcard() {
    return idcard;
  }

  public void setIdcard(String idcard) {
    this.idcard = idcard;
  }

}
