
package egovframework.bsc.properly.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.bsc.properly.vo.BscProperlyVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("bscProperlyDAO")
public class BscProperlyDAO  extends EgovAbstractDAO {
	@SuppressWarnings("unchecked")
    public List<BscProperlyVO> selectProperyList(Map<String, Object> map) throws Exception {
		return (List<BscProperlyVO>) list("BscProperlyDAO.selectProperyList", map);
    }

       

}

