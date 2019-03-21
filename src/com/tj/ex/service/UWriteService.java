package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.NoticeDao;
import com.tj.ex.dao.UpgradeDao;
public class UWriteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession httpSession = request.getSession();
			String aId = (String)httpSession.getAttribute("aId");
			String uTitle = request.getParameter("uTitle");
			String uContent = request.getParameter("uContent");
			UpgradeDao upgradeDao = new UpgradeDao();
			int result = upgradeDao.uWrite(aId, uTitle, uContent);
			// joinMember결과에 따라 적절히 request.setAttribute
			if(result == NoticeDao.SUCCESS) { // 회원가입 진행
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
