package myproject.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import myproject.configuration.exception.BaseException;
import myproject.configuration.http.BaseResponse;
import myproject.configuration.http.BaseResponseCode;
import myproject.data.domain.MySQLPageRequest;
import myproject.data.domain.PageRequestParameter;
import myproject.domain.Board;
import myproject.domain.BoardType;
import myproject.domain.MenuType;
import myproject.parameter.BoardParameter;
import myproject.parameter.BoardSearchParameter;
import myproject.service.BoardService;
import myproject.web.bind.annotation.RequestConfig;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 게시판 컨트롤러
 * @author kim-insu
 */
@Controller
@RequiredArgsConstructor
public class MyBoardController {

	Logger logger = LoggerFactory.getLogger(getClass());

	private final BoardService boardService;

	/**
	 * 목록 리턴
	 * @return
	 */
	@GetMapping("/{menuType}")
//	@ResponseBody
//	@ApiOperation(value = "목록 조회", notes = "게시물 목록 정보를 조회할 수 있습니다.")
	public String list(@PathVariable MenuType menuType, BoardSearchParameter parameter, MySQLPageRequest pageRequest, Model model) {
		logger.info("getList");
		logger.info("pageRequest = {}", pageRequest);
		parameter.setBoardType(menuType.boardType());
		PageRequestParameter<BoardSearchParameter> pageRequestParameter = new PageRequestParameter<BoardSearchParameter>(pageRequest, parameter);
//		return new BaseResponse<List<Board>>(boardService.getList(parameter));
		List<Board> boardList = boardService.getList(pageRequestParameter);;
		model.addAttribute("boardList", boardList);
		model.addAttribute("menuType", menuType);
		return "/myboard/list";
//		return new BaseResponse<List<Board>>(boardService.getList(pageRequestParameter));
	}

	/**
	 * 상세 페이지
	 * @param boardSeq
	 * @param model
	 * @return
	 */
	@GetMapping("/{menuType}/{boardSeq}")
	public String detail(@PathVariable MenuType menuType, @PathVariable int boardSeq,Model model) {
		Board board = boardService.get(boardSeq);
		//null 처리
		if (board == null) {
			throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] { "게시물" });
		}
		model.addAttribute("board", board);
		model.addAttribute("menuType", menuType);
		return "/myboard/detail";
	}

	/**
	 * 등록 화면
	 * @param parameter
	 * @param model
	 */
	@GetMapping("/{menuType}/form")
	@RequestConfig(loginCheck = false)
	public String form(@PathVariable MenuType menuType, BoardParameter parameter, Model model){
		model.addAttribute("parameter", parameter);
		model.addAttribute("menuType",menuType);
		return "/myboard/form";
	}

	/**
	 * 수정 화면
	 * @param parameter
	 * @param model
	 */
	@GetMapping("/{menuType}/edit/{boardSeq}")
	@RequestConfig(loginCheck = false)
	public String edit(@PathVariable MenuType menuType,
					   @PathVariable(required = false) int boardSeq,
					   BoardParameter parameter, Model model){
		Board board = boardService.get(parameter.getBoardSeq());
		//null 처리
		if (board == null) {
			throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] { "게시물" });
		}
		model.addAttribute("path", menuType);
		model.addAttribute("board", board);
		model.addAttribute("parameter", parameter);
		model.addAttribute("menuType", menuType);
		return "/myboard/form";
	}

	/**
	 * 등록/수정 처리
	 * @param parameter
	 */
//	@GetMapping("/save")
	@PostMapping("/{menuType}/save")
	@RequestConfig(loginCheck = false)
	@ResponseBody
	@ApiOperation(value = "등록 / 수정 처리", notes = "신규 게시물 저장 및 기존 게시물 업데이트가 가능합니다.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "boareSeq", value = "게시물 번호", example = "1"),
			@ApiImplicitParam(name = "title", value = "제목", example = "kim-insu-spring"),
			@ApiImplicitParam(name = "content", value = "내용", example = "kim-insu-lecture")
	})
	public BaseResponse<Integer> save(@PathVariable MenuType menuType, BoardParameter parameter) {
		// 제목 필수 체크
		if (StringUtils.isEmpty(parameter.getTitle())) {
			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] {"title", "제목"});
		}
		// 내용 필수 체크
		if (StringUtils.isEmpty(parameter.getContent())) {
			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] {"content", "내용"});
		}
		parameter.setBoardType(menuType.boardType());
		boardService.save(parameter);
		return new BaseResponse<Integer>(BaseResponseCode.SUCCESS, menuType.boardType().toString().toLowerCase(),"success");
	}

	/**
	 * 삭제 처리
	 * @param boardSeq
	 */
	@DeleteMapping("/{boardSeq}")
	@RequestConfig
	@ResponseBody
	@ApiOperation(value = "삭제 처리", notes = "게시물 번호에 해당하는 정보를 삭제합니다.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1")
	})
	public BaseResponse<Boolean> delete(@PathVariable int boardSeq) {
		Board board = boardService.get(boardSeq);
		if (board == null) {
			return new BaseResponse<Boolean>(false);
		}
		boardService.delete(boardSeq);
		return new BaseResponse<Boolean>(true);
	}

	/**
	 * 대용량 등록 처리
	 */
	@ResponseBody
	@ApiOperation(value = "대용량 등록처리1", notes = "대용량 등록처리1")
	@PutMapping("/saveList1")
	public BaseResponse<Boolean> saveList1() {
		int count = 0;
		// 테스트를 위한 랜덤 1000건의 데이터를 생성
		List<BoardParameter> list = new ArrayList<BoardParameter>();
		while (true) {
			count++;
			String title = RandomStringUtils.randomAlphabetic(10);
			String content = RandomStringUtils.randomAlphabetic(10);
			list.add(new BoardParameter(title, content));
			if (count >= 50) {
				break;
			}
		}
		long start = System.currentTimeMillis();
		boardService.saveList1(list);
		long end = System.currentTimeMillis();
		logger.info("실행 시간 : {}", (end - start) / 1000.0);
		return new BaseResponse<Boolean>(true);
	}

	/**
	 * 대용량 등록 처리.
	 */
	@PutMapping("/saveList2")
	@ApiOperation(value = "대용량 등록처리2", notes = "대용량 등록처리2")
	public BaseResponse<Boolean> saveList2() {
		int count = 0;
		// 테스트를 위한 랜덤 1000건의 데이터를 생성
		List<BoardParameter> list = new ArrayList<BoardParameter>();
		while (true) {
			count++;
			String title = RandomStringUtils.randomAlphabetic(10);
			String content = RandomStringUtils.randomAlphabetic(10);
			list.add(new BoardParameter(title, content));
			if (count >= 1000) {
				break;
			}
		}
		long start = System.currentTimeMillis();
		boardService.saveList2(list);
		long end = System.currentTimeMillis();
		logger.info("실행 시간 : {}", (end - start) / 1000.0);
		return new BaseResponse<Boolean>(true);
	}
}



