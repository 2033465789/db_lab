
import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StudentEdit2
 */
@WebServlet("/StudentEdit2")
public class TestServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 13231243251L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Student student = new Student();
		String Sno = request.getParameter("Sno");
		String Sname = request.getParameter("Sname");
		String Ssex = request.getParameter("Ssex");
		int Sage = Integer.parseInt(request.getParameter("Sage"));
		String Sdept = request.getParameter("Sdept");
		HttpSession session = request.getSession();
		if (true) {
			response.sendRedirect(
					request.getContextPath() + File.separatorChar + "show.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + File.pathSeparator
					+ "addfail.jsp");
		}

	}

}
