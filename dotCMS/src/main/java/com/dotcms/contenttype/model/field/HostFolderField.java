package com.dotcms.contenttype.model.field;

import java.util.List;

import org.immutables.value.Value;

import com.dotcms.repackage.com.google.common.collect.ImmutableList;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(as = ImmutableHostFolderField.class)
@JsonDeserialize(as = ImmutableHostFolderField.class)
@Value.Immutable
public abstract class HostFolderField extends Field implements OnePerContentType {



  @Value.Check
  public HostFolderField normalize() {
    super.check();
    FieldBuilder builder = FieldBuilder.builder(this);
    if (!acceptedDataTypes().contains(dataType()) && acceptedDataTypes().size() > 0) {
      builder.dataType(acceptedDataTypes().get(0));
    }
    if (!indexed()) {
      builder.indexed(true);
    }


    return (ImmutableHostFolderField) FieldBuilder.builder(this).build();

  }


  @Value.Default
  @Override
  public boolean indexed() {
    return true;
  };

  private static final long serialVersionUID = 1L;

  @Override
  public Class type() {
    return HostFolderField.class;
  }

  @Value.Default
  @Override
  public DataTypes dataType() {
    return DataTypes.SYSTEM;
  };

  @Override
  public final List<DataTypes> acceptedDataTypes() {
    return ImmutableList.of(DataTypes.SYSTEM, DataTypes.TEXT);
  }

  public abstract static class Builder implements FieldBuilder {
  }
}
