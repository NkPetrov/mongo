package com.uib.mongo.controller

import com.uib.mongo.repository.entity.document.ListQuestionnaire
import com.uib.mongo.service.QuestionnaireService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/list")
class ListController(
    private val questionnaireService: QuestionnaireService
) {
    @PostMapping("/addList/{questionnaireId}")
    fun addList(
        @PathVariable("questionnaireId") questionnaireId: String,
        list: ListQuestionnaire
    ): String {
        var listDB = questionnaireService.saveEditList(list)
        var questionnaire = questionnaireService.getQuestionnaireByQuestionnaireId(questionnaireId)
        questionnaire!!.lists!!.add(listDB)
        questionnaireService.saveEditQuestionnaire(questionnaire)
        //questionnaireService.getQuestionnaireByQuestionnaireId(questionnaireId)!!.lists!!.add(list)
        return "redirect:/main"
    }

    @GetMapping("/deleteList/{listId}")
    fun deleteList(
        @PathVariable("listId") listId: String): String {
        questionnaireService.deleteList(listId)
        return "redirect:/main"
    }
}
