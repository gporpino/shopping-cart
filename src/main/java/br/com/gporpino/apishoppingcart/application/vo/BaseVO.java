package br.com.gporpino.apishoppingcart.application.vo;

public abstract class BaseVO {

  protected Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    BaseVO other = (BaseVO) obj;
    if (id != other.getId())
      return false;
    return true;
  }

}
