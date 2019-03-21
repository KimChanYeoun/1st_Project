package com.tj.ex.service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.ex.dao.CustomerDao;
import com.tj.ex.dto.CustomerDto;
public class CModifyService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("mPhotoUp");
		int maxSize = 1024*1024*5; // 최대업로드 사이즈는 5M
		MultipartRequest cRequest = null;
		String cPhoto = "";
		try {
			cRequest = new MultipartRequest(request, path, maxSize, 
									"utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = cRequest.getFileNames();
			String param = params.nextElement();
			cPhoto = cRequest.getFilesystemName(param);
			// mId, mPw,  mName, mEmail, mPhoto, mBirth, mAddress 파라미터 값 받아
			String cId = cRequest.getParameter("cId");
			String cPw = cRequest.getParameter("cPw");
			String cName = cRequest.getParameter("cName");
			String cEmail = cRequest.getParameter("cEmail");
			String cTel = cRequest.getParameter("cTel");
			String cGender = cRequest.getParameter("cGender");
			Date cBirth = null;
			String cBirthStr = cRequest.getParameter("cBirth");
			if(!cBirthStr.equals("")) {
				cBirth = Date.valueOf(cBirthStr);
			}
			String cAddr = cRequest.getParameter("cAddr");
			if(cPhoto==null) { // 사진은 원래 db에 있는 걸로 해
				String dbmPhoto = cRequest.getParameter("dbmPhoto");
				cPhoto = dbmPhoto;
			}
			// dto객체로 만들고, dao의 joinMember(dto)호출
			CustomerDto dto = new CustomerDto(cId, cPw, cName, cPhoto, cBirth, cTel, cEmail, cGender, cAddr, null, 0);
			CustomerDao mDao = new CustomerDao();
			int result = mDao.modifyCustomer(dto);
			if(result == CustomerDao.SUCCESS) {
				// 회원수정 성공시 세션 갈아엎기, modifyResultMsg 추가하기
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("customer", dto);
				request.setAttribute("resultMsg", "회원정보 수정 성공");
			}else {
				// 회원수정 실패시 modifyResultMsg 추가하기
				request.setAttribute("resultMsg", "회원정보 수정 실패");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("resultMsg", "5M 이하의 사진만 첨부 가능");
		}
		// 서버에 올라간 mPhoto 파일을 소스폴더에 filecopy
		File serverFile = new File(path+"/"+cPhoto);
		if(serverFile.exists()) {
			InputStream  is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:/MAGA_IT/source/7_jQuery/project/WebContent/mPhotoUp/"+cPhoto);
				byte[] bs = new byte[(int)serverFile.length()];
				while(true) {
					int nByteCnt = is.read(bs);
					if(nByteCnt==-1) {
						break;
					}
					os.write(bs, 0, nByteCnt);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if(os!=null) {
						os.close();
					}
					if(is!=null) {
						is.close();
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
}
