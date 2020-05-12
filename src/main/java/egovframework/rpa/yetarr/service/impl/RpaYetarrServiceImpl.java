
package egovframework.rpa.yetarr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rpa.yetarr.dao.RpaYetarrDAO;
import egovframework.rpa.yetarr.service.RpaYetarrService;
import egovframework.rpa.yetarr.vo.RpaYetarrVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("rpaYetarrService")
public class RpaYetarrServiceImpl extends EgovAbstractServiceImpl implements RpaYetarrService{
	private static final Logger LOGGER = LoggerFactory.getLogger(RpaYetarrServiceImpl.class);
	
    @Resource(name = "rpaYetarrDAO")
    private RpaYetarrDAO rpaYetarrDAO;

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectYetArrivedList(RpaYetarrVO searchVO) throws Exception {
		Map<String, Object> resultmap = new HashMap<String, Object>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("chkmonth", searchVO.getSearchMonth());
			
			List<Map<String, Object>> result = rpaYetarrDAO.selectYetArrivedList(map);
			//int cnt = attrbMngDAO.selectBBSMasterInfsCnt(searchVO);
			
			List<RpaYetarrVO> yetList = (List<RpaYetarrVO>) result.get(0);
			List<RpaYetarrVO> yetTotal = (List<RpaYetarrVO>) result.get(1);
			
			//GROUP BY된 데이터를 받을 MAP
			Map<String, Map<String, Object>> resultMap = new HashMap<String, Map<String, Object>>();
			
			for(int i=0; i<yetList.size(); i++){
				String orderNumber = "";
				if (yetList.get(i).getWaers().toString().equals("") ){
					orderNumber = yetList.get(i).getHwaer().toString();
				}
				else{
					orderNumber = yetList.get(i).getWaers().toString();
				}
				if(resultMap.containsKey(orderNumber)){
					//KEY값이 존재하면 해당 키값의 해당되는 가격을 가져와 더해줌
					resultMap.get(orderNumber).put("wrbtr", Double.parseDouble(resultMap.get(orderNumber).get("wrbtr").toString()) 
								+ Double.parseDouble(yetList.get(i).getWrbtr().toString()));
					resultMap.get(orderNumber).put("wrbtrp", Double.parseDouble(resultMap.get(orderNumber).get("wrbtrp").toString()) 
								+ Double.parseDouble(yetList.get(i).getWrbtrp().toString()));
				}else{
					//KEY값이 존재하지 않으면 MAP에 데이터를 넣어줌
					Map<String, Object> dataMap = new HashMap<String, Object>();
					dataMap.put("wrbtr", Double.parseDouble(yetList.get(i).getWrbtr().toString()));
					dataMap.put("wrbtrp", Double.parseDouble(yetList.get(i).getWrbtrp().toString()));
					resultMap.put(orderNumber, dataMap);
				}
			}
			
			//GROUP BY된 데이터를 받을 MAP
			Map<String, Map<String, Object>> resultMap2 = new HashMap<String, Map<String, Object>>();
			
			for(int i=0; i<yetTotal.size(); i++){
				String orderNumber = "total";
				if(resultMap2.containsKey(orderNumber)){
					//KEY값이 존재하면 해당 키값의 해당되는 가격을 가져와 더해줌
					resultMap2.get(orderNumber).put("wrbtrp", Double.parseDouble(resultMap2.get(orderNumber).get("wrbtrp").toString()) 
							+ Double.parseDouble(yetTotal.get(i).getWrbtrp().toString()));
					resultMap2.get(orderNumber).put("taxamt", Double.parseDouble(resultMap2.get(orderNumber).get("taxamt").toString()) 
							+ Double.parseDouble(yetTotal.get(i).getTaxamt().toString()));
					resultMap2.get(orderNumber).put("blamt", Double.parseDouble(resultMap2.get(orderNumber).get("blamt").toString()) 
							+ Double.parseDouble(yetTotal.get(i).getBlamt().toString()));
				}else{
					//KEY값이 존재하지 않으면 MAP에 데이터를 넣어줌
					Map<String, Object> dataMap = new HashMap<String, Object>();
					dataMap.put("wrbtrp", Double.parseDouble(yetTotal.get(i).getWrbtrp().toString()));
					dataMap.put("taxamt", Double.parseDouble(yetTotal.get(i).getTaxamt().toString()));
					dataMap.put("blamt", Double.parseDouble(yetTotal.get(i).getBlamt().toString()));
					resultMap2.put(orderNumber, dataMap);
				}
			}
			
			
			resultmap.put("yetList", yetList);
			resultmap.put("yetTotal", yetTotal);
			resultmap.put("resultMap", resultMap);
			resultmap.put("resultMap2", resultMap2);
		}
		catch(Exception e){
			LOGGER.error(e.toString());
			throw e;
	 	} 
		return resultmap;
	}

}

