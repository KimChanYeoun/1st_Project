package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.QnADao;
public class QModifyService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			// mId, fTitle, fContent,  fileName, fIp
			int qNum = Integer.parseInt(request.getParameter("qNum"));
			String qTitle = request.getParameter("qTitle");
			String qContent = request.getParameter("qContent");
			String qIp = request.getRemoteAddr();
			QnADao qDao = new QnADao();
			int result = qDao.qModify(qNum, qTitle, qContent, qIp);
			// joinMember결과에 따라 적절히 request.setAttribute
			if(result == QnADao.SUCCESS) { 
				request.setAttribute("resultMsg", "글수정 성공");
			}else {
				request.setAttribute("resultMsg", "글수정 실패");
			}
			// pageNum이 request객체에서 온 것은 null값이라 request에 담아놓음
			String qpageNum = request.getParameter("qpageNum");
			request.setAttribute("qpageNum", qpageNum);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.setAttribute("resultMsg", "글쓰기 실패");
		}
	}
}