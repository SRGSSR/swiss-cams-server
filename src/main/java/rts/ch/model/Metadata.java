package rts.ch.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The metadata from api roundshot.
 */
@JsonIgnoreProperties({"is_sharing", "avg_score","distributor_name","instance_name","cam_url"})
@JsonAutoDetect(fieldVisibility= JsonAutoDetect.Visibility.ANY)
public class Metadata {

    final String altitude;
    final String category;
    @JsonProperty(value = "cam_name")
    final String cameraName;
    @JsonProperty(value = "taken_at")
    final String takenAt;
    @JsonProperty(value = "last_taken_at")
    final String lastTakenAt;
    final String latitude;
    final String longitude;
    final String region;
    final String status;
    @JsonProperty(value = "best_shot")
    final Boolean bestShot;
    @JsonProperty(value = "image_url")
    public final String imageUrl;
    @JsonProperty(value = "customer_name")
    final String customerName;

    @JsonCreator
    public Metadata(@JsonProperty(value = "altitude") String altitude,
                    @JsonProperty(value = "category") String category,
                    @JsonProperty(value = "cam_name") String cameraName,
                    @JsonProperty(value = "taken_at") String takenAt,
                    @JsonProperty(value = "last_taken_at") String lastTakenAt,
                    @JsonProperty(value = "latitude") String latitude,
                    @JsonProperty(value = "longitude") String longitude,
                    @JsonProperty(value = "region") String region,
                    @JsonProperty(value = "status") String status,
                    @JsonProperty(value = "best_shot") Boolean bestShot,
                    @JsonProperty(value = "image_url") String imageUrl,
                    @JsonProperty(value = "customer_name") String customerName) {
        this.altitude = altitude;

        this.category = category;
        this.cameraName = cameraName;
        this.takenAt = takenAt;
        this.lastTakenAt = lastTakenAt;
        this.latitude = latitude;
        this.longitude = longitude;
        this.region = region;
        this.status = status;
        this.bestShot = bestShot;
        this.imageUrl = imageUrl.replace("http","https");
        this.customerName = customerName;
    }
}
