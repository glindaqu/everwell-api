package ru.glindaquint.everwell.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.glindaquint.everwell.dto.requests.feed.InsertFeedRequest;
import ru.glindaquint.everwell.dto.responses.DataResponse;
import ru.glindaquint.everwell.models.Feed;
import ru.glindaquint.everwell.models.FeedProduct;
import ru.glindaquint.everwell.services.FeedService;
import ru.glindaquint.everwell.services.UserService;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class FeedController {
    @Autowired
    private final FeedService feedService;
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<DataResponse<?>> insertFeed(@RequestBody @Valid InsertFeedRequest request) {
        try {
            feedService.insertFeed(request);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(DataResponse.builder()
                                                   .error(e.getMessage())
                                                   .build());
        }
        return ResponseEntity.ok(null);
    }

    @GetMapping("/get-products")
    public ResponseEntity<DataResponse<?>> getFeedProducts(@RequestParam @Valid Long feedId) {
        var response = DataResponse.<Set<FeedProduct>>builder()
                                   .data(feedService.getFeedProducts(feedId))
                                   .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-owned-by-user")
    public ResponseEntity<DataResponse<?>> getFeedOwnedByUser() {
        var response = DataResponse.<Set<Feed>>builder()
                                   .data(feedService.getUsersFeeds(userService.getCurrentUser()))
                                   .build();
        return ResponseEntity.ok(response);
    }
}
