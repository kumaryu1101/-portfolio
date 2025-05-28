package model.dto;

import java.util.Date;

public class EventDTO {
	private int id; 			//イベントid
	private String name; //イベントの名前
	private Date date; 		//開催日
	private String organizerName; //オーガナイザー(開催者)
	private String comment; 		//コメント
	
	//コンストラクタ
	public EventDTO() {}
	
	public EventDTO(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public EventDTO(String name, Date date, String organizerName, String comment) {
		this.name = name;
		this.date = date;
		this.organizerName = organizerName;
		this.comment = comment;
	}
	
	//ゲッター　セッター
	public int getId() { return id; }		//ID
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }		//イベント名
    public void setName(String name) { this.name = name; }

    public Date getDate() { return date; }		//date(日付
    public void setDate(Date date) { this.date = date; }

    public String getOrganizerName() { return organizerName; }	//オーガナイザー
    public void setOrganizerName(String organizerName) { this.organizerName = organizerName; }

    public String getComment() { return comment; }	//comment
    public void setComment(String comment) { this.comment = comment; }
    
}
	


