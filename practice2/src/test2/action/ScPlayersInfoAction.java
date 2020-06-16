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

public class ScPlayersInfoAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String teamName = request.getParameter("teamName");
		int back_Num = Integer.parseInt(request.getParameter("back_num"));
		
		System.out.println(teamName);
		System.out.println(back_Num);
		
		ScPlayersRepository scPlayersRepository = 
					ScPlayersRepository.getInstance();
		List<ScPlayers> scPlayerInfo = scPlayersRepository.findPlayerInfo(teamName, back_Num);
		
		Gson gson = new Gson();
		String scPlayersJson = gson.toJson(scPlayerInfo);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json); charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		pw.println(scPlayersJson);
	}

}
