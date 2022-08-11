package util.hl7.sh.segment

import play.api.libs.json.{Format, Json}

/***
 *
 * @param observationId is assumed to be LOINC number. If we want to
 * support multiple coding system, its type should be CodedWithException as HL7 standard
 */
case class ObservationResult(setId: String,
                             valueType: String,
                             observationId: String,
                             observationValues: List[String],
                             units: String,
                             referencesRange: String,
                             status: String
                            ) extends Segment

object ObservationResult {
  implicit val jsonFormat: Format[ObservationResult] = Json.format[ObservationResult]
}