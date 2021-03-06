package io.searchbox.cluster;

/*Generated by MPS */

import io.searchbox.action.GenericResultAbstractAction;
import io.searchbox.action.AbstractMultiINodeActionBuilder;

public class NodesStats extends GenericResultAbstractAction {

  protected NodesStats(NodesStats.Builder builder) {
    super(builder);
    setURI(buildURI());
  }
  @Override
  protected String buildURI() {
    return super.buildURI() + "/_nodes/" + nodes + "/stats";
  }
  @Override
  public String getRestMethodName() {
    return "GET";
  }
  @Override
  public String getPathToResult() {
    return "nodes";
  }
  public static class Builder extends AbstractMultiINodeActionBuilder<NodesStats, NodesStats.Builder> {
    public NodesStats.Builder withSettings() {
      return addCleanApiParameter("settings");
    }
    /**
     * Indices stats about size, document count, indexing and deletion times, search times, field cache size , merges and flushes
     */
    public NodesStats.Builder withIndices() {
      return addCleanApiParameter("indices");
    }
    /**
     * File system information, data path, free disk space, read/write stats
     */
    public NodesStats.Builder withFs() {
      return addCleanApiParameter("fs");
    }
    /**
     * HTTP connection information
     */
    public NodesStats.Builder withHttp() {
      return addCleanApiParameter("http");
    }
    /**
     * JVM stats, memory pool information, garbage collection, buffer pools
     */
    public NodesStats.Builder withJvm() {
      return addCleanApiParameter("jvm");
    }
    /**
     * TCP information
     */
    public NodesStats.Builder withNetwork() {
      return addCleanApiParameter("network");
    }
    /**
     * Operating system stats, load average, cpu, mem, swap
     */
    public NodesStats.Builder withOs() {
      return addCleanApiParameter("os");
    }
    /**
     * Process statistics, memory consumption, cpu usage, open file descriptors
     */
    public NodesStats.Builder withProcess() {
      return addCleanApiParameter("process");
    }
    /**
     * Statistics about each thread pool, including current size, queue and rejected tasks
     */
    public NodesStats.Builder withThreadPool() {
      return addCleanApiParameter("thread_pool");
    }
    /**
     * Transport statistics about sent and received bytes in cluster communication
     */
    public NodesStats.Builder withTransport() {
      return addCleanApiParameter("transport");
    }
    /**
     * Clears all the flags (first). Useful, if you only want to retrieve specific stats
     */
    public NodesStats.Builder withClear() {
      return addCleanApiParameter("clear");
    }
    @Override
    public NodesStats build() {
      return new NodesStats(this);
    }
  }
}
