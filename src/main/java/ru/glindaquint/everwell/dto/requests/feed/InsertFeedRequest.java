package ru.glindaquint.everwell.dto.requests.feed;

import lombok.*;
import ru.glindaquint.everwell.models.FeedProduct;
import ru.glindaquint.everwell.types.FeedType;

import java.util.Set;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsertFeedRequest {
    private FeedType feedType;
    private Set<FeedProduct> products;
}
