package com.kafka.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
  @Bean
  public NewTopic generateTopic() {
    Map<String, String> configurations = new HashMap<>();
    configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE); //delete (borra el mensaje), compact (mantiene el mas reciente)
    //tiempo retencion mensajes, por defecto tiene -1 que significa que no se elimina
    configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000"); //milisegundos
    //tamaño maximo que tienen los segmentos en un topic, por defecto 1GB
    configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");//bytes
    configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012"); // Tamanio maximo de cada mensaje - defecto 1000000 - 1 MB

    return TopicBuilder.name("sb-topic")
                       .partitions(2)
                       .replicas(2)
                       .configs(configurations)
                       .build();
  }
}
