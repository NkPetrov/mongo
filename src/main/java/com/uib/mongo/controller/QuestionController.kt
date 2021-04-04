package com.uib.mongo.controller

import com.uib.mongo.repository.entity.document.Question
import com.uib.mongo.service.QuestionnaireService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/question")
class QuestionController(
        private val questionnaireService: QuestionnaireService
) {
    @GetMapping("/editQuestion/{listId}/{partId}/{questionId}")
    fun getRow(@PathVariable("partId") partId: String,
               @PathVariable("questionId") questionId: String,
               @PathVariable("listId") listId: String,
               model: Model): String {
        model.addAttribute("question",
                questionnaireService.getQuestionByQuestionId(questionId))
        model.addAttribute("partsRecursive",
                questionnaireService.getPartRecursiveList(questionnaireService.getListQuestionnaireByListId(listId)?.parts!!))
        model.addAttribute("partQuestion", questionnaireService.getPartQuestionnaireByPartId(partId))
        return "editQuestion"
    }

    @PostMapping("/editQuestion/{listId}")
    fun editRow(@RequestParam("partId") partId: String,
                @PathVariable("listId") listId: String,
                question: Question): String {
        var editPart = questionnaireService.getPartQuestionnaireByPartId(partId)
        var editQuestion = questionnaireService.getQuestionByQuestionId(question.questionId)
        if (editQuestion != null) {
            if (editQuestion in editPart?.questions!!) {

            }else{
                editPart.questions?.add(questionnaireService.saveEditQuestion(question))
                questionnaireService.saveEditPart(editPart)
            }
        }else{
            questionnaireService.saveEditQuestion(question)
        }

        return "redirect:/main/editList/${listId}"
    }

    @PostMapping("/addQuestion/{listId}")
    fun addQuestion(@RequestParam("partId") partId: String,
                    @PathVariable("listId") listId: String,
                    question: Question): String {
        var editPart = questionnaireService.getPartQuestionnaireByPartId(partId)
        editPart?.questions?.add(questionnaireService.saveEditQuestion(question))
        if (editPart != null) {
            questionnaireService.saveEditPart(editPart)
        }
        return "redirect:/main/editList/${listId}"
    }
}
