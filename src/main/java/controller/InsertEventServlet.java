package controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.dao.EntryListDAO;
import model.dao.EventDAO;
import model.dao.UsersDAO;
import model.dto.EntryListDTO;
import model.dto.EventDTO;
import model.dto.UsersDTO;

/**
 * Servlet implementation class InsertEventServlet
 */
@WebServlet("/InsertEventServlet")
public class InsertEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public InsertEventServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	//イベントにエントリー（参加）する
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
		//ユーザーIDからユーザーの名前、ダンサー名を取得
		String userId = request.getParameter("userId");
		String userName = null;
		String dancerName = null;
		try {
			UsersDAO userdao = new UsersDAO();
			userName = userdao.findByid(Integer.parseInt(userId)).getUserName();
			dancerName = userdao.findByid(Integer.parseInt(userId)).getDancerName();
		} catch (NumberFormatException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		

			//eventdtoとuserdtoを引数にし,entrylistdtoを生成し,イベントリストに追加する
		try {
			int eventid = Integer.parseInt(eventId);
			int userid = Integer.parseInt(userId);

			EventDTO eventdto = new EventDTO(eventid, eventName);
			UsersDTO userdto = new UsersDTO(userid, userName, dancerName);
			EntryListDAO entrylistdao = new EntryListDAO();
			
			//重複チェック(重複していた場合、trueが返す）
			boolean check = entrylistdao.checkEntryList(eventdto, userdto);
			if(check) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/checkErr.jsp");
				dispatcher.forward(request, response); //重複していたらERR画面へ
			}
			
			
			EntryListDTO entrydto = new EntryListDTO(eventdto, userdto);

			entrylistdao.insertEntry(entrydto); // イベント参加登録

			// 登録後、最新のエントリーリストを取得して表示
			request.setAttribute("list", entrylistdao.selectEntryList(eventdto));
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/Event/entrylist.jsp");
			dispatcher.forward(request, response);

		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}