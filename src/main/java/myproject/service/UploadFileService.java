package myproject.service;

import myproject.domain.UploadFile;
import myproject.parameter.UploadFileParameter;
import myproject.repository.UploadFileRepository;
import org.springframework.stereotype.Service;

/**
 * 업로드 파일 서비스
 * @author kim-insu
 */
@Service
public class UploadFileService {


	private final UploadFileRepository repository;
	public UploadFileService(UploadFileRepository repository) {
	this.repository = repository;
	}
/**
 * 등록 처리
 * @author kim-insu
 */
	public void save(UploadFileParameter parameter) {
		repository.save(parameter);
	}

	public UploadFile get(int uploadFileSeq) {
		return repository.get(uploadFileSeq);
	}
}