package com.yuchao.oj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuchao.oj.model.dto.question.QuestionQueryRequest;
import com.yuchao.oj.model.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuchao.oj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author TIDE
 * @description 针对表【question(题目)】的数据库操作Service
 * @createDate 2023-09-06 00:04:47
 */
public interface QuestionService extends IService<Question> {

    /**
     * 校验
     *
     * @param question
     * @param b
     */

    void validQuestion(Question question, boolean b);

    /**
     * 获取题目封装
     *
     * @param question
     * @param request
     * @return
     */
    QuestionVO getQuestionVO(Question question, HttpServletRequest request);

    /**
     * 获取查询条件
     *
     * @param questionQueryRequest
     * @return
     */
    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest);

    /**
     * 分页获取题目封装
     *
     * @param questionPage
     * @param request
     * @return
     */
    Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage, HttpServletRequest request);
}
