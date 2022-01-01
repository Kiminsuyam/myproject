package myproject.data.domain;

import lombok.Data;
import myproject.data.domain.MySQLPageRequest;

/**
 * 페이지 요청정보와 파라미터 정보
 * @author kim-insu
 * @param <T>
 */
@Data
public class PageRequestParameter<T> {

	private MySQLPageRequest pageRequest;
	private T parameter;

	public PageRequestParameter(MySQLPageRequest pageRequest, T parameter) {
		this.pageRequest=pageRequest;
		this.parameter=parameter;
	}
}
