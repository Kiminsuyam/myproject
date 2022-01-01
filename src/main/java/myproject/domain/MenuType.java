package myproject.domain;

import lombok.Getter;

/**
 * 메뉴 종류
 * @author kim-insu
 */
@Getter
public enum MenuType implements BaseCodeLabelEnum {

	community(BoardType.COMMUNITY, "menu.community", "/community"),
	notice(BoardType.NOTICE,"menu.notice", "/notice"),
	faq(BoardType.FAQ,"menu.faq", "/faq"),
	inquiry(BoardType.INQUIRY, "menu.inquiry","/inquiry"),
	;

	private String code;
	private String label;

	private BoardType boardType;
	private String menuCode;
	private String url;

	MenuType(BoardType boardType, String menuCode, String url) {
		this.boardType = boardType;
		this.menuCode = menuCode;
		this.url = url;
	}

	public BoardType boardType() {
		return boardType;
	}

	public String menuCode() {
		return menuCode;
	}

	public String url() {
		return url;
	}

	MenuType(String label) {
		this.code = name();
		this.label = label;
	}

	@Override
	public String code() {
		return code;
	}

	@Override
	public String label() {
		return label;
	}
}
