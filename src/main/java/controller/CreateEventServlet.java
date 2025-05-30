package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.dao.EventDAO;
import model.dto.EventDTO;

/**
 * Servlet implementation class CreateEvent
 */
@WebServlet("/CreateEventServlet")
public class CreateEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateEventServlet() {
        super();
    }


    //organizerのトップ画面
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/Organizer/CreateEvent.jsp");
		dispatcher.forward(request, response);
	}
    
    //イベントを作成する
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		
		String name = request.getParameter("eventName");
		String date = request.getParameter("eventDate");
		String organizer = request.getParameter("organizerName");
		String comment = request.getParameter("comment");
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = f.parse(date);
			EventDTO eventdto = new EventDTO(name,d,organizer,comment);
			EventDAO eventdao = new EventDAO();
			try {
				eventdao.insertEvent(eventdto);
				System.out.println("成功");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/BackHome.jsp");
		dispatcher.forward(request, response);
	}
}
