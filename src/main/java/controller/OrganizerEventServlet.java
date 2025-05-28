package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.dao.EntryListDAO;
import model.dao.EventDAO;
import model.dto.EntryListDTO;
import model.dto.EventDTO;

/**
 * Servlet implementation class OrganizerEventServlet
 */
@WebServlet("/OrganizerEventServlet")
public class OrganizerEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public OrganizerEventServlet() {
        super();
       
    }
    //イベント一覧
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EventDAO eventdao = new EventDAO();
		ArrayList<EventDTO> eventlist = null;//このentrylistにDBからもらった配列を代入
		try {
			eventlist = eventdao.selectEvent();
			if(eventlist == null) {
				eventlist = new ArrayList<>();//もしエントリーリストがnullだった場合
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("eventlist", eventlist);

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/Organizer/OrganizerEventList.jsp");//jspに画面遷移
		dispatcher.forward(request, response);

	
	}

	//イベントの結果（参加者のスコア）を表示する
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// パラメータ取得
		//イベントIDからイベント名を取得
		String eventId = request.getParameter("eventId");
		String eventName = null;
		

		//エントリーリスト表示
		int eventid = Integer.parseInt(eventId);
		EntryListDAO entrylist = new EntryListDAO();
		List<EntryListDTO> list = new ArrayList<>();//listにエントリーリストをDBからもらう

		try {
			list = entrylist.resultEntryList(eventid);//DBからもらう
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("list", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/Organizer/OrganizerResult.jsp");
		dispatcher.forward(request, response);

	}

}
