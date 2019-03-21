package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.UpgradeDao;
import com.tj.ex.dto.UpgradeDto;
public class UModifyViewService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int uNum = Integer.parseInt(request.getParameter("uNum"));
		UpgradeDao upgradeDao = new UpgradeDao();
		UpgradeDto dto = upgradeDao.uModifyView_replyView(uNum);
		request.setAttribute("umodify_view", dto);
	}
}
