package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.UpgradeDao;
import com.tj.ex.dto.UpgradeDto;
public class UContentService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String upageNum = request.getParameter("npageNum");
		if(upageNum==null || upageNum.equals("")){
			upageNum = "1";
		}
		int uNum = Integer.parseInt(request.getParameter("uNum"));
		UpgradeDao upgradeDao = new UpgradeDao();
		UpgradeDto dto = upgradeDao.uContentView(uNum);
		request.setAttribute("ucontent_view", dto);
		request.setAttribute("upageNum", upageNum);
	}

}
