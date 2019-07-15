package hrd;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/preg")
public class ProductRegSvr extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "물품 등록");
		request.setAttribute("view", "productReg.jsp");
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		final String p_name = request.getParameter("p_name");
		
		String sql = " INSERT INTO i_product (p_no, p_name) "
				+ " SELECT max(p_no) + 1, ? FROM i_product ";
		
		int result = DAO.executeUpdate(sql, new I_DAO() {
			@Override
			public void prepare(PreparedStatement ps, HashMap<String, Object> hm) {				
			}

			@Override
			public int prepare(PreparedStatement ps) throws Exception {			
				ps.setString(1, p_name);				
				return ps.executeUpdate();
			}			
		});
		
		
	}

}








