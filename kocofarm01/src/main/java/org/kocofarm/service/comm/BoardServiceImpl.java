/*package org.kocofarm.service.comm;

import java.io.Console;
import java.util.List;

import org.junit.Test;
import org.kocofarm.domain.comm.BoardVO;
import org.kocofarm.mapper.comm.AttachFileMapper;
import org.kocofarm.mapper.comm.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
@Log4j
@Service
public class BoardServiceImpl implements BoardService {


	@Setter (onMethod_= @Autowired)
	private BoardMapper mapper;
	
	@Setter (onMethod_= @Autowired)
	private AttachFileMapper attachMapper;
	
	
	@Override
	
	public void insertselectkey(Board board){
		
		
		
		
		return;
	}
	
	
	@Test
	public void boardTest(){
		BoardVO board = new BoardVO();
		
		board.setTitle("제목 테스트");
		board.setContent("내용테스트");
		board.setWriter("작성자 테스트");

		
		
		
	}
	
	
	
	@Transactional
	@Override
	public void setBoard(BoardVO board){
		
		mapper.insertSelectKey(board);
		
		log.info("board : "+ board);
		
		if(board.getAttachList() == null || board.getAttachList().size() <=0){
			
			return;
			
		}
		
		board.getAttachList().forEach(attach->{
			
			attach.setBno(board.getBno());
			attachMapper.setFile(attach);
			
			
		});
		
		
	}
	
	
	
	


	@Override
	public BoardVO getBoard(long bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setupBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<BoardVO> getBoardList() {
		// TODO Auto-generated method stub
		return null;
	}

}
*/