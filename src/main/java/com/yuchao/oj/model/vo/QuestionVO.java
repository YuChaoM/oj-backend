package com.yuchao.oj.model.vo;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuchao.oj.model.dto.question.JudgeCase;
import com.yuchao.oj.model.dto.question.JudgeConfig;
import com.yuchao.oj.model.entity.Post;
import com.yuchao.oj.model.entity.Question;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目视图
 */
@Data
public class QuestionVO implements Serializable {

    private static final long serialVersionUID = 1725748233754064812L;

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表（json 数组）
     */
    private List<String> tags;

    /**
     * 答案
     */
    private String answer;

    /**
     * 题目提交数
     */
    private Integer submitNum;

    /**
     * 题目通过数
     */
    private Integer acceptedNum;

    /**
     * 判题配置（json对象）
     */
    private JudgeConfig judgeConfig;

    /**
     * 点赞数
     */
    private Integer thumbNum;

    /**
     * 收藏数
     */
    private Integer favourNum;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 用户脱敏数据
     */
    private UserVO user;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 包装类转对象
     *
     * @param questionVO
     * @return
     */
    public static Question voToObj(QuestionVO questionVO) {
        if (questionVO == null) {
            return null;
        }
        Question question = new Question();
        BeanUtils.copyProperties(questionVO, question);// 浅拷贝
        List<String> tags = questionVO.getTags();
        if (tags != null) {
            question.setTags(JSONUtil.toJsonStr(tags));
        }
        JudgeConfig judgeCase = questionVO.getJudgeConfig();
        if (judgeCase != null) {
            question.setJudgeConfig(JSONUtil.toJsonStr(judgeCase));
        }
        return question;
    }

    /**
     * 对象转包装类
     *
     * @param question
     * @return
     */
    public static QuestionVO objToVo(Question question) {
        if (question == null) {
            return null;
        }
        QuestionVO questionVO = new QuestionVO();
        BeanUtils.copyProperties(question, questionVO);
        String tags = question.getTags();
        questionVO.setTags(JSONUtil.toList(tags, String.class));
        JudgeConfig judgeConfig = JSONUtil.toBean(question.getJudgeConfig(), JudgeConfig.class);
        questionVO.setJudgeConfig(judgeConfig);
        return questionVO;
    }

}
