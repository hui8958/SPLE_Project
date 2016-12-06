package io.searchbox.client;

/*Generated by MPS */

import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import java.io.IOException;

public class Main {
  public static void main(String[] args) {
    JestClientFactory factory = new JestClientFactory();
    factory.setHttpClientConfig(new HttpClientConfig.Builder("http://localhost:9200").multiThreaded(true).build());
    JestClient client = factory.getObject();
    try {
      client.execute(new CreateIndex.Builder("articles").build());
      System.out.println("Create index");
      client.execute(new DeleteIndex.Builder("articles").build());
      System.out.println("Deleted index");
    } catch (IOException e) {
      System.out.println("There is an exception");
    }
  }
}