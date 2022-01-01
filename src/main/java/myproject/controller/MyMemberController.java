package myproject.controller;

import lombok.RequiredArgsConstructor;
import myproject.configuration.session.HttpSessionMember;
import myproject.configuration.session.HttpSessionNiceAuth;
import myproject.dto.SessionMember;
import myproject.dto.SessionNiceAuth;
import myproject.parameter.MemberSaveParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class MyMemberController {

	private final HttpSessionMember httpSessionMember;
	private final HttpSessionNiceAuth httpSessionNiceAuth;

	@PostMapping("/niceAuth/response")
	@ResponseBody
	public boolean niceAuthResponse(HttpServletRequest request) {
		//실명인증 성공해서 응답이 온 경우 예시
		//디비 조회해서 실제회원인 경우라고 가정하고 예시
		SessionNiceAuth niceAuth = new SessionNiceAuth();
		//인증받은 식별코드..
		niceAuth.setAuthId("342342342552552");
		niceAuth.setPhoneNumber("01012341234");
		niceAuth.setName("김인수");
		//로그인 시 세션에 정보 저장
		httpSessionNiceAuth.setAttribute(niceAuth);
		return true;
	}

	@PostMapping("/member/signup/save")
	@ResponseBody
	public boolean signUpSave(@RequestParam String memberId) {
		//회원가입 예시
		SessionNiceAuth niceAuth = httpSessionNiceAuth.getAttribute();
		//본인인증 안된경우 예외처리
		if(niceAuth == null) {
			throw new RuntimeException("회원가입시 본인인증은 필수 입니다.");
		}
		//DB에 저장될 정보 set 본인인증 정보까지
		MemberSaveParameter member = new MemberSaveParameter();
		member.setAuthId(niceAuth.getAuthId());
		member.setName(niceAuth.getName());
		member.setPhoneNumber(niceAuth.getPhoneNumber());
		member.setMemberId(memberId); //실제 회원에게 입력받은 아이디
		//회원 DB에 저장 로직 처리 후
		//본인인증 세션 초기화
		httpSessionNiceAuth.removeAttribute();
		return true;
	}

	@PostMapping("/login")
	@ResponseBody
	public boolean login(@RequestParam String memberId) {
		//디비조회에서 실제회원인 경우라고 가정하고 예시.
		SessionMember member = new SessionMember();
		member.setMemberId(memberId);
		//로그인 시 세션에 정보 저장
		httpSessionMember.setAttribute(member);
		return true;
	}

	@GetMapping("/mypage/info")
	public String info(Model model) {
		//세션에 저장된 정보
		model.addAttribute("member", httpSessionMember.getAttribute());
		return "/mypage/info";
	}

	@GetMapping("/logout")
	public String logout() {
		//로그아웃 시 모든 세션정보 삭제
		httpSessionMember.invalidate();
		return "redirect:/main";
	}
}
