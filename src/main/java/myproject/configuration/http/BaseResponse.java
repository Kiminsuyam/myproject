package myproject.configuration.http;

import lombok.Data;
import lombok.Getter;
import myproject.domain.BoardType;
import myproject.domain.MenuType;

/**
 * 공통으로 사용할 응답 클래스.
 * @author kim-insu
 * @param <T>
 */

@Data
public class BaseResponse<T> {
	private BaseResponseCode code;
	private String message;
	private T data;
	private String path;

	public BaseResponse(T data) {
		this.code = BaseResponseCode.SUCCESS;
		this.data = data;
	}

	public BaseResponse(BaseResponseCode Code,
						String message) {
		this.code = Code;
		this.message = message;
	}

	public BaseResponse(BaseResponseCode Code,
						String path,
						String message) {
		this.code = Code;
		this.path = path;
		this.message = message;
	}
}
