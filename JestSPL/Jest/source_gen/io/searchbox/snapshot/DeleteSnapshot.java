package io.searchbox.snapshot;

/*Generated by MPS */


public class DeleteSnapshot extends AbstractSnapshotAction {

  protected DeleteSnapshot(DeleteSnapshot.Builder builder) {
    super(builder);
  }
  @Override
  public String getRestMethodName() {
    return "DELETE";
  }
  public static class Builder extends AbstractSnapshotAction.SingleSnapshotBuilder<DeleteSnapshot, DeleteSnapshot.Builder> {
    public Builder(String repository, String snapshot) {
      super(repository, snapshot);
    }
    @Override
    public DeleteSnapshot build() {
      return new DeleteSnapshot(this);
    }
  }
}
