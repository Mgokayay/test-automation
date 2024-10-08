package com.doping.demo.util;

import com.doping.demo.dto.QuestionResponse;
import com.doping.demo.entity.Question;

import java.util.List;

public class QuestionDtoConversion {


    public static QuestionResponse questionConvert(Question question) {
        return new QuestionResponse(question.getId(), question.getQuestionText(), question.getOptions(), question.getCorrectAnswerIndex());
    }

    public static List<QuestionResponse> questionConvertList(List<Question> questions) {
        return questions.stream().map(QuestionDtoConversion::questionConvert)
                .toList();
    }
}
