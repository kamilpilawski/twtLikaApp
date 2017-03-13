package us.tla.model

import org.springframework.validation.ObjectError

/**
 * Created by Kamil on 13.03.2017.
 */
class Response {

    val meta = Meta()
    val validation = mutableListOf<ObjectError>()

    fun pushM(text: String, type: MsgType): Response {
        meta.messages.add(Msg(text, type))
        return this
    }

    fun pushV(errors: List<ObjectError>): Response {
        validation.addAll(errors)
        return this
    }

    class Meta(val messages: MutableList<Msg> = mutableListOf<Msg>())

    data class Msg(val text: String, val type: MsgType)

    enum class MsgType {INFO, SUCCESS, WARNING, ERROR }

    data class ValidationMsg(val field: String, val message: String)
}