package io.searchbox.indices;

/*Generated by MPS */

import io.searchbox.action.GenericResultAbstractAction;
import com.google.gson.Gson;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import io.searchbox.action.AbstractAction;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

public class Analyze extends GenericResultAbstractAction {

  protected Analyze(Analyze.Builder builder) {
    super(builder);
    this.indexName = builder.index;
    this.payload = "{\"text\": " + new Gson().toJson(builder.textToAnalyze) + "}";
    setURI(buildURI());
  }
  @Override
  protected String buildURI() {
    return super.buildURI() + "/_analyze";
  }
  @Override
  public String getRestMethodName() {
    return "POST";
  }
  @Override
  public int hashCode() {
    return new HashCodeBuilder().appendSuper(super.hashCode()).toHashCode();
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
    return new EqualsBuilder().appendSuper(super.equals(obj)).isEquals();
  }
  public static class Builder extends AbstractAction.Builder<Analyze, Analyze.Builder> {
    private String index;
    private List<String> textToAnalyze = new ArrayList<String>();
    public Analyze.Builder index(String index) {
      this.index = index;
      return this;
    }
    public Analyze.Builder text(String textToAnalyze) {
      this.textToAnalyze.add(textToAnalyze);
      return this;
    }
    public Analyze.Builder text(Collection<? extends String> textToAnalyze) {
      this.textToAnalyze.addAll(textToAnalyze);
      return this;
    }
    public Analyze.Builder analyzer(String analyzer) {
      return setParameter("analyzer", analyzer);
    }
    /**
     * The analyzer can be derived based on a field mapping.
     */
    public Analyze.Builder field(String field) {
      return setParameter("field", field);
    }
    public Analyze.Builder tokenizer(String tokenizer) {
      return setParameter("tokenizer", tokenizer);
    }
    public Analyze.Builder filter(String filter) {
      return setParameter("filters", filter);
    }
    /**
     * By default, the format the tokens are returned in are in json and its called detailed.
     * The text format value provides the analyzed data in a text stream that is a bit more readable.
     */
    public Analyze.Builder format(String format) {
      return setParameter("format", format);
    }
    @Override
    public Analyze build() {
      return new Analyze(this);
    }
  }
}
