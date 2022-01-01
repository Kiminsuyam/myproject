package myproject.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/example/parameter")
public class ExampleParameterController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("/example1")
	public void example1(@RequestParam(value = "id", required = false) String id,
						 @RequestParam(value = "code", required = false) String code,
						 Model model) {
		model.addAttribute("id", id);
		model.addAttribute("code", code);

	}

	/**
	 * Map활용 parameter 받는방법
	 * @param paramMap
	 * @param model
	 */
	@GetMapping("/example2")
	public void example2(@RequestParam Map<String, Object> paramMap, Model model) {
		model.addAttribute("paramMap", paramMap);
	}

	/**
	 * Class활용 parameter 받는방법
	 * @param parameter
	 * @param model
	 */
	@GetMapping("/example3")
	public void example3(ExampleParameter parameter, Model model) {
		model.addAttribute("parameter", parameter);
	}

	/**
	 * @PathVariable활용 parameter 받는방법
	 * @param id
	 * @param model
	 */
	@GetMapping("/example4/{id}/{code}")
	public String example4(@PathVariable String id,
						   @PathVariable String code,
						   Model model) {
		model.addAttribute("id", id);
		model.addAttribute("code", code);
		return "example/parameter/example4";
	}

	/**
	 * String[] 배열을 받는방법
	 * @param ids
	 * @param model
	 */
	@GetMapping("/example5")
	public String example5(@RequestParam String[] ids, Model model) {
		model.addAttribute("ids", ids);
		return "example/parameter/example5";
	}
	/*
	@GetMapping("/example5")

		public String example5(@HttpServletRequest request, Model model) {
			model.addAttribute("ids", request.getParameterValues("ids");
			return "example/parameter/example5";
	}*/

	/**
	 * json 받는방법
	 * @param paramMap
	 * @param model
	 */
	@GetMapping("/example6/form")
	public void form() {
	}

	/**
	 * json 받는방법
	 * @param requestBody
	 * @param
	 */
/*	@PostMapping("/example6/saveData")
	@ResponseBody
	public Map<String, Object> example6(@RequestBody Map<String, Object> requestBody) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", true);
		logger.info("requestBody : {}", requestBody);
		return resultMap;
	} */
	@PostMapping("/example6/saveData")
	@ResponseBody
	public Map<String, Object> example6(@RequestBody ExampleRequestBodyUser requestBody) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", true);
		logger.info("requestBody : {}", requestBody);
		return resultMap;
	}
}
