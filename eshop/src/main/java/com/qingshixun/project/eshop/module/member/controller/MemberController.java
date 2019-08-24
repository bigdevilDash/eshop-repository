package com.qingshixun.project.eshop.module.member.controller;

import com.qingshixun.project.eshop.dto.MemberDTO;
import com.qingshixun.project.eshop.module.member.service.MemberServiceImpl;
import com.qingshixun.project.eshop.web.BaseController;
import com.qingshixun.project.eshop.web.ResponseData;
import com.qingshixun.project.eshop.web.SimpleHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/front/member")
public class MemberController extends BaseController {

    @Autowired
    private MemberServiceImpl memberService;

    /**
     * 保存会员
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData memberSave(final @ModelAttribute MemberDTO member) throws Exception {
        return new SimpleHandler(request) {
            @Override
            protected void doHandle(ResponseData responseData) throws Exception {
                // 执行保存会员并发送邮件
                memberService.saveMember(member, getRealPath());
            }
        }.handle();
    }

    /**
     * 激活账号
     *
     */
    @RequestMapping(value = "/activate/{memberId}/{validateCode}", method = RequestMethod.GET)
    public String activateAccount(@PathVariable String memberId, @PathVariable String validateCode) throws Exception {
        memberService.activeMember(memberId, validateCode);
        // 转向（forward）前端页面，文件：/WEB-INF/views/login.jsp
        return "redirect:/front/login";
    }

}
