package myproject.parameter;

import lombok.Data;
import myproject.domain.BoardType;

import java.util.List;

/**
 * 게시물 검색 파라메터
 * @author kim-insu
 */
@Data
public class BoardSearchParameter {

	private String keyword;
//	private List<BoardType> boardTypes;
	private BoardType[] boardTypes;
	private BoardType boardType;

	public BoardSearchParameter() {

	}

}