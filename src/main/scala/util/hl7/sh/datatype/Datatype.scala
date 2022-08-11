package util.hl7.sh.datatype

import play.api.libs.json.{Format, Json}

trait Datatype

//ExtendedCompositeIdWithCheckDigit
case class CompositeID(idNumber: String)

case class PatientName(familyName: String, givenName: String)

case class MessageType(code: String, triggerEvent: String)

case class CodedWithException(id: String, nameOfCodingSystem: String, codingSystemVersionId: String)

case class loincCode(id: String, nameOfCodingSystem: String, codingSystemVersionId: String)

object Datatype {
    implicit val compositeID: Format[CompositeID] = Json.format[CompositeID]
    implicit val patientName: Format[PatientName] = Json.format[PatientName]
    implicit val messageType: Format[MessageType] = Json.format[MessageType]
    implicit val codedWithException: Format[CodedWithException] = Json.format[CodedWithException]
}