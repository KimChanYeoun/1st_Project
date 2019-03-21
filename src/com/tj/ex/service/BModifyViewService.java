package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.BoardDao;
import com.tj.ex.dto.BoardDto;
public class BModifyViewService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int bNum = Integer.parseInt(request.getParameter("bNum"));
		BoardDao boardDao = new BoardDao();
		BoardDto dto = boardDao.bmodifyView_replyView(bNum);
		request.setAttribute("bmodify_view", dto);
	}
}
