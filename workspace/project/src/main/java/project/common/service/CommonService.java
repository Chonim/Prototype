package project.common.service;

import java.util.Map;

public interface CommonService {
	Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;
	
	Map<String, Object> login(Map<String, Object> map) throws Exception;

	void join(Map<String, Object> map);
}