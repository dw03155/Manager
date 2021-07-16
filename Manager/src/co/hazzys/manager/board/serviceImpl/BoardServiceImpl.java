package co.hazzys.manager.board.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.hazzys.manager.Dao;
import co.hazzys.manager.board.service.BoardService;
import co.hazzys.manager.board.vo.BoardVO;

public class BoardServiceImpl implements BoardService {
	private Connection conn = Dao.getConnection();
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<BoardVO> boardList() { // 게시판 목록
		List<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo;
		String sql = "select * from  board";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new BoardVO(); // vo 초기화
				vo.setBoardid(rs.getString("boardid"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setEnterdate(rs.getDate("enterdate"));
				vo.setHit(rs.getInt("hit"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public BoardVO boardSelect(BoardVO vo) { // 글 조회 (한건조회)
		String sql = "select * from board where boardid = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getBoardId());
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo.setBoardid(rs.getString("boardid"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setEnterdate(rs.getDate("enterdate"));
				vo.setHit(rs.getInt("hit"));
				updateHit(vo.getBoardId());//조회수 증가시키는 method
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	private void updateHit(String boardId) { // 조회수를 증가시킨다.
		String sql = "update board set hit = hit +1 where id = ?"; // ?에 들어갈 값 boardId
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, boardId); // ?에 들어갈 값 넣어줌
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//end of UH

	@Override
	public int boardInsert(BoardVO vo) { //
		int n = 0;
		return n;
	}

	@Override
	public int boardUpdate(BoardVO vo) {
		int n = 0;
		return n;
	}

	@Override
	public int boardDelete(BoardVO vo) {
		int n = 0;
		return n;
	}

}
