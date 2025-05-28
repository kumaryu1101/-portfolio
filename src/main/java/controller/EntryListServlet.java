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
 * Servlet implementation class EntryListServlet
 */
@WebServlet("/EntryListServlet")
public class EntryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public EntryListServlet() {
        
    }

	//イベントエントリー>>イベント一覧の時に表示される
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

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/Event/EventList.jsp");//jspに画面遷移
		dispatcher.forward(request, response);

	}

	
	//イベントエントリー>>イベント一覧>> エントリーリストを見る
	// エントリーリスト表示
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			request.setCharacterEncoding("UTF-8");

			// パラメータ取得
			//イベントIDからイベント名を取得
			String eventId = request.getParameter("eventId");
			String eventName = null;
			try {
				eventName = new EventDAO().findById(Integer.parseInt(eventId)).getName();
			} catch (Exception e) {
				e.printStackTrace();
			}

			//エントリーリスト表示
			int eventid = Integer.parseInt(eventId);
			EventDTO eventdto = new EventDTO(eventid, eventName);
			EntryListDAO entrylist = new EntryListDAO();
			List<EntryListDTO> list = new ArrayList<>();//listにエントリーリストをDBからもらう
			

			try {
				list = entrylist.selectEntryList(eventdto);//DBからもらう
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

			request.setAttribute("list", list);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/Event/entrylist.jsp");
			dispatcher.forward(request, response);
		}
	}