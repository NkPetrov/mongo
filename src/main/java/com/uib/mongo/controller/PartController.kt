package com.uib.mongo.controller

import com.uib.mongo.repository.entity.document.PartQuestionnaire
import com.uib.mongo.service.QuestionnaireService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/part")
class PartController(
        private val questionnaireService: QuestionnaireService
) {
    @GetMapping("/deletePart/{listId}/{partId}")
    fun deletePart(@PathVariable("partId") partId: String,
                   @PathVariable("listId") listId: String
    ): String {
        var list = questionnaireService.getListQuestionnaireByListId(listId)
        var part = questionnaireService.getPartQuestionnaireByPartId(partId)
        if(list!!.parts!!.contains(part)) {
            list!!.parts!!.remove(part)
            questionnaireService.saveEditList(list)
        }else{
            questionnaireService.recursSearchPart(listId ,part)
        }
        questionnaireService.deletePart(part!!)
        return "redirect:/main/editList/${listId}"
    }

    @PostMapping("/addPart/{listId}")
    fun addPart(@PathVariable("listId") listId: String,
                 @RequestParam("partIdSelected") partIdSelected: String,
                 part: PartQuestionnaire): String {

        var partSelected = questionnaireService.getPartQuestionnaireByPartId(partIdSelected)

        if (partSelected != null) {

            part.parentId = partIdSelected
            var partSaved = questionnaireService.saveEditPart(part)

            partSelected!!.children!!.add(partSaved)
            questionnaireService.saveEditPart(partSelected)
        }else if (partSelected == null) {

            var listSelected = questionnaireService.getListQuestionnaireByListId(listId)
            part.parentId = listId
            var partSaving = questionnaireService.saveEditPart(part)

            listSelected!!.parts!!.add(partSaving)

            questionnaireService.saveEditList(listSelected)
        }

        return "redirect:/main/editList/${listId}"
    }
}
