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
        questionnaireService.deletePart(partId)
        return "redirect:/main/editList/${listId}"
    }

    @PostMapping("/addPart/{listId}")
    fun editPart(@PathVariable("listId") listId: String,
                 @RequestParam("partIdSelected") partIdSelected: String,
                 part: PartQuestionnaire): String {
        if(partIdSelected == "list"){
            var parentList = questionnaireService.getListQuestionnaireByListId(listId)
            var partSaving = PartQuestionnaire(name = part.name)
            partSaving.parentId = "list"
            questionnaireService.saveEditPart(partSaving)
            parentList!!.parts!!.add(partSaving)
            questionnaireService.saveEditList(parentList)
        }else{
            var parentPart = questionnaireService.getPartQuestionnaireByPartId(partIdSelected)
            var partSaved = questionnaireService.saveEditPart(part)
            parentPart!!.children!!.add(partSaved)
            questionnaireService.saveEditPart(parentPart!!)
        }

        return "redirect:/main/editList/${listId}"
    }
}
