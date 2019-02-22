package org.kocofarm.service.comm;

import java.util.List;

import org.kocofarm.domain.comm.BoardVO;

public interface BoardService {
	
	public void setBoard(BoardVO board);
	
	public BoardVO getBoard(long bno);
	
	public boolean setupBoard(BoardVO board);
	
	public boolean delBoard(BoardVO board);
	
	public List<BoardVO> getBoardList();
	
	
	
	

}
