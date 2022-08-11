package util.hl7.sh.segment

import play.api.libs.json.{Format, Json}

case class ObservationRequest(universalServiceIdentifier: String) extends Segment// TODO: is it LOINCode ?

object ObservationRequest {
  implicit val jsonFormat: Format[ObservationRequest] = Json.format[ObservationRequest]
}
