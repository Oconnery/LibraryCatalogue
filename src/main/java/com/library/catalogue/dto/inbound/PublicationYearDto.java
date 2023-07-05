package com.library.catalogue.dto.inbound;

import com.library.catalogue.validation.constraint.ValidateYearFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class PublicationYearDto {
    @ValidateYearFormat
    @NonNull
    private Integer fromPublicationYear;
    @ValidateYearFormat
    @NonNull
    private Integer toPublicationYear;
}