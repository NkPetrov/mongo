package com.uib.mongo.controller

import com.uib.mongo.repository.entity.document.ListQuestionnaire
import com.uib.mongo.repository.entity.document.PartQuestionnaire
import com.uib.mongo.repository.entity.document.Question
import com.uib.mongo.repository.entity.document.Questionnaire
import com.uib.mongo.service.CustomUserDetailsService
import com.uib.mongo.service.QuestionnaireService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping(value = ["/", "/main"], method = [RequestMethod.GET])
class MainController(
        private val questionnaireService: QuestionnaireService,
        private val customUserDetailsService: CustomUserDetailsService
) {
    @GetMapping
    fun getStartingPage(model: Model): String {
        model.addAttribute("newQuestionnaire", Questionnaire(name = "default"))
        model.addAttribute("newList", ListQuestionnaire(listName = ""))
        model.addAttribute("questionnaire", questionnaireService.getQuestionnaireByUser(customUserDetailsService.getCurrentUser()))
        return "main"
    }

    @GetMapping("/editList/{listId}")
    fun editList(@PathVariable("listId") listId: String,
                 model: Model): String {
        //write number
        //questionnaireService.getNumberPart(questionnaireService.getListQuestionnaireByListId(listId)?.parts)

        model.addAttribute("list",
                questionnaireService.getListQuestionnaireByListId(listId))
        model.addAttribute("partsList",
                questionnaireService.getPartRecursiveList(questionnaireService.getListQuestionnaireByListId(listId)?.parts!!))
        model.addAttribute("partsRecursive",questionnaireService.getListQuestionnaireByListId(listId)?.parts!!)
        model.addAttribute("newQuestion", Question())
        model.addAttribute("newPart", PartQuestionnaire())
        return "editList"
    }

    @GetMapping("/editListName/{listId}")
    fun getListName(@PathVariable("listId") listId: String,
                    model: Model): String {
        model.addAttribute("list",
                questionnaireService.getListQuestionnaireByListId(listId))
        return "editListName"
    }

    @PostMapping("/editListName/{listId}")
    fun editListName(@PathVariable("listId") listId: String,
                     list: ListQuestionnaire): String {
        questionnaireService.saveEditList(list)
        return "redirect:/main/editList/${list.listId}"
    }

    @GetMapping("/editInnerPart/{partId}/{listId}")
    fun getInnerPart(@PathVariable("partId") partId: String,
                     @PathVariable("listId") listId: String,
                     model: Model): String {
        model.addAttribute("part",
                questionnaireService.getPartQuestionnaireByPartId(partId))
        model.addAttribute("listId", listId)
        return "editInnerPartName"
    }

    @PostMapping("/editInnerPart/{listId}")
    fun editInnerPart(@PathVariable("listId") listId: String,
                      part: PartQuestionnaire): String {
        questionnaireService.saveEditPart(part)
        return "redirect:/main/editList/${listId}"
    }
}
