package com.library.catalogue.dto.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.library.catalogue.validation.constraint.ValidateYearFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@JsonDeserialize(builder = PublicationYearDto.PublicationYearDtoBuilder.class)
public class PublicationYearDto {
    @ValidateYearFormat
    @JsonProperty
    @NonNull
    private Integer fromPublicationYear;
    @ValidateYearFormat
    @JsonProperty
    @NonNull
    private Integer toPublicationYear;
}