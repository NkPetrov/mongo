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
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/main")
class MainController(
    private val questionnaireService: QuestionnaireService,
    private val customUserDetailsService: CustomUserDetailsService
) {
    @GetMapping
    fun getStartingPage(model: Model): String {
        model.addAttribute("newQuestionnaire", Questionnaire(name = "default"))
        model.addAttribute("newList", ListQuestionnaire(listName = ""))
        model.addAttribute(
            "questionnaire",
            questionnaireService.getQuestionnaireByUser(customUserDetailsService.getCurrentUser())
        )
        return "main"
    }

    @GetMapping("/editList/{listId}")
    fun editList(
        @PathVariable("listId") listId: String,
        model: Model
    ): String {
        //write number
        //questionnaireService.getNumberPart(questionnaireService.getListQuestionnaireByListId(listId)?.parts)

        model.addAttribute(
            "list",
            questionnaireService.getListQuestionnaireByListId(listId)
        )
        model.addAttribute(
            "partsList",
            questionnaireService.getPartRecursiveList(questionnaireService.getListQuestionnaireByListId(listId)?.parts!!)
        )
        model.addAttribute("partsRecursive", questionnaireService.getListQuestionnaireByListId(listId)?.parts!!)
        model.addAttribute("newQuestion", Question())
        model.addAttribute("newPart", PartQuestionnaire())
        return "editList"
    }

    @GetMapping("/editListName/{listId}")
    fun getListName(
        @PathVariable("listId") listId: String,
        model: Model
    ): String {
        model.addAttribute(
            "list",
            questionnaireService.getListQuestionnaireByListId(listId)
        )
        return "editListName"
    }

    @PostMapping("/editListName/{listId}")
    fun editListName(
        @PathVariable("listId") listId: String,
        list: ListQuestionnaire
    ): String {
        questionnaireService.saveEditList(list)
        return "redirect:/main/editList/${list.listId}"
    }

    @GetMapping("/editInnerPart/{partId}/{listId}")
    fun getInnerPart(
        @PathVariable("partId") partId: String,
        @PathVariable("listId") listId: String,
        model: Model
    ): String {
        var partsRecursive =
            questionnaireService.getPartRecursiveList(questionnaireService.getListQuestionnaireByListId(listId)?.parts!!)
        var partMutList = partsRecursive!!.toMutableList()
        partMutList.remove(questionnaireService.getPartQuestionnaireByPartId(partId))

        model.addAttribute("partsRecursive", partMutList)
        model.addAttribute("part", questionnaireService.getPartQuestionnaireByPartId(partId))
        model.addAttribute("list", questionnaireService.getListQuestionnaireByListId(listId))
        return "editInnerPartName"
    }

    @PostMapping("/editInnerPart/{listId}")
    fun editInnerPart(
        @PathVariable("listId") listId: String,
        @RequestParam("partIdSelected") partIdSelected: String,
        part: PartQuestionnaire
    ): String {
        //var parentPart = questionnaireService.getPartQuestionnaireByPartId(questionnaireService.recursSearchPartChildren(listId, part))
        var parentPart = questionnaireService.getPartQuestionnaireByPartId(part.parentId!!)
        var partSelected = questionnaireService.getPartQuestionnaireByPartId(partIdSelected)

        if (parentPart != null && partSelected != null) {
            if(parentPart != partSelected){
                parentPart.children!!.remove(questionnaireService.getPartQuestionnaireByPartId(part.partId))
                questionnaireService.saveEditPart(parentPart)

                part.parentId = partIdSelected
                var partSaved = questionnaireService.saveEditPart(part)

                partSelected!!.children!!.remove(questionnaireService.getPartQuestionnaireByPartId(part.partId))
                questionnaireService.saveEditPart(partSelected)
                partSelected!!.children!!.add(partSaved)
                questionnaireService.saveEditPart(partSelected)
            }else{
                questionnaireService.saveEditPart(part)
            }

        } else if (parentPart == null && partSelected == null) {

            questionnaireService.saveEditPart(part)

        } else if (parentPart != null && partSelected == null) {
            parentPart.children!!.remove(questionnaireService.getPartQuestionnaireByPartId(part.partId))
            questionnaireService.saveEditPart(parentPart)

            var listSelected = questionnaireService.getListQuestionnaireByListId(partIdSelected)
            part.parentId = listId
            var partSaved = questionnaireService.saveEditPart(part)

            listSelected!!.parts!!.remove(questionnaireService.getPartQuestionnaireByPartId(part.partId))
            questionnaireService.saveEditList(listSelected)
            listSelected!!.parts!!.add(partSaved)
            questionnaireService.saveEditList(listSelected)
        } else if (parentPart == null && partSelected != null) {

            var parentList = questionnaireService.getListQuestionnaireByListId(listId)
            parentList!!.parts!!.remove(questionnaireService.getPartQuestionnaireByPartId(part.partId))
            questionnaireService.saveEditList(parentList)

            part.parentId = partIdSelected
            var partSaved = questionnaireService.saveEditPart(part)

            partSelected!!.children!!.remove(questionnaireService.getPartQuestionnaireByPartId(part.partId))
            questionnaireService.saveEditPart(partSelected)
            partSelected!!.children!!.add(partSaved)
            questionnaireService.saveEditPart(partSelected)
        }

        return "redirect:/main/editList/${listId}"
    }
}
