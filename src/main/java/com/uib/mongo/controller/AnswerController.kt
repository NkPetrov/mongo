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
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView

@Controller
@RequestMapping("/answer")
class AnswerController(
        private val questionnaireService: QuestionnaireService
) {
    @GetMapping("/editAnswer/{listId}")
    fun getAnswer(@PathVariable("listId") listId: String,
                  @RequestParam("answerId") answerId: String,
                  @RequestParam("questionId") questionId: String,
                  @RequestParam("partId") partId: String,
                  model: Model): String {
        model.addAttribute("questionId",questionId)
        model.addAttribute("partId",partId)
        model.addAttribute("answer", questionnaireService.getAnswerById(answerId = answerId))
        return "editAnswer"
    }

    @PostMapping("/addAnswer/{questionId}")
    fun addAnswer(@PathVariable("questionId") questionId: String,
                  @RequestParam("listId") listId: String,
                  @RequestParam("partId") partId: String,
                  answer: QuestionAnswer,
                  attribute: RedirectAttributes
    ): RedirectView {
        var answer = questionnaireService.saveEditAnswer(answer)
        var question = questionnaireService.getQuestionByQuestionId(questionId)
        question!!.answers!!.add(answer)
        questionnaireService.saveEditQuestion(question)

        attribute.addAttribute("questionId", questionId)
        attribute.addAttribute("partId", partId)
        return RedirectView("/question/editQuestion/${listId}")
    }

    @PostMapping("/editAnswer/{listId}")
    fun editAnswer(@PathVariable("listId") listId: String,
                   @RequestParam("answerId") answerId: String,
                   @RequestParam("partId") partId: String,
                   @RequestParam("questionId") questionId: String,
                   answer: QuestionAnswer,
                   attribute: RedirectAttributes
    ): RedirectView {
        questionnaireService.saveEditAnswer(answer)

        attribute.addAttribute("questionId", questionId)
        attribute.addAttribute("partId", partId)
        return RedirectView("/question/editQuestion/${listId}")
    }

    @PostMapping("/deleteAnswer/{listId}")
    fun deleteAnswer(
            @PathVariable("listId") listId: String,
            @RequestParam("answerId") answerId: String,
            @RequestParam("partId") partId: String,
            @RequestParam("questionId") questionId: String,
            attribute: RedirectAttributes
    ): RedirectView {
        var parentQuestion = questionnaireService.getQuestionByQuestionId(questionId)
        var deletedAnswer = questionnaireService.getAnswerById(answerId)
        questionnaireService.deleteAnswer(answerId)
        parentQuestion!!.answers!!.remove(deletedAnswer!!)
        questionnaireService.saveEditQuestion(parentQuestion)

        attribute.addAttribute("questionId", questionId)
        attribute.addAttribute("partId", partId)
        return RedirectView("/question/editQuestion/${listId}")
    }
}
