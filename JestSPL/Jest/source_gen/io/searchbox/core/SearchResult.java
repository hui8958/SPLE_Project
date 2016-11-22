package io.searchbox.core;

/*Generated by MPS */

import io.searchbox.client.JestResult;
import com.google.gson.Gson;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Map;
import io.searchbox.cloning.CloneUtils;
import com.google.gson.JsonArray;
import java.util.Set;
import java.util.HashMap;
import io.searchbox.core.search.aggregation.MetricAggregation;
import io.searchbox.core.search.aggregation.RootAggregation;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class SearchResult extends JestResult {

  public static final String EXPLANATION_KEY = "_explanation";
  public static final String HIGHLIGHT_KEY = "highlight";
  public static final String SORT_KEY = "sort";
  public static final String[] PATH_TO_TOTAL = "hits/total".split("/");
  public static final String[] PATH_TO_MAX_SCORE = "hits/max_score".split("/");
  public SearchResult(SearchResult searchResult) {
    super(searchResult);
  }
  public SearchResult(Gson gson) {
    super(gson);
  }
  @Override
  @Deprecated
  public <T> T getSourceAsObject(Class<T> clazz) {
    return super.getSourceAsObject(clazz);
  }
  @Override
  @Deprecated
  public <T> List<T> getSourceAsObjectList(Class<T> type) {
    return super.getSourceAsObjectList(type);
  }
  public <T> SearchResult.Hit<T, Void> getFirstHit(Class<T> sourceType) {
    return getFirstHit(sourceType, Void.class);
  }
  public <T, K> SearchResult.Hit<T, K> getFirstHit(Class<T> sourceType, Class<K> explanationType) {
    SearchResult.Hit<T, K> hit = null;
    List<SearchResult.Hit<T, K>> hits = getHits(sourceType, explanationType, true);
    if (!(hits.isEmpty())) {
      hit = hits.get(0);
    }
    return hit;
  }
  public <T> List<SearchResult.Hit<T, Void>> getHits(Class<T> sourceType) {
    return getHits(sourceType, Void.class);
  }
  public <T, K> List<SearchResult.Hit<T, K>> getHits(Class<T> sourceType, Class<K> explanationType) {
    return getHits(sourceType, explanationType, false);
  }
  protected <T, K> List<SearchResult.Hit<T, K>> getHits(Class<T> sourceType, Class<K> explanationType, boolean returnSingle) {
    List<SearchResult.Hit<T, K>> sourceList = new ArrayList<SearchResult.Hit<T, K>>();
    if (jsonObject != null) {
      String[] keys = getKeys();
      if (keys != null) {
        String sourceKey = keys[keys.length - 1];
        JsonElement obj = jsonObject.get(keys[0]);
        for (int i = 1; i < keys.length - 1; i++) {
          obj = ((JsonObject) obj).get(keys[i]);
        }
        if (obj.isJsonObject()) {
          sourceList.add(extractHit(sourceType, explanationType, obj, sourceKey));
        } else
        if (obj.isJsonArray()) {
          for (JsonElement hitElement : obj.getAsJsonArray()) {
            sourceList.add(extractHit(sourceType, explanationType, hitElement, sourceKey));
            if (returnSingle) {
              break;
            }
          }
        }
      }
    }
    return sourceList;
  }
  protected <T, K> SearchResult.Hit<T, K> extractHit(Class<T> sourceType, Class<K> explanationType, JsonElement hitElement, String sourceKey) {
    SearchResult.Hit<T, K> hit = null;
    if (hitElement.isJsonObject()) {
      JsonObject hitObject = hitElement.getAsJsonObject();
      JsonObject source = hitObject.getAsJsonObject(sourceKey);
      if (source != null) {
        String index = hitObject.get("_index").getAsString();
        String type = hitObject.get("_type").getAsString();
        Double score = null;
        if (hitObject.has("_score") && !(hitObject.get("_score").isJsonNull())) {
          score = hitObject.get("_score").getAsDouble();
        }
        JsonElement explanation = hitObject.get(EXPLANATION_KEY);
        Map<String, List<String>> highlight = extractHighlight(hitObject.getAsJsonObject(HIGHLIGHT_KEY));
        List<String> sort = extractSort(hitObject.getAsJsonArray(SORT_KEY));
        JsonObject clonedSource = null;
        for (JestResult.MetaField metaField : JestResult.META_FIELDS) {
          JsonElement metaElement = hitObject.get(metaField.esFieldName);
          if (metaElement != null) {
            if (clonedSource == null) {
              clonedSource = (JsonObject) CloneUtils.deepClone(source);
            }
            clonedSource.add(metaField.internalFieldName, metaElement);
          }
        }
        if (clonedSource != null) {
          source = clonedSource;
        }
        hit = new SearchResult.Hit<T, K>(sourceType, source, explanationType, explanation, highlight, sort, index, type, score);
      }
    }
    return hit;
  }
  protected List<String> extractSort(JsonArray sort) {
    if (sort == null) {
      return null;
    }
    List<String> retval = new ArrayList<String>(sort.size());
    for (JsonElement sortValue : sort) {
      retval.add((sortValue.isJsonNull() ? "" : sortValue.getAsString()));
    }
    return retval;
  }
  protected Map<String, List<String>> extractHighlight(JsonObject highlight) {
    Map<String, List<String>> retval = null;
    if (highlight != null) {
      Set<Map.Entry<String, JsonElement>> highlightSet = highlight.entrySet();
      retval = new HashMap<String, List<String>>(highlightSet.size());
      for (Map.Entry<String, JsonElement> entry : highlightSet) {
        List<String> fragments = new ArrayList<String>();
        for (JsonElement element : entry.getValue().getAsJsonArray()) {
          fragments.add(element.getAsString());
        }
        retval.put(entry.getKey(), fragments);
      }
    }
    return retval;
  }
  public Integer getTotal() {
    Integer total = null;
    JsonElement obj = getPath(PATH_TO_TOTAL);
    if (obj != null) {
      total = obj.getAsInt();
    }
    return total;
  }
  public Float getMaxScore() {
    Float maxScore = null;
    JsonElement obj = getPath(PATH_TO_MAX_SCORE);
    if (obj != null) {
      maxScore = obj.getAsFloat();
    }
    return maxScore;
  }
  protected JsonElement getPath(String[] path) {
    JsonElement retval = null;
    if (jsonObject != null) {
      JsonElement obj = jsonObject;
      for (String component : path) {
        if (obj == null) {
          break;
        }
        obj = ((JsonObject) obj).get(component);
      }
      retval = obj;
    }
    return retval;
  }
  public MetricAggregation getAggregations() {
    final String rootAggrgationName = "aggs";
    if (jsonObject == null) {
      return new RootAggregation(rootAggrgationName, new JsonObject());
    }
    if (jsonObject.has("aggregations")) {
      return new RootAggregation(rootAggrgationName, jsonObject.getAsJsonObject("aggregations"));
    }
    if (jsonObject.has("aggs")) {
      return new RootAggregation(rootAggrgationName, jsonObject.getAsJsonObject("aggs"));
    }
    return new RootAggregation(rootAggrgationName, new JsonObject());
  }
  /**
   *  Immutable class representing a search hit.
   * 
   *  @param <T> type of source
   *  @param <K> type of explanation
   *  @author cihat keser
   */
  public class Hit<T, K> {
    public final T source;
    public final K explanation;
    public final Map<String, List<String>> highlight;
    public final List<String> sort;
    public final String index;
    public final String type;
    public final Double score;
    public Hit(Class<T> sourceType, JsonElement source) {
      this(sourceType, source, null, null);
    }
    public Hit(Class<T> sourceType, JsonElement source, Class<K> explanationType, JsonElement explanation) {
      this(sourceType, source, explanationType, explanation, null, null);
    }
    public Hit(Class<T> sourceType, JsonElement source, Class<K> explanationType, JsonElement explanation, Map<String, List<String>> highlight, List<String> sort) {
      this(sourceType, source, explanationType, explanation, highlight, sort, null, null, null);
    }
    public Hit(Class<T> sourceType, JsonElement source, Class<K> explanationType, JsonElement explanation, Map<String, List<String>> highlight, List<String> sort, String index, String type, Double score) {
      if (source == null) {
        this.source = null;
      } else {
        this.source = createSourceObject(source, sourceType);
      }
      if (explanation == null) {
        this.explanation = null;
      } else {
        this.explanation = createSourceObject(explanation, explanationType);
      }
      this.highlight = highlight;
      this.sort = sort;
      this.index = index;
      this.type = type;
      this.score = score;
    }
    public Hit(T source) {
      this((Class<T>) source, null, null, null);
    }
    public Hit(T source, K explanation) {
      this((Class<T>) source, (JsonElement) explanation, null, null);
    }
    public Hit(T source, K explanation, Map<String, List<String>> highlight, List<String> sort) {
      this(source, explanation, highlight, sort, null, null, null);
    }
    public Hit(T source, K explanation, Map<String, List<String>> highlight, List<String> sort, String index, String type, Double score) {
      this.source = source;
      this.explanation = explanation;
      this.highlight = highlight;
      this.sort = sort;
      this.index = index;
      this.type = type;
      this.score = score;
    }
    @Override
    public int hashCode() {
      return new HashCodeBuilder().append(source).append(explanation).append(highlight).append(sort).toHashCode();
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
      SearchResult.Hit rhs = (SearchResult.Hit) obj;
      return new EqualsBuilder().append(source, rhs.source).append(explanation, rhs.explanation).append(highlight, rhs.highlight).append(sort, rhs.sort).isEquals();
    }
  }
}
