package util.hl7.sh.message

import play.api.libs.json.{Format, Json}
import util.hl7.sh.segment.{MessageHeader, ObservationRequest, ObservationResult, PatientIdentification}

case class ORU_R01(header: MessageHeader,
                   patientIdentification: PatientIdentification,
                   observationRequest: ObservationRequest,
                   observationResults: List[ObservationResult]
                  ) extends HL7Message

object ORU_R01{
  implicit val fmt: Format[ORU_R01] = Json.format[ORU_R01]
}
