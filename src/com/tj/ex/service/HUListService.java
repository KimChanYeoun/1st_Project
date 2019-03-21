package com.tj.ex.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.UpgradeDao;
import com.tj.ex.dto.UpgradeDto;

public class HUListService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String upageNum = request.getParameter("upageNum");
		String requestPageNum = (String) request.getAttribute("upageNum");
		if (upageNum == null) {
			if (requestPageNum == null)
				upageNum = "1";
			else
				upageNum = requestPageNum;
		}
		int startRow = 1;
		int endRow = 5;
		UpgradeDao upgradeDao = new UpgradeDao();
		ArrayList<UpgradeDto> ulist = upgradeDao.ulist(startRow, endRow);
		request.setAttribute("ulist", ulist);
	}
}