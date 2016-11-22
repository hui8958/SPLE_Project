package io.searchbox.core.search.aggregation;

/*Generated by MPS */

import com.google.gson.JsonObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class StatsAggregation extends MetricAggregation {

  public static final String TYPE = "stats";
  private Long count;
  private Double min;
  private Double max;
  private Double avg;
  private Double sum;
  public StatsAggregation(String name, JsonObject statsAggregation) {
    super(name, statsAggregation);
    this.count = statsAggregation.get(String.valueOf(AggregationField.COUNT)).getAsLong();
    this.min = (!(statsAggregation.has(String.valueOf(AggregationField.MIN))) || statsAggregation.get(String.valueOf(AggregationField.MIN)).isJsonNull() ? null : statsAggregation.get(String.valueOf(AggregationField.MIN)).getAsDouble());
    this.max = (!(statsAggregation.has(String.valueOf(AggregationField.MAX))) || statsAggregation.get(String.valueOf(AggregationField.MAX)).isJsonNull() ? null : statsAggregation.get(String.valueOf(AggregationField.MAX)).getAsDouble());
    this.avg = (!(statsAggregation.has(String.valueOf(AggregationField.AVG))) || statsAggregation.get(String.valueOf(AggregationField.AVG)).isJsonNull() ? null : statsAggregation.get(String.valueOf(AggregationField.AVG)).getAsDouble());
    this.sum = (!(statsAggregation.has(String.valueOf(AggregationField.SUM))) || statsAggregation.get(String.valueOf(AggregationField.SUM)).isJsonNull() ? null : statsAggregation.get(String.valueOf(AggregationField.SUM)).getAsDouble());
  }
  public Long getCount() {
    return count;
  }
  /**
   * @return Min if it was found and not null, null otherwise
   */
  public Double getMin() {
    return min;
  }
  /**
   * @return Max if it was found and not null, null otherwise
   */
  public Double getMax() {
    return max;
  }
  /**
   * @return Avg if it was found and not null, null otherwise
   */
  public Double getAvg() {
    return avg;
  }
  /**
   * @return Sum if it was found and not null, null otherwise
   */
  public Double getSum() {
    return sum;
  }
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (obj.getClass() != getClass()) {
      return false;
    }
    StatsAggregation rhs = (StatsAggregation) obj;
    return new EqualsBuilder().appendSuper(super.equals(obj)).append(count, rhs.count).append(min, rhs.min).append(max, rhs.max).append(avg, rhs.avg).append(sum, rhs.sum).isEquals();
  }
  @Override
  public int hashCode() {
    return new HashCodeBuilder().appendSuper(super.hashCode()).append(count).append(avg).append(max).append(min).append(sum).toHashCode();
  }
}
