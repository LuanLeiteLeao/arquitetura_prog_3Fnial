package view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;
import controle.ICrudControler;

@WebServlet("/api")
public class ViewServlet extends HttpServlet {
	private Views view;
	public ViewServlet() {
		super();
		this.view = new Views();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String model = request.getParameter("model");
		System.out.println(model);

		 ICrudControler controle =  (ICrudControler) view.getCasoDeUso(model);
		
		try {
			PrintWriter out = response.getWriter();
			String employeeJsonString = new Gson().toJson(controle.listar());

			System.out.println(employeeJsonString);
			out.println(employeeJsonString.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		out.println("{}");

	}
}
