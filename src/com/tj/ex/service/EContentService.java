package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.ErrorboardDao;
import com.tj.ex.dto.ErrorboardDto;
public class EContentService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int eNum = Integer.parseInt(request.getParameter("eNum"));
		ErrorboardDao eDao = new ErrorboardDao();
		ErrorboardDto dto = eDao.eContentView(eNum);
		request.setAttribute("econtent_view", dto);
	}

}
