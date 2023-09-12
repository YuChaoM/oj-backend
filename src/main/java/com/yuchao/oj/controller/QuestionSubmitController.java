package com.yuchao.oj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuchao.oj.common.BaseResponse;
import com.yuchao.oj.common.ErrorCode;
import com.yuchao.oj.common.ResultUtils;
import com.yuchao.oj.exception.BusinessException;
import com.yuchao.oj.exception.ThrowUtils;
import com.yuchao.oj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.yuchao.oj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yuchao.oj.model.entity.Question;
import com.yuchao.oj.model.entity.QuestionSubmit;
import com.yuchao.oj.model.entity.User;
import com.yuchao.oj.model.vo.QuestionSubmitVO;
import com.yuchao.oj.service.QuestionService;
import com.yuchao.oj.service.QuestionSubmitService;
import com.yuchao.oj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;


    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return 记录的id
     */
    @PostMapping("/")
    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
                                               HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能
        final User loginUser = userService.getLoginUser(request);
        long questionSubmitId = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(questionSubmitId);
    }

    /**
     * 分页获取题目提交列表（除了管理员外，普通用户只能看到非答案、提交代码等公开信息）
     * @param questionSubmitQueryRequest
     * @param request
     * @return 分页数据
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest,
                                                               HttpServletRequest request) {
        long current = questionSubmitQueryRequest.getCurrent();
        long size = questionSubmitQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        // 未脱敏的分页数据
        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
        User loginUser = userService.getLoginUser(request);
        // 返回脱敏信息
        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage, loginUser));
    }
}
