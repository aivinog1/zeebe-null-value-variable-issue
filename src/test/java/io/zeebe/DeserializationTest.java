package io.zeebe;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.camunda.zeebe.protocol.jackson.record.AbstractRecord;
import io.camunda.zeebe.protocol.record.value.JobRecordValue;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Before;
import org.junit.Test;

public class DeserializationTest {
  
  private ObjectMapper objectMapper;

  @Before
  public void setUp() {
    objectMapper = new ObjectMapper();
  }

  @Test
  public void shouldDeserializeJobRecordWithNullableVariables() throws IOException {
    final AbstractRecord<JobRecordValue> record;
    try (InputStream json = DeserializationTest.class.getResourceAsStream("/job-with-nullable-variable.json")) {
      record = objectMapper.readValue(json, new TypeReference<>() {});
    }

    assertThat(record.getValue().getVariables()).size().isEqualTo(1);
  }
}
