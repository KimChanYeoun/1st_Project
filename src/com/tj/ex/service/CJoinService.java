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
public class CJoinService implements Service {
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
			if(cPhoto==null) {
				cPhoto = "NOIMG.JPG";
			}
			int cPoint = 1000;
			// dto객체로 만들고, dao의 joinMember(dto)호출
			CustomerDto dto = new CustomerDto(cId, cPw, cName, cPhoto, cBirth, cTel, cEmail, cGender, cAddr, null, cPoint);
			CustomerDao cDao = new CustomerDao();
			// mId 중복체크
			int result = cDao.cIdConfirm(cId);
			if(result == CustomerDao.NONEXISTENT) { // 회원가입 진행
				result = cDao.joinCostomer(dto);
				if(result == CustomerDao.SUCCESS) {
					HttpSession httpSession = request.getSession();
					httpSession.setAttribute("cId", cId);
					request.setAttribute("resultMsg","회원가입이 성공되었습니다");
				}else {
					request.setAttribute("errorMsg", "회원가입 실패되었습니다");
				}
			}else {
				request.setAttribute("errorMsg", "중복된 ID라서 회원가입 불가합니다");
			}
			// joinMember결과에 따라 적절히 request.setAttribute
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("errorMsg", "5M 이하의 사진을 첨부해 주세요");
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
					if(nByteCnt==-1) break;{
					os.write(bs, 0, nByteCnt);
					}
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