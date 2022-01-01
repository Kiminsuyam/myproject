package myproject.domain;

import lombok.Getter;

@Getter
public enum ThumbnailType {

	WEB_MAIN(500, 300),
	WEB_SUB(150, 70),
	;
	ThumbnailType(int width, int height) {
		this.width = width;
		this.height = height;
	}
	private int width;
	private int height;

	public int width() {
		return width;
	}

	public int height() {
		return height;
	}
}
