package myproject.parameter;

import lombok.Data;
import myproject.domain.BoardType;

@Data
public class BoardParameter {

	private int boardSeq;
	private BoardType boardType;
	private String title;
	private String content;
	private boolean delYn;

	//기본생성자
	public BoardParameter() {
	}
	//테스트용 생성자
	public BoardParameter(String title, String content) {
		this.title = title;
		this.content = content;
	}

}
