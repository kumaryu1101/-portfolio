package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.dao.EntryListDAO;
import model.dao.EventDAO;
import model.dao.UsersDAO;
import model.dto.EntryListDTO;
import model.dto.EventDTO;
import model.dto.UsersDTO;


@WebServlet("/AddPointServlet")
public class AddPointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddPointServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String point = request.getParameter("point");
		String userId = request.getParameter("userId");
		HttpSession session = request.getSession();
		EventDTO event =(EventDTO)session.getAttribute("event");
		
		EntryListDAO entrylistdao = new EntryListDAO();
		
		//ポイントを追加
		try {
			UsersDAO usersdao = new UsersDAO();
			UsersDTO usersdto = usersdao.findByid(Integer.parseInt(userId));
			EntryListDTO entrylist = new EntryListDTO(event,usersdto,Integer.parseInt(point));
			entrylistdao.updatePoints(entrylist);
		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		session.removeAttribute("event");//セッションスコープ削除
		
		
		//イベント一覧に戻る
		EventDAO eventdao = new EventDAO();
  		ArrayList<EventDTO> eventlist = null;//このentrylistにDBからもらった配列を代入
  		try {
  			eventlist = eventdao.selectEvent();
  			if(eventlist == null) {
  				eventlist = new ArrayList<>();//もしイベントリストがnullだった場合
  			}
  		} catch (ClassNotFoundException | SQLException e) {
  			e.printStackTrace();
  		}
  		request.setAttribute("eventlist", eventlist);

  		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/Judge/JudgeScreen.jsp");//jspに画面遷移
  		dispatcher.forward(request, response);
	}

}
