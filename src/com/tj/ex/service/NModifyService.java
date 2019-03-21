package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.NoticeDao;
public class NModifyService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			// mId, fTitle, fContent,  fileName, fIp
			int nNum = Integer.parseInt(request.getParameter("nNum"));
			String nTitle = request.getParameter("nTitle");
			String nContent = request.getParameter("nContent");
			NoticeDao nDao = new NoticeDao();
			int result = nDao.nModify(nNum, nTitle, nContent);
			// joinMember결과에 따라 적절히 request.setAttribute
			if(result == NoticeDao.SUCCESS) { 
				request.setAttribute("resultMsg", "글수정 성공");
			}else {
				request.setAttribute("resultMsg", "글수정 실패");
			}
			// pageNum이 request객체에서 온 것은 null값이라 request에 담아놓음
			String npageNum = request.getParameter("npageNum");
			request.setAttribute("npageNum", npageNum);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.setAttribute("resultMsg", "글쓰기 실패");
		}
	}
}