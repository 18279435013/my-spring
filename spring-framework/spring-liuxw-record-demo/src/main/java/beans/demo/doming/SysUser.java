package beans.demo.doming;



public class SysUser {
	private Integer id;

	private String niceName;


	public void init(){
		this.id = 2;
		System.out.println(this.id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNiceName() {
		return niceName;
	}

	public void setNiceName(String niceName) {
		this.niceName = niceName;
	}
}
