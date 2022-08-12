package util.hl7.sh.segment

import play.api.libs.json.{Format, JsString, Json}
import util.hl7.sh.datatype.{CompositeID, PatientName}

case class PatientIdentification(identifiers: List[CompositeID],
                                 gender: Gender,
                                 dateOfBirth: String, //todo normalize
                                 names: List[PatientName]) extends Segment

sealed trait Gender {
  def textRepresentation: String
}

case object Male extends Gender {
  override def textRepresentation: String = "M"
}

case object Female extends Gender {
  override def textRepresentation: String = "F"
}

object Gender{
  implicit val jsonFormat: Format[Gender] = Format(
    jsValue => jsValue
      .validate[String]
      .filter(s => s == "M" || s == "F")
      .map(s => if (s == "M") Male else Female),
    gender => JsString(gender.textRepresentation)
  )

  def fromIdentifier(s: String): Gender = {
    JsString(s).as[Gender]
  }
}


object PatientIdentification {
  import util.hl7.sh.datatype.Datatype._
  implicit val fmt: Format[PatientIdentification] = Json.format[PatientIdentification]
}