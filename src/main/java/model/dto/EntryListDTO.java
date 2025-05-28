package model.dto;

public class EntryListDTO {

	private EventDTO event; //イベントのid
	private UsersDTO user; //userのid
	private int points = 0; //参加者のポイント 初期値０
	
	//コンストラクタ
	public EntryListDTO() {}
	
	public EntryListDTO(EventDTO event, UsersDTO user) {
		this.event = event;
		this.user = user;
	}
	public EntryListDTO(EventDTO event, UsersDTO user, int points) {
		this.event = event;
		this.user = user;
		this.points = points;
	}
	
	//ゲッター　セッター
	
	public EventDTO getEvent() { return event; }
    public void setEvent(EventDTO event) { this.event = event; }

    public UsersDTO getUser() { return user; }
    public void setUser(UsersDTO user) { this.user = user; }

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }

}
