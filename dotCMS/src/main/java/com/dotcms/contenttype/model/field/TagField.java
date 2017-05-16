package com.dotcms.contenttype.model.field;

import java.util.List;

import org.immutables.value.Value;

import com.dotcms.repackage.com.google.common.collect.ImmutableList;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(as = ImmutableTagField.class)
@JsonDeserialize(as = ImmutableTagField.class)
@Value.Immutable
public abstract class TagField extends Field  implements OnePerContentType{


	private static final long serialVersionUID = 1L;
	
    @Value.Check
    public TagField normalize() {
      super.check();
      FieldBuilder builder = FieldBuilder.builder(this);
      if (!acceptedDataTypes().contains(dataType()) && acceptedDataTypes().size() > 0) {
        builder.dataType(acceptedDataTypes().get(0));
      }
      if (!indexed()) {
        builder.indexed(true);
      }


      return (ImmutableTagField) FieldBuilder.builder(this).build();

    }
	@Override
	public  Class type() {
		return  TagField.class;
	}
	
	public String typeName(){
		return LegacyFieldTypes.getLegacyName(TagField.class);
	}


	@Value.Default
	@Override
	public boolean indexed() {
		return true;
	};
	@Override
	public List<DataTypes> acceptedDataTypes(){
		return ImmutableList.of(DataTypes.SYSTEM);
	}
	@Value.Default
	@Override
	public DataTypes dataType(){
		return DataTypes.SYSTEM;
	};
	public abstract static class Builder implements FieldBuilder {}
	
}
