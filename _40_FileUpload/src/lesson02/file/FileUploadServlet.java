package lesson02.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@SuppressWarnings("serial")
@WebServlet("/fileUploadServlet")
public class FileUploadServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		out.println("<html><head><title>Multipart Test" +
					"</title></head><body>");
		
		try {
			// 기본 경로(WebContent경로)
			String contextRootPath = 
					this.getServletContext().getRealPath("/");
			System.out.println("contextRootPath : " + contextRootPath);
			
			DiskFileItemFactory diskFactory = new DiskFileItemFactory();
			diskFactory.setRepository(
					new File(contextRootPath + "/WEB-INF/temp"));
			ServletFileUpload upload = new ServletFileUpload(diskFactory);
			
			@SuppressWarnings("unchecked")
			List<FileItem> items = upload.parseRequest(req);
			
			FileItem item = null;
			for(int i=0;i<items.size();i++) {
				item = items.get(i);
				if(item.isFormField())				// 일반 변수
					processFormField(out, item);
				else								// 파일
					processUploadFile(out, item, contextRootPath);
			}
			
		}catch(Exception e) {
			out.println("<pre>");
			e.printStackTrace(out);
			out.println("</pre>");
		}
		
		out.println("</body></html>");
	}
	
	private void processFormField(PrintWriter out, FileItem item) 
			throws Exception {
		String name = item.getFieldName();		// 변수명
		String value = item.getString("UTF-8");	// 값
		
		out.println(name + ":" + value + "<br/>");
	}
	
	private void processUploadFile(PrintWriter out, FileItem item,
			String contextRootPath) throws Exception{
		String name = item.getFieldName();
		String fileName = item.getName();
		String contentType = item.getContentType();
		long fileSize = item.getSize();
		
		String uploadedFileName = System.currentTimeMillis() + 
				fileName.substring(fileName.lastIndexOf("."));
		System.out.println("저장파일 이름 : " + uploadedFileName);
		File uploadedFile = new File(
				contextRootPath + "/upload/" + uploadedFileName);
		item.write(uploadedFile);
		
		// 브라우저에 이미지 보여주기
		out.println("<p>");
		out.println("파라미터 이름 : " + name + "<br/>");
		out.println("파일 이름 : " + fileName + "<br/>");
		out.println("콘텐츠 타입 : " + contentType + "<br/>");
		out.println("파일 크기 : " + fileSize + "<br/>");
		out.println("<img src='./upload/" + uploadedFileName
				+ "' width='500'><br>");
		out.println("</p>");
	}
}














