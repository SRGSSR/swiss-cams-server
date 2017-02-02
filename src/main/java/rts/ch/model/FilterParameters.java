package rts.ch.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The filter parameters set in the roundshot api request.
 */
@JsonAutoDetect(fieldVisibility= JsonAutoDetect.Visibility.ANY)
public final class FilterParameters {

    final String category;
    final String region;

    @JsonProperty(value = "bestshot")
    final String bestShot;

    final String randomize;

    @JsonProperty(value = "date_from")
    final String dateFrom;

    @JsonProperty(value = "date_to")
    final String dateTo;
    final String limit;
    final Size size;

    enum Size{
        full("full"),
        half("half"),
        quarter("quarter"),
        def("default"),
        medium("medium"),
        optional("optional"),
        archiveprev("archiveprev"),
        oneeight("oneeight"),
        thumbnail("thumbnail");

        final String size;

        Size(String size) {
            this.size = size;
        }

        @Override
        public String toString() {
            return this.size;
        }
    }

    @JsonCreator
    public FilterParameters(@JsonProperty(value = "category") String category,
                            @JsonProperty(value = "region") String region,
                            @JsonProperty(value = "bestshot") String bestShot,
                            @JsonProperty(value = "date_from") String dateFrom,
                            @JsonProperty(value = "date_to") String dateTo,
                            @JsonProperty(value = "limit") String limit,
                            @JsonProperty(value = "size") String size,
                            @JsonProperty(value = "randomize") String randomize){
        this.category = category;
        this.region = region;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.limit = limit;
        this.bestShot = bestShot;
        this.randomize = randomize;
        this.size = size != null && !size.equals("default") ? Size.valueOf(size) : Size.def;
    }

    @Override
    public String toString() {
        return (!isNullOrEmpty(category)  ? "&category="+category : "" )+
               (!isNullOrEmpty(region)    ? "&region[]="+region : ""     )+
               (!isNullOrEmpty(bestShot)    ? "&bestshot="+bestShot : ""     )+
               (!isNullOrEmpty(randomize)    ? "&randomize="+randomize : ""     )+
               (!isNullOrEmpty(dateFrom)  ? "&date_from="+dateFrom : "")+
               (!isNullOrEmpty(dateTo)    ? "&date_to="+dateTo : ""    )+
               (!isNullOrEmpty(limit)     ? "&limit="+limit : ""    )+
               (!isNullOrEmpty(size.name())      ? "&size="+size.toString() : ""    ) ;
    }

    /**
     * Test if the string parameter is null or empty.
     * @param str, string param
     * @return boolean
     */
    private boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }
}
