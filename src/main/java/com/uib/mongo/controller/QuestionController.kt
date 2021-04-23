package com.uib.mongo.controller

import com.uib.mongo.repository.entity.document.Question
import com.uib.mongo.repository.entity.document.QuestionAnswer
import com.uib.mongo.repository.entity.document.Questionnaire
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
import java.util.*

@Controller
@RequestMapping("/question")
class QuestionController(
    private val questionnaireService: QuestionnaireService
) {
    @GetMapping("/editQuestion/{listId}")
    fun getRow(
        @PathVariable("listId") listId: String,
        @RequestParam("questionId") questionId: String,
        @RequestParam("partId") partId: String,
        model: Model
    ): String {
        model.addAttribute(
            "question", questionnaireService.getQuestionByQuestionId(questionId)
        )
        model.addAttribute("newAnswer", QuestionAnswer(answer = ""))
        model.addAttribute("key", "key")
        model.addAttribute(
            "partsRecursive",
            questionnaireService.getPartRecursiveList(questionnaireService.getListQuestionnaireByListId(listId)?.parts!!)
        )
        model.addAttribute("partQuestion", questionnaireService.getPartQuestionnaireByPartId(partId))
        return "editQuestion"
    }

    @PostMapping("/editQuestion/{listId}")
    fun editRow(
        @PathVariable("listId") listId: String,
        @RequestParam("partId") partId: String,
        question: Question, attribute: RedirectAttributes
    ): RedirectView{
        var editPart = questionnaireService.getPartQuestionnaireByPartId(partId)
        var editQuestion = questionnaireService.getQuestionByQuestionId(question.questionId)

        if (editQuestion != null) {
            if (!editPart!!.questions!!.contains(editQuestion)) {
                questionnaireService.deleteQuestion(editQuestion.questionId)
                question.questionId = UUID.randomUUID().toString()
                editPart.questions!!.add(questionnaireService.saveEditQuestion(question))
                questionnaireService.saveEditPart(editPart)
            } else {
                questionnaireService.saveEditQuestion(question)
            }
        }

        attribute.addAttribute("partId", partId)
        attribute.addAttribute("questionId", question.questionId)
        return RedirectView("/question/editQuestion/${listId}")

       // return "redirect:/main/editList/${listId}"
    }

    @PostMapping("/addQuestion/{listId}")
    fun addQuestion(
        @RequestParam("partId") partId: String,
        @PathVariable("listId") listId: String,
        question: Question
    ): String {
        var editPart = questionnaireService.getPartQuestionnaireByPartId(partId)
        editPart?.questions?.add(questionnaireService.saveEditQuestion(question))
        if (editPart != null) {
            questionnaireService.saveEditPart(editPart)
        }
        return "redirect:/main/editList/${listId}"
    }

    @GetMapping("/deleteQuestion/{listId}/{questionId}")
    fun deleteQuestion(
        @PathVariable("questionId") questionId: String,
        @PathVariable("listId") listId: String
    ): String {
        questionnaireService.deleteQuestion(questionId)
        return "redirect:/main/editList/${listId}"
    }
}
