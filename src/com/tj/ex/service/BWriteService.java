package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.BoardDao;
public class BWriteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession httpSession = request.getSession();
			String cId = (String)httpSession.getAttribute("cId");
			String bTitle = request.getParameter("bTitle");
			String bContent = request.getParameter("bContent");
			String bIp = request.getRemoteAddr();
			BoardDao bDao = new BoardDao();
			int result = bDao.bwrite(cId, bTitle, bContent, bIp);
			// joinMember결과에 따라 적절히 request.setAttribute
			if(result == BoardDao.SUCCESS) { // 회원가입 진행
				request.setAttribute("resultMsg", "글쓰기 성공");
			}else {
				request.setAttribute("resultMsg", "글쓰기 실패");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.setAttribute("resultMsg", "글쓰기 실패");
		}
	}
}
