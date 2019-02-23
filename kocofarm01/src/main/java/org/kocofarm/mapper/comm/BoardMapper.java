package org.kocofarm.mapper.comm;

import java.util.List;

import org.kocofarm.domain.comm.BoardVO;

public interface BoardMapper {

	public void setBoard(BoardVO board);
	
	public int setUpBoard(BoardVO board);
	
	public void delBoard(long bno);
	
	public List<BoardVO> getboardList(long bno);
	
	public BoardVO getBoard(long bno);
	
	
	public void insertSelectKey(BoardVO board);
}
