package test2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test2.action.Action;
import test2.action.ScPlayersHomeAction;
import test2.action.ScPlayersInfoAction;
import test2.action.ScPlayersListAction;

@WebServlet("/scplayers")
public class ScPlayersController extends HttpServlet {
	private final static String TAG = "ScPlayersController : ";
	private static final long serialVersionUID = 1L;
	
    public ScPlayersController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		System.out.println(TAG + "doProcess : " + cmd);
		Action action = router(cmd);
		System.out.println(action);
		action.execute(request, response);
		
	}
	
	public Action router(String cmd) {
		if(cmd.equals("home")) {
			return new ScPlayersHomeAction();
		} else if(cmd.equals("playerList")) {
			return new ScPlayersListAction();
		} else if(cmd.equals("playerInfo")) {
			return new ScPlayersInfoAction();
		}
		return null;
	}
}
