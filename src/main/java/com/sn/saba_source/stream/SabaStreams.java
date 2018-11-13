package com.sn.saba_source.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface SabaStreams {

    String TEST_DATA = "test-data-output";

    @Output(TEST_DATA)
    MessageChannel outboundTestData();

}
