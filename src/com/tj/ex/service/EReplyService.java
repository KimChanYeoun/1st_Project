package com.tj.ex.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.ex.dao.ErrorboardDao;

public class EReplyService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("fileboardUp");
		int maxSize = 1024 * 1024 * 10; // 최대업로드 사이즈는 10M
		MultipartRequest mRequest = null;
		String efileName = "";
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			efileName = mRequest.getFilesystemName(param);
			// mId, fTitle, fContent, fileName, fIp
			HttpSession httpSession = request.getSession();
			String aId = (String) httpSession.getAttribute("aId");
			String eTitle = mRequest.getParameter("eTitle");
			String eContent = mRequest.getParameter("eContent");
			String eIp = request.getRemoteAddr();
			System.out.println(mRequest.getParameter("eGroup"));
			int eGroup = Integer.parseInt(mRequest.getParameter("eGroup"));
			int eStep = Integer.parseInt(mRequest.getParameter("eStep"));
			int eIndent = Integer.parseInt(mRequest.getParameter("eIndent"));
			ErrorboardDao eDao = new ErrorboardDao();
			int result = eDao.ereply(aId, eTitle, eContent, efileName, eGroup, eStep, eIndent, eIp);
			// joinMember결과에 따라 적절히 request.setAttribute
			if (result == ErrorboardDao.SUCCESS) { // 회원가입 진행
				request.setAttribute("resultMsg", "답글쓰기 성공");
			} else {
				request.setAttribute("resultMsg", "답글쓰기 실패");
			}
			// pageNum이 request객체에서 온 것은 null값이라 request에 담아놓음
			String epageNum = request.getParameter("epageNum");
			request.setAttribute("epageNum", epageNum);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.setAttribute("resultMsg", "답글쓰기 실패");
		}
		// 서버에 올라간 fileboardUp 파일을 소스폴더에 filecopy
		File serverFile = new File(path + "/" + efileName);
		if (serverFile.exists()) {
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:/MAGA_IT/source/7_jQuery/project/WebContent/fileboardUp/" + efileName);
				byte[] bs = new byte[(int) serverFile.length()];
				while (true) {
					int nByteCnt = is.read(bs);
					if (nByteCnt == -1) {
						break;
					}
					os.write(bs, 0, nByteCnt);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if (os != null) {
						os.close();
					}
					if (is != null) {
						is.close();
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
}