package com.example.bucket_list_project.service.bucketBoard.request.bucketBoard;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SearchBucketListRequest {

    private String searchWord;
    private int pageValue;
}
