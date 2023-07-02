package com.library.catalogue.dto.inbound;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class PublicationYearDto {
    @NonNull
    private Integer fromPublicationYear;
    @NonNull
    private Integer toPublicationYear;
}