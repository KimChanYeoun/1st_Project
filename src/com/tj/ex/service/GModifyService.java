package com.tj.ex.service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.ex.dao.GalleryDao;
public class GModifyService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("fileboardUp");
		int maxSize = 1024*1024*10; // 최대업로드 사이즈는 10M
		MultipartRequest mRequest = null;
		String gfileName = "";
		try {
			mRequest = new MultipartRequest(request, path, maxSize, 
									"utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			gfileName = mRequest.getFilesystemName(param);
			String dbgFileName = mRequest.getParameter("dbgFileName");
			if(gfileName==null) {
				gfileName = dbgFileName;
			}
			// mId, fTitle, fContent,  fileName, fIp
			int gNum = Integer.parseInt(mRequest.getParameter("gNum"));
			String gTitle = mRequest.getParameter("gTitle");
			String gContent = mRequest.getParameter("gContent");
			String gIp = request.getRemoteAddr();
			GalleryDao galleryDao = new GalleryDao();
			int result = galleryDao.gModify(gNum, gTitle, gContent, gfileName, gIp);
			// joinMember결과에 따라 적절히 request.setAttribute
			if(result == GalleryDao.SUCCESS) { // 회원가입 진행
				request.setAttribute("resultMsg", "글수정 성공");
			}else {
				request.setAttribute("resultMsg", "글수정 실패");
			}
			// pageNum이 request객체에서 온 것은 null값이라 request에 담아놓음
			String gpageNum = mRequest.getParameter("gpageNum");
			request.setAttribute("gpageNum", gpageNum);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("resultMsg", "글쓰기 실패");
		}
		// 서버에 올라간 fileboardUp 파일을 소스폴더에 filecopy
		File serverFile = new File(path+"/"+gfileName);
		if(serverFile.exists()) {
			InputStream  is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:/MAGA_IT/source/7_jQuery/project/WebContent/fileboardUp/"+gfileName);
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