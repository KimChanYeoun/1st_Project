package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.CustomerDao;
import com.tj.ex.dto.CustomerDto;

public class ACusViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession httpSession = request.getSession();
		String cId = request.getParameter("cId");
		CustomerDao customerDao = new CustomerDao();
		CustomerDto dto = customerDao.getCustomer(cId);
		httpSession.setAttribute("content_view", dto);
	}

}

