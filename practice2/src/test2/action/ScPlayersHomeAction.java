package test2.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test2.model.ScTeams;
import test2.repository.ScTeamsRepository;

public class ScPlayersHomeAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ScTeamsRepository scTeamsRepository = 
				ScTeamsRepository.getInstance();
		List<ScTeams> scTeams = scTeamsRepository.findTeam();
		System.out.println(scTeams);
		
		request.setAttribute("scTeams", scTeams);
		
		RequestDispatcher dis = request.getRequestDispatcher("home.jsp");
		dis.forward(request, response);
	}
}
