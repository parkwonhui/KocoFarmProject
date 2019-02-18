package org.kocofarm.mapper.comm;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kocofarm.domain.comm.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j

public class TestCommMapper {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		
		
		board.setTitle("제목 테스트");
		board.setContent("내용테스트");
		board.setWriter("작성자");
		
		
		mapper.setBoard(board);
		log.info(board);
	}
	
}
