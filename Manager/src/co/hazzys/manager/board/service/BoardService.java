package co.hazzys.manager.board.service;

import java.util.List;

import co.hazzys.manager.board.vo.BoardVO;

public interface BoardService {
	List<BoardVO> boardList();//전체 게시글목록
	BoardVO boardSelect(BoardVO vo);//게시글 조회(한건조회)
	int boardInsert(BoardVO vo);	//게시글 쓰기
	int boardUpdate(BoardVO vo);	//게시글 수정
	int boardDelete(BoardVO vo);	//게시글 삭제
}
