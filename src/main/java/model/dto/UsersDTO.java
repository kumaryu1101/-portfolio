package model.dto;

public class UsersDTO {
	private int id; //userid
	private String username; //userの名前
	private String dancername; //イベントに参加する時の名前
	
	//コンストラクタ
	public UsersDTO() {}
	
	public UsersDTO(String username, String dancername) {
		this.username = username;
		this.dancername = dancername;
	}
	public UsersDTO(int id,String username, String dancername) {
		this.id = id;
		this.username = username;
		this.dancername = dancername;
	}
	
	// ゲッター・セッター
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUserName() { return username; }
    public void setUserName(String username) { this.username = username; }
    public String getDancerName() { return dancername; }
    public void setDancerName(String dancerName) { this.dancername = dancerName; }
    
}
	
	


