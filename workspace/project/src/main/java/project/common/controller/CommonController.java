package project.common.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import project.common.common.CommandMap;
import project.common.service.CommonService;
 
@Controller
public class CommonController {
    Logger log = Logger.getLogger(this.getClass());
     
    @Resource(name="commonService")
    private CommonService commonService;
    
    @RequestMapping(value="/common/downloadFile.do")
    public void downloadFile(CommandMap commandMap, HttpServletResponse response) throws Exception{
        Map<String,Object> map = commonService.selectFileInfo(commandMap.getMap());
        String storedFileName = (String)map.get("STORED_FILE_NAME");
        String originalFileName = (String)map.get("ORIGINAL_FILE_NAME");
         
        byte fileByte[] = FileUtils.readFileToByteArray(new File("C:\\dev\\file\\"+storedFileName));
         
        response.setContentType("application/octet-stream");
        response.setContentLength(fileByte.length);
        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFileName,"UTF-8")+"\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.getOutputStream().write(fileByte);
         
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
    
	// login
	@RequestMapping(value="/common/login.do")
	public ModelAndView login(CommandMap commandMap, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:..");
		
		// input text save
		String inputId = (String) commandMap.get("id");
		String inputPw = (String) commandMap.get("password");
		
		// db search result save
		Map<String, Object> map = commonService.login(commandMap.getMap());
		
		// compare (input, result)

		if (map != null && !map.isEmpty()){
			if (inputId.equals(map.get("ID")) && (inputPw.equals(map.get("PASSWORD")))) {
				session.setAttribute("sName", map.get("NAME"));
				session.setAttribute("sId", map.get("ID"));	
			} else {
				// ID isn't null in DB, but one of (ID, PASS) is incorrect.
			}
		} else {
			// ID is null in DB
		}
		return mv;		
	}
	
	// logout
	@RequestMapping(value="/common/logout.do")
	public ModelAndView login(HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:..");
		session.invalidate();
		return mv;
	}
	
	@RequestMapping(value="/common/join.do")
	public ModelAndView join(CommandMap commandMap, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:..");
		
		String id = (String) commandMap.get("id");
		String name = (String) commandMap.get("name");
		
		try {
		// db insert
		commonService.join(commandMap.getMap());
			
		// create session
		session.setAttribute("sName", name);
		session.setAttribute("sId", id);
		} catch (Exception e) {e.printStackTrace();} 	
		
		return mv;	
	}
		
}
	
	
	
	
	
	
	
	
	