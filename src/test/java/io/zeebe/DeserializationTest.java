package io.zeebe;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.camunda.zeebe.protocol.jackson.ZeebeProtocolModule;
import io.camunda.zeebe.protocol.record.Record;
import io.camunda.zeebe.protocol.record.value.JobRecordValue;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Before;
import org.junit.Test;

public class DeserializationTest {
  
  private ObjectMapper objectMapper;

  @Before
  public void setUp() {
    objectMapper = new ObjectMapper().registerModule(new ZeebeProtocolModule());
  }

  @Test
  public void shouldDeserializeJobRecordWithNullableVariables() throws IOException {
    final Record<JobRecordValue> record;
    try (InputStream json = DeserializationTest.class.getResourceAsStream("/job-with-nullable-variable.json")) {
      record = objectMapper.readValue(json, new TypeReference<>() {});
    }

    assertThat(record.getValue().getVariables()).size().isEqualTo(2);
  }
}
