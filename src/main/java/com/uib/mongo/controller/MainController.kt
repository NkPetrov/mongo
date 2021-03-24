package com.uib.mongo.controller

import com.uib.mongo.repository.entity.document.Questionnaire
import com.uib.mongo.repository.entity.user.User
import com.uib.mongo.service.CustomUserDetailsService
import com.uib.mongo.service.QuestionnaireService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.math.BigInteger

@Controller
@RequestMapping("/main")
class MainController(
        private val questionnaireService: QuestionnaireService,
        private val customUserDetailsService: CustomUserDetailsService
) {

    @GetMapping
    fun getStartingPage(model: Model): String {
        model.addAttribute("newQuestionnaire", Questionnaire(name = "default"))
        model.addAttribute("questionnaire", questionnaireService.getQuestionnaireByUser(customUserDetailsService.getCurrentUser()))
        return "main"
    }

    @PostMapping("/editList/{listId}/{questionnaireId}")
    fun editList(@PathVariable("listId") listId: BigInteger,
                 @PathVariable("questionnaireId") questionnaireId: BigInteger,
                 model: Model): String{
        model.addAttribute("list",
                questionnaireService.getQuestionnaireByListIdAndQuestionnaireId(listId))
        return "editList"
    }
}
