package com.uib.mongo.service

import com.uib.mongo.repository.DomainQuestionnaireRepository
import com.uib.mongo.repository.ListQuestionnaireRepository
import com.uib.mongo.repository.PartQuestionnaireRepository
import com.uib.mongo.repository.QuestionAnswersRepository
import com.uib.mongo.repository.QuestionRepository
import com.uib.mongo.repository.QuestionnaireRepository
import com.uib.mongo.repository.entity.document.DomainQuestionnaire
import com.uib.mongo.repository.entity.document.ListQuestionnaire
import com.uib.mongo.repository.entity.document.PartQuestionnaire
import com.uib.mongo.repository.entity.document.Question
import com.uib.mongo.repository.entity.document.QuestionAnswer
import com.uib.mongo.repository.entity.document.Questionnaire
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service

@Service
class QuestionnaireService(
    private val questionnaireRepo: QuestionnaireRepository,
    private val listQuestionnaireRepo: ListQuestionnaireRepository,
    private val domainQuestionnaireRepo: DomainQuestionnaireRepository,
    private val partQuestionnaireRepo: PartQuestionnaireRepository,
    private val questionRepo: QuestionRepository,
    private val answersRepo: QuestionAnswersRepository
) {
    fun getQuestionnaireByUser(user: User?): List<Questionnaire>? = questionnaireRepo.findByCreator(user?.username)
    fun getQuestionnaireByQuestionnaireId(questionnaireId: String?): Questionnaire? =
        questionnaireRepo.findByQuestionnaireId(questionnaireId)

    fun getListQuestionnaireByListId(listId: String): ListQuestionnaire? = listQuestionnaireRepo.findByListId(listId)

//    fun getPartListQuestionnaireByPartListId(partListId: String): PartListQuestionnaire? =
//            partListQuestionnaireRepo.findByPartListId(partListId)

    fun getPartQuestionnaireByPartId(partId: String): PartQuestionnaire? = partQuestionnaireRepo.findByPartId(partId)

    fun getQuestionByQuestionId(questionId: String): Question? = questionRepo.findByQuestionId(questionId)

    fun getDomainById(domainId: String): DomainQuestionnaire? = domainQuestionnaireRepo.findByDomainId(domainId)

    fun getAnswerById(answerId: String): QuestionAnswer? = answersRepo.findByAnswerId(answerId)

    fun addDomain(domain: DomainQuestionnaire, listid: String) {
        val domainSaved = domainQuestionnaireRepo.save(domain)
        val list = listQuestionnaireRepo.findByListId(listid)
        list!!.domainSections!!.add(domainSaved)
        listQuestionnaireRepo.save(list)
    }


    fun saveEditList(list: ListQuestionnaire) = listQuestionnaireRepo.save(list)

    fun saveEditQuestionnaire(questionnaire: Questionnaire) = questionnaireRepo.save(questionnaire)

//    fun saveEditPartList(part: PartListQuestionnaire) = partListQuestionnaireRepo.save(part)

    fun saveEditPart(part: PartQuestionnaire) = partQuestionnaireRepo.save(part)

    fun saveEditQuestion(question: Question) = questionRepo.save(question)

    fun saveEditDomain(domain: DomainQuestionnaire) = domainQuestionnaireRepo.save(domain)

    fun saveEditAnswer(answer: QuestionAnswer) = answersRepo.save(answer)

    fun deleteQuestion(questionId: String) = questionRepo.deleteById(questionId)

    fun deletePart(part: PartQuestionnaire) = partQuestionnaireRepo.delete(part)

    fun deleteAnswer(answerId: String) = answersRepo.deleteById(answerId)

    fun deleteDomain(domainId: String) = domainQuestionnaireRepo.deleteById(domainId)

    fun deleteList(listId: String) = listQuestionnaireRepo.deleteById(listId)

    fun getPartRecursiveList(parts: MutableList<PartQuestionnaire>): List<PartQuestionnaire>? {
        if (parts != null) {
            return parts.flatMap { getPartRecursive(it) }
                .toList()
        } else return null
    }

    fun getPartRecursive(part: PartQuestionnaire): List<PartQuestionnaire> {
        return if (part.children != null) {
            listOf(part) + part.children!!.flatMap { getPartRecursive(it) }
        } else {
            listOf(part)
        }
    }

//    fun getNumberPart(parts: MutableList<PartQuestionnaire>?) {
//        if (parts != null) {
//            getNumberParentPart(parts)
//            for (part: PartQuestionnaire in parts) {
//                if (part.children != null) {
//                    getNumberParentPart(part.children)
//                    part.number += part.number.plus(".1")
//                    getNumberPart(part.children)
//                }
//                saveEditPart(part)
//            }
//        }
//    }
//
//    fun getNumberParentPart(parts: MutableList<PartQuestionnaire>?) {
//        parts?.forEachIndexed { index, element ->
//            element.number += index.plus(1).toString()
//            saveEditPart(element)
//        }
//    }

    fun recursSearchPart(listId: String, partSearch: PartQuestionnaire?) {
        var partsList = getListQuestionnaireByListId(listId)!!.parts

        for (part in partsList!!) {
            recursSearchPart(partSearch!!, part)
        }
    }

    fun recursSearchPart(partSearch: PartQuestionnaire, partsChildren: PartQuestionnaire) {
        if (partsChildren.children!!.size != 0) {
            for (part in partsChildren.children!!) {
                if (part == partSearch) {
                   var deletePart = getPartQuestionnaireByPartId(partsChildren.partId)
                    deletePart!!.children!!.remove(partSearch)
                    saveEditPart(deletePart)
                } else {
                    recursSearchPart(partSearch, part)
                }
            }
        }
    }


    fun recursSearchPartChildren(listId: String, partSearch: PartQuestionnaire?): String{
        var returnPartId: String = ""
        var partsList = getListQuestionnaireByListId(listId)!!.parts
        for (part in partsList!!) {
            returnPartId = recursSearchPartChildren(partSearch!!, part).toString()
        }
        return returnPartId
    }

    fun recursSearchPartChildren(partSearch: PartQuestionnaire, partsChildren: PartQuestionnaire): String {
        var returnPartId: String = ""
        if (partsChildren.children!!.size != 0) {
            for (part in partsChildren.children!!) {
                if (part == partSearch) {
                    return partsChildren.partId
                } else {
                    returnPartId = recursSearchPartChildren(partSearch, part)
                }
            }
        }
        return returnPartId
    }

//    fun getPartRecursiveList(parts: List<PartQuestionnaire>?): List<PartQuestionnaire>? {
////        if (parts != null) {
////            for (part: PartQuestionnaire in parts) {
////                if (part.children != null) {
////                    return getPartRecursiveList(part.children)
////                }
////                return part.children
////            }
////        }
////        return null
////    }

}
