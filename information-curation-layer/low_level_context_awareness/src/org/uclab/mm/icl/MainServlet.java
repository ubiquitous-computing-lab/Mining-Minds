/*
Copyright [2016] [Dong Uk, Kang]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package org.uclab.mm.icl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import org.uclab.mm.icl.llc.LLCManager.User;

@WebServlet(name="MainServlet", urlPatterns = {"/main"})
public class MainServlet implements Servlet {
	/**/
	public final static LLCServer server = new LLCServer(2950);
	//public final static HLCServer sserver = new HLCServer();
	private static final HashMap<Long, User> map = new HashMap<Long, User>(); // have to be moved somewhere

	private transient ServletConfig servletConfig;

	
	public static HashMap<Long, User> getUserMap(){
		return map;
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return "Main Servlet for ICL 3.0";
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		this.servletConfig = arg0;
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String servletName = servletConfig.getServletName();
		arg1.setContentType("text/html");
		PrintWriter writer = arg1.getWriter();
		writer.print("<html><head></head><body>Hello from " + servletName + "</body></html>" );
	}
	
}
