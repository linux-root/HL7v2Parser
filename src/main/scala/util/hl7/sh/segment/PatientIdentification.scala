package util.hl7.sh.segment

import play.api.libs.json.{Format, Json}
import util.hl7.sh.datatype.{CompositeID, PatientName}

case class PatientIdentification(identifiers: List[CompositeID],
                                 names: List[PatientName]) extends Segment

object PatientIdentification {
  import util.hl7.sh.datatype.Datatype._
  implicit val fmt: Format[PatientIdentification] = Json.format[PatientIdentification]
}