package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.CustomerDao;

public class CIdConfirmService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String cId = request.getParameter("cId");
		CustomerDao customerDao = new CustomerDao();
		int result = customerDao.cIdConfirm(cId);
		if (result == CustomerDao.EXISTENT) {
			request.setAttribute("idConfirmMsg", "중복된 ID입니다");
		} else {
			request.setAttribute("idConfirmMsg", "사용 가능한 ID입니다");
		}
	}
}
