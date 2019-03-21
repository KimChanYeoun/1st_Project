package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.UpgradeDao;
public class UModifyService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			// mId, fTitle, fContent,  fileName, fIp
			int uNum = Integer.parseInt(request.getParameter("uNum"));
			String uTitle = request.getParameter("uTitle");
			String uContent = request.getParameter("uContent");
			UpgradeDao upgradeDao = new UpgradeDao();
			int result = upgradeDao.uModify(uNum, uTitle, uContent);
			// joinMember결과에 따라 적절히 request.setAttribute
			if(result == UpgradeDao.SUCCESS) { 
				request.setAttribute("resultMsg", "글수정 성공");
			}else {
				request.setAttribute("resultMsg", "글수정 실패");
			}
			// pageNum이 request객체에서 온 것은 null값이라 request에 담아놓음
			String upageNum = request.getParameter("upageNum");
			request.setAttribute("upageNum", upageNum);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.setAttribute("resultMsg", "글쓰기 실패");
		}
	}
}