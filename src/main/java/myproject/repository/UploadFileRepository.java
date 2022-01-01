package myproject.repository;

import myproject.domain.UploadFile;
import myproject.parameter.UploadFileParameter;
import org.springframework.stereotype.Repository;

/**
 * 업로드 파일 Repository
 * @author kim-insu
 */
@Repository
public interface UploadFileRepository {

	void save(UploadFileParameter parameter);

	UploadFile get(int uploadFileSeq);
}
