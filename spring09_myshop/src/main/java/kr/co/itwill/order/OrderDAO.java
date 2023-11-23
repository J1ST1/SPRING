package kr.co.itwill.order;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAO {

	public OrderDAO() {
		System.out.println("-----OrderDAO() 객체 생성됨");
	}//end
	
	@Autowired
	SqlSession sqlSession;
	
	public String orderno(String date) {
		return sqlSession.selectOne("order.orderno", date);
	}//orderno() end
	
	
}//class end
