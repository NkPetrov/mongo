package com.uib.mongo.controller

import com.uib.mongo.repository.entity.document.QuestionAnswer
import com.uib.mongo.service.QuestionnaireService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/answer")
class AnswerController(
        private val questionnaireService: QuestionnaireService
) {
    @GetMapping("/editAnswer/{listId}")
    fun getAnswer(@PathVariable("listId") listId: String,
                  @RequestParam("answerId") answerId: String,
                  model: Model): String {
        model.addAttribute("answer", questionnaireService.getAnswerById(answerId = answerId))
        return "editAnswer"
    }

    @PostMapping("/addAnswer/{questionId}")
    fun addAnswer(@PathVariable("questionId") questionId: String,
                  @RequestParam("listId") listId: String,
                  answer: QuestionAnswer): String {
        var answer = questionnaireService.saveEditAnswer(answer)
        var question = questionnaireService.getQuestionByQuestionId(questionId)
        question!!.answers!!.add(answer)
        questionnaireService.saveEditQuestion(question)
        return "redirect:/main/editList/${listId}"
    }

    @PostMapping("/editAnswer/{listId}")
    fun editAnswer(@PathVariable("listId") listId: String,
                   @RequestParam("answerId") answerId: String,
                   answer: QuestionAnswer): String {
        questionnaireService.saveEditAnswer(answer)
        return "redirect:/main/editList/${listId}"
    }

    @PostMapping("/deleteAnswer/{listId}")
    fun deleteAnswer(
            @PathVariable("listId") listId: String,
            @RequestParam("answerId") answerId: String,
    ): String {
        questionnaireService.deleteAnswer(answerId)
        return "redirect:/main/editList/${listId}"
    }
}
