package test2.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import test2.model.ScPlayers;
import test2.repository.ScPlayersRepository;


public class ScPlayersListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String teamName = request.getParameter("teamName");
		
		System.out.println(teamName);
		
		ScPlayersRepository scPlayersRepository = 
				ScPlayersRepository.getInstance();
		List<ScPlayers> scPlayers = scPlayersRepository.findPlayers(teamName);
		
		Gson gson = new Gson();
		String scPlayersJson = gson.toJson(scPlayers);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json); charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		pw.println(scPlayersJson);
	}

}
