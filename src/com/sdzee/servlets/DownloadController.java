package com.sdzee.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.servlets.util.AvoidDuplication;

public class DownloadController extends HttpServlet {

	private static final long serialVersionUID = -3498276185950503694L;
	
	private static final int BUFFER_SIZE = 10240;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String requestedFile = req.getPathInfo();
		
		if (requestedFile == null || "/".equals(requestedFile))
		{
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		requestedFile = URLDecoder.decode(requestedFile, "UTF-8");
		File file = new File(AvoidDuplication.getPath(this), requestedFile);
		
		if (!file.exists())
		{
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		String fileType = getServletContext().getMimeType(file.getName());
		
		if (fileType == null)
		{
			fileType = "application/octet-stream";
		}
		
		/* Initializing HTTP response */
		resp.reset();
		resp.setBufferSize(BUFFER_SIZE);
		resp.setContentType(fileType);
		resp.setHeader("Content-Length", String.valueOf(file.length()));
		resp.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
		
		BufferedInputStream input = null;
		BufferedOutputStream output = null;
		
		try
		{
			input = new BufferedInputStream(new FileInputStream(file));
			output = new BufferedOutputStream(resp.getOutputStream(), BUFFER_SIZE);
			
			byte[] buffer = new byte[BUFFER_SIZE];
			int length = 0;
			
			while ((length = input.read(buffer)) > 0)
			{
				output.write(buffer, 0, length);
			}
		}
		finally
		{
			try
			{
				output.close();
			}
			catch (IOException ignore)
			{}
			try
			{
				input.close();
			}
			catch (IOException ignore)
			{}
		}
	}
}
