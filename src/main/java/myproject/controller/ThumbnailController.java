package myproject.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myproject.configuration.exception.BaseException;
import myproject.configuration.http.BaseResponseCode;
import myproject.domain.ThumbnailType;
import myproject.domain.UploadFile;
import myproject.service.UploadFileService;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/thumbnail")
@Api(tags = "썸네일 API")
public class ThumbnailController {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UploadFileService uploadFileService;

	@GetMapping("/make/{uploadFileSeq}/{thumbnailType}")
	public void make(@PathVariable int uploadFileSeq, @PathVariable ThumbnailType thumbnailType, HttpServletResponse response) throws IOException {
		UploadFile uploadFile = uploadFileService.get(uploadFileSeq);
		if (uploadFile == null) {
			throw new BaseException(BaseResponseCode.UPLOAD_FILE_IS_NULL);
		}
		String pathname = uploadFile.getPathname();
		File file = new File(pathname);
		if(!file.isFile()) {
			throw new BaseException(BaseResponseCode.UPLOAD_FILE_IS_NULL);
		}
		try {
			String thumbnailPathname = uploadFile.getPathname().replace(".", "_" + thumbnailType.width() + "_" + thumbnailType.getHeight() + ".");
			File thumbnailFile = new File(thumbnailPathname);
			if (!thumbnailFile.isFile()) {
				Thumbnails.of("pathname")
						.size(thumbnailType.width(), thumbnailType.height())
						.toFile("thumbnailFilename");
			}
			response.setContentType(MediaType.IMAGE_PNG_VALUE);
			FileCopyUtils.copy(new FileInputStream(thumbnailFile), response.getOutputStream());
			logger.info("thumbnailFilename : {}", thumbnailFile);
		} catch (IOException e) {
			logger.error("e", e);
		}


	}
}
