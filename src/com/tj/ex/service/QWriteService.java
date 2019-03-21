package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.QnADao;
public class QWriteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession httpSession = request.getSession();
			String cId = (String)httpSession.getAttribute("cId");
			String qTitle = request.getParameter("qTitle");
			String qContent = request.getParameter("qContent");
			String qIp = request.getRemoteAddr();
			QnADao qDao = new QnADao();
			int result = qDao.qWrite(cId, qTitle, qContent, qIp);
			// joinMember결과에 따라 적절히 request.setAttribute
			if(result == QnADao.SUCCESS) { // 회원가입 진행
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
