package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.UpgradeDao;
public class UDeleteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int uNum = Integer.parseInt(request.getParameter("uNum"));
		UpgradeDao upgradeDao = new UpgradeDao();
		int result = upgradeDao.uDelete(uNum);
		if(result == UpgradeDao.SUCCESS) {
			request.setAttribute("resultMsg", "글삭제 성공");
		}else {
			request.setAttribute("resultMsg", "글삭제 실패");
		}
	}
}
