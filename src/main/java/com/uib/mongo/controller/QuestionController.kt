package com.uib.mongo.controller

import com.uib.mongo.repository.entity.document.Question
import com.uib.mongo.service.CustomUserDetailsService
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
    @GetMapping("/editQuestion/{listId}/{questionId}")
    fun getRow(@PathVariable("questionId") questionId: String,
                        @PathVariable("listId") listId: String,
                 model: Model): String{
        model.addAttribute("question",
                questionnaireService.getQuestionByQuestionId(questionId))
        return "editQuestion"
    }

    @PostMapping("/editQuestion/{listId}")
    fun editRow(@PathVariable("listId") listId: String,
                     question: Question): String{
        questionnaireService.saveEditQuestion(question)
        return "redirect:/main/editList/${listId}"
    }

    @PostMapping("/addQuestion/{listId}")
    fun addQuestion(@RequestParam("partId") partId: String,
                    @PathVariable("listId") listId: String,
                     question: Question): String{
        var editPart = questionnaireService.getPartQuestionnaireByPartId(partId)
        editPart?.questions?.add(questionnaireService.saveEditQuestion(question))
        if (editPart != null) {
            questionnaireService.saveEditPart(editPart)
        }
        return "redirect:/main/editList/${listId}"
    }
}
