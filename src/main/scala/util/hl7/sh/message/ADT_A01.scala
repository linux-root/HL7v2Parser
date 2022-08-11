package util.hl7.sh.message

import play.api.libs.json.{Format, Json}
import util.hl7.sh.segment.{EventType, MessageHeader, PatientIdentification}

case class ADT_A01(header: MessageHeader,
                   eventType: EventType,
                   patientIdentification: PatientIdentification
                  ) extends HL7Message
object ADT_A01{
  implicit val fmt: Format[ADT_A01] = Json.format[ADT_A01]
}
