package myproject.repository;

import myproject.data.domain.PageRequestParameter;
import myproject.domain.Board;
import myproject.parameter.BoardParameter;
import myproject.parameter.BoardSearchParameter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *  게시판 Repository
 * @author kim-insu
 */

@Repository
public interface BoardRepository {

//	List<Board> getList(Board parameter);
//	List<Board> getList(BoardSearchParameter parameter);
	List<Board> getList(PageRequestParameter<BoardSearchParameter> pageRequestParameter);
	Board get(int boardSeq);

	void save(BoardParameter board);
	void saveList(Map<String,Object> paramMap);

	void update(BoardParameter board);

	void delete(int boardSeq);


}
