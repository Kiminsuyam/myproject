package myproject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myproject.data.domain.PageRequestParameter;
import myproject.domain.Board;
import myproject.parameter.BoardParameter;
import myproject.parameter.BoardSearchParameter;
import myproject.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository repository;

	/**
	 * 목록 리턴
	 * @param
	 * @param pageRequestParameter
	 * @return
	 */
//	public List<Board> getList(BoardSearchParameter parameter) {return repository.getList(parameter);}
	public List<Board> getList(PageRequestParameter<BoardSearchParameter> pageRequestParameter) {
		return repository.getList(pageRequestParameter);
	}

	/**
	 * 상세 정보 리턴
	 * @param boardSeq
	 * @return
	 */
	public Board get(int boardSeq) {
		return repository.get(boardSeq);
	}

	/**
	 * 등록 처리
	 * @param parameter
	 */
	public void save(BoardParameter parameter) {
		//조회하여 리턴된 정보
		Board board = repository.get(parameter.getBoardSeq());
		if (board == null) {
			repository.save(parameter);
		} else {
			repository.update(parameter);
		}

	}

	/**
	 * 업데이트 처리
	 * @param board
	 */
//	public void update(Board board) {
//		repository.update(board);
//	}

	/**
	 * 삭제 처리
	 * @param boardSeq
	 */
	public void delete(int boardSeq) {
		repository.delete(boardSeq);
	}

	/**
	 * 단순 반복문을 이용한 등록 처리
	 */
	public void saveList1(List<BoardParameter> list) {
		for (BoardParameter parameter : list) {
			repository.save(parameter);
		}
	}

	/**
	 * 100개씩 배열에 담아서 일괄 등록 처리 (실무에서 자주 사용)
	 */
	public  void saveList2(List<BoardParameter> boardList) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("boardList", boardList);
		repository.saveList(paramMap);
	}
}