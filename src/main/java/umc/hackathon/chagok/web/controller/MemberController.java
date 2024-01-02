package umc.hackathon.chagok.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.hackathon.chagok.apiPayload.ApiResponse;
import umc.hackathon.chagok.service.MemberService.MemberService;
import umc.hackathon.chagok.web.dto.MemberRequest;
import umc.hackathon.chagok.web.dto.MemberResponse;
import umc.hackathon.chagok.web.dto.MemberResponse.LoginResponseDTO;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MemberController {

    @Operation(summary = "특정 회원의 박스 조회 API", description = "특정 회원의 월별 박스 목록을 조회합니다. Query parameter로 달, 년도를 넘겨주세요. Header로 memberId를 넘겨주세요")
    @GetMapping("")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4001", description = "사용자가 없습니다."),

    })
    @Parameters({
            @Parameter(name = "mm", description = "선택 달 입니다!"),
            @Parameter(name = "page", description = "선택 년도 입니다!"),
    })
    public ApiResponse<List<Boolean>> viewBox(@RequestParam Integer mm, @RequestParam Integer yy,
                                              @RequestHeader Long memberId) {

        List<Boolean> boxList = memberService.boxCheck(memberId, mm, yy);
        return ApiResponse.onSuccess(boxList);
    }

    private final MemberService memberService;


}
