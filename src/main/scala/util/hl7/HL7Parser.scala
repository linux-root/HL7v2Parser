package util.hl7

import ca.uhn.hl7v2.DefaultHapiContext
import ca.uhn.hl7v2.model.Message
import ca.uhn.hl7v2.validation.impl.NoValidation
import play.api.libs.json.JsValue
import ca.uhn.hl7v2.model.v28.message.{ADT_A01 => ADT_A01_28}
import ca.uhn.hl7v2.model.v22.message.{ADT_A01 => ADT_A01_22}
import ca.uhn.hl7v2.model.v23.message.{ADT_A01 => ADT_A01_23}
import ca.uhn.hl7v2.model.v28.message.{ORU_R01 => ORU_R01_28}
import ca.uhn.hl7v2.model.v23.message.{ORU_R01 => ORU_R01_23}
import util.hl7.HL7Converter.Syntax.Ops

import scala.util.Try

object HL7Parser {

  private object HAPI {
    private val context = new DefaultHapiContext()
    context.setValidationContext(new NoValidation) // FIXME
    private val parser = context.getPipeParser
    def parse(text: String): Option[Message] = Try {
      parser.parse(text)
    }.toOption
  }

  def textER7ToJson(text: String): Option[JsValue] = {
    HAPI.parse(text).map {
      case m: ADT_A01_28 =>
        m.toJson
      case m: ORU_R01_23 =>
        m.toJson
      case m: ORU_R01_28 =>
        m.toJson
      case m: ADT_A01_22 =>
        ???
      case m: ADT_A01_23 =>
        ???
    }
  }
}
