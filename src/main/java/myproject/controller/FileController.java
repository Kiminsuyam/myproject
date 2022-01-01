package myproject.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import myproject.configuration.GlobalConfig;
import myproject.configuration.exception.BaseException;
import myproject.configuration.http.BaseResponse;
import myproject.configuration.http.BaseResponseCode;
import myproject.parameter.UploadFileParameter;
import myproject.repository.UploadFileRepository;
import myproject.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
@Api(tags = "파일 API")
public class FileController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private GlobalConfig config;

	@Autowired
	private UploadFileService uploadFileService;


	/**
	 * 업로드 리턴
	 * @return
	 */
	@PostMapping("/save")
	@ApiOperation(value = "업로드", notes = "")
	public BaseResponse<Boolean> save(@RequestParam("uploadFile")MultipartFile multipartFile) throws IOException {
		logger.debug("config: {}", config);
		if (multipartFile == null || multipartFile.isEmpty()) {
			throw new BaseException(BaseResponseCode.DATA_IS_NULL);
		}
		//날짜폴더 추가
		String currentDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		String uploadFilePath = config.getUploadFilePath() + currentDate + "/";
		logger.debug("uploadFilePath : {}", uploadFilePath);

		String prefix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") +1);
		String filename = UUID.randomUUID().toString() + "." + prefix;
		logger.info("fileName : {}", filename);
		//폴더가 없다면 생성
		File folder = new File(uploadFilePath);
		if (!folder.isDirectory()) {
			folder.mkdirs();
		}
		String pathName = uploadFilePath + filename;
		String resoucePathname = config.getUploadResourcePath() + currentDate + "/" + filename;

		File dest = new File(pathName);
		logger.debug("dest : {}", dest);
		try {
			multipartFile.transferTo(dest);
			//파일 업로드 된 후 DB에 저장
			UploadFileParameter parameter = new UploadFileParameter();
			//컨텐츠 종류
			parameter.setContentType(multipartFile.getContentType());
			//원본파일명
			parameter.setOriginalFilename(multipartFile.getOriginalFilename());
			//저장파일명
			parameter.setFilename(filename);
			//실제파일 저장경로
			parameter.setPathname(pathName);
			//파일 크기
			parameter.setSize((int) multipartFile.getSize());
			//static resource 접근 경로
			parameter.setResourcePathname(resoucePathname);
			uploadFileService.save(parameter);
		}	catch (IllegalStateException | IOException e) {
			logger.error("e", e);
		}
		return new BaseResponse<Boolean>(true);
	}
}
