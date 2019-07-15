package hrd;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pim")
public class ProductImportSvr extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql = " SELECT a.i_no, B.P_name, a.i_cnt, a.i_date "
				+ " FROM i_import A "
				+ " INNER JOIN i_product B "
				+ " ON A.p_no = B.p_no ";
		
		Object list = DAO.executeQuery(sql, 
				new I_DAO() {
					@Override
					public void prepare(PreparedStatement ps, HashMap<String, Object> hm) throws Exception {
						List<ProductVo> list = new ArrayList();
						
						ResultSet rs = ps.executeQuery();
						while(rs.next()) {
							ProductVo vo = new ProductVo();
							vo.setI_no(rs.getInt("i_no"));
							vo.setP_name(rs.getString("p_name"));
							vo.setI_cnt(rs.getInt("i_cnt"));
							vo.setI_date(rs.getString("i_date"));
							list.add(vo);
						}
						hm.put("rs", rs);
						hm.put("data", list);
					}

					@Override
					public int prepare(PreparedStatement ps) throws Exception {						
						return 0;
					}			
		});
		
		
		request.setAttribute("list", list);
		request.setAttribute("title", "입고 조회");
		request.setAttribute("view", "productIm.jsp");
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
