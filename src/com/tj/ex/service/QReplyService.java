package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.QnADao;
public class QReplyService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			// mId, fTitle, fContent,  fileName, fIp
			HttpSession httpSession = request.getSession();
			String aId = (String)httpSession.getAttribute("aId");
			String qTitle = request.getParameter("qTitle");
			String qContent = request.getParameter("qContent");
			String qIp = request.getRemoteAddr();
			int qGroup = Integer.parseInt(request.getParameter("qGroup"));
			int qStep = Integer.parseInt(request.getParameter("qStep"));
			int qIndent = Integer.parseInt(request.getParameter("qIndent"));
			QnADao qnADao = new QnADao();
			int result = qnADao.qReply(aId, qTitle, qContent, qGroup, qStep, qIndent, qIp);
			// joinMember결과에 따라 적절히 request.setAttribute
			if(result == QnADao.SUCCESS) { // 회원가입 진행
				request.setAttribute("resultMsg", "답글쓰기 성공");
			}else {
				request.setAttribute("resultMsg", "답글쓰기 실패");
			}
			// pageNum이 request객체에서 온 것은 null값이라 request에 담아놓음
			String qpageNum = request.getParameter("qpageNum");
			request.setAttribute("qpageNum", qpageNum);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.setAttribute("resultMsg", "답글쓰기 실패");
		}
	}
}