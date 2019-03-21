package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.BoardDao;
public class BReplyService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			// mId, fTitle, fContent,  fileName, fIp
			HttpSession httpSession = request.getSession();
			String cId = (String)httpSession.getAttribute("cId");
			String bTitle = request.getParameter("bTitle");
			String bContent = request.getParameter("bContent");
			String bIp = request.getRemoteAddr();
			int bGroup = Integer.parseInt(request.getParameter("bGroup"));
			int bStep = Integer.parseInt(request.getParameter("bStep"));
			int bIndent = Integer.parseInt(request.getParameter("bIndent"));
			BoardDao bDao = new BoardDao();
			int result = bDao.breply(cId, bTitle, bContent, bIp, bGroup, bStep, bIndent);
			// joinMember결과에 따라 적절히 request.setAttribute
			if(result == BoardDao.SUCCESS) { // 회원가입 진행
				request.setAttribute("resultMsg", "답글쓰기 성공");
			}else {
				request.setAttribute("resultMsg", "답글쓰기 실패");
			}
			// pageNum이 request객체에서 온 것은 null값이라 request에 담아놓음
			String bpageNum = request.getParameter("bpageNum");
			request.setAttribute("bpageNum", bpageNum);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.setAttribute("resultMsg", "답글쓰기 실패");
		}
	}
}