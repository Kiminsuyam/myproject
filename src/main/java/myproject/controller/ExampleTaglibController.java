package myproject.controller;

import lombok.RequiredArgsConstructor;
import myproject.data.domain.MySQLPageRequest;
import myproject.data.domain.PageRequestParameter;
import myproject.domain.BoardType;
import myproject.parameter.BoardSearchParameter;
import myproject.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/example/taglib/")
public class ExampleTaglibController {

	private final BoardService boardService;

	@RequestMapping("/search")
	public void search(BoardSearchParameter parameter, Model model, MySQLPageRequest pageRequest) {
		model.addAttribute("boardTypes", BoardType.values());
		model.addAttribute("parameter", parameter);
		PageRequestParameter<BoardSearchParameter> pageRequestParameter = new PageRequestParameter<BoardSearchParameter>(pageRequest, parameter);
		model.addAttribute("boardList", boardService.getList(pageRequestParameter));
	}



	@RequestMapping("/searchtest")
	public void searchtest(BoardSearchParameter parameter, Model model) {
		model.addAttribute("boardTypes", BoardType.values());
		model.addAttribute("parameter", parameter);

	}

}
