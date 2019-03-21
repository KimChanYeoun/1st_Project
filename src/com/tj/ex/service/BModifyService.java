package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.BoardDao;
public class BModifyService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			// mId, fTitle, fContent,  fileName, fIp
			int bNum = Integer.parseInt(request.getParameter("bNum"));
			String bTitle = request.getParameter("bTitle");
			String bContent = request.getParameter("bContent");
			String bIp = request.getRemoteAddr();
			BoardDao bDao = new BoardDao();
			int result = bDao.bmodify(bNum, bTitle, bContent, bIp);
			// joinMember결과에 따라 적절히 request.setAttribute
			if(result == BoardDao.SUCCESS) { 
				request.setAttribute("resultMsg", "글수정 성공");
			}else {
				request.setAttribute("resultMsg", "글수정 실패");
			}
			// pageNum이 request객체에서 온 것은 null값이라 request에 담아놓음
			String bpageNum = request.getParameter("bpageNum");
			request.setAttribute("bpageNum", bpageNum);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.setAttribute("resultMsg", "글쓰기 실패");
		}
	}
}