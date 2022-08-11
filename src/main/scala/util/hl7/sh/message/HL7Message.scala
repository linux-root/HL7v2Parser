package util.hl7.sh.message

import play.api.libs.json.{Json, Writes}
import util.hl7.sh.segment.MessageHeader

/***
 * designed to fit SH Business logic as much as possible
 * Original HL7v2 model HAPI implementation is very complicated
 * Design our own models coming along with converters to convert
 * from HAPI model can mitigate this unnecessary complexity.
 *
 * Example : observationId is a field indicating the type of observation in an
 * observation code space ( for example LOINC code system).
 * Type of that field is “Coded With Exceptions” - which is a data type having 22 columns describing
 * the code and code system (code number, version, exception,…).
 * By knowing that the code system is actually LOINC latest version, we only need
 * to store that field in a much simple data type:  a string.
 *
 * These models can also keep our Json structure stable
 * comparing to constructing Json directly from HAPI model using Json.obj(...)
 *
 */
trait HL7Message {
 def header: MessageHeader
}

object HL7Message {
  implicit val jsWrites: Writes[HL7Message] = Writes{
    case m: ADT_A01 =>
      Json.toJson(m)
    case m: ORU_R01 =>
      Json.toJson(m)
  }
}

