package com.fasterxml.jackson.databind;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.wangmeng.model.AJsonpSupport;

public class SmartDefaultSerializerProvider extends DefaultSerializerProvider {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8566985311216311652L;

	public SmartDefaultSerializerProvider() { super(); }
	
    public SmartDefaultSerializerProvider(SmartDefaultSerializerProvider src) { super(src); }

    protected SmartDefaultSerializerProvider(SerializerProvider src, SerializationConfig config,SerializerFactory f) {
        super(src, config, f);
    }

    @Override
    public DefaultSerializerProvider copy()
    {
        if (getClass() != SmartDefaultSerializerProvider.class) {
            return super.copy();
        }
        return new SmartDefaultSerializerProvider(this);
    }
    
    @Override
    public DefaultSerializerProvider createInstance(SerializationConfig config, SerializerFactory jsf) {
        return new SmartDefaultSerializerProvider(this, config, jsf);
    }

	@Override
	public void serializeValue(JsonGenerator gen, Object value) throws IOException {
		if(value instanceof AJsonpSupport) {
			AJsonpSupport o = (AJsonpSupport)value;
			if(StringUtils.isNotBlank(o.getCallback())) {
				gen.writeRawValue(((AJsonpSupport)value).getCallback());
				gen.writeRawValue("(");
				super.serializeValue(gen, value);
				gen.writeRawValue(")");
			}else {
				super.serializeValue(gen, value);
			}
		}else {
			super.serializeValue(gen, value);
		}
	}

	@Override
	public void serializeValue(JsonGenerator gen, Object value, JavaType rootType) throws IOException {
		if(value instanceof AJsonpSupport) {
			AJsonpSupport o = (AJsonpSupport)value;
			if(StringUtils.isNotBlank(o.getCallback())) {
				gen.writeRawValue(o.getCallback());
				gen.writeRawValue("(");
				super.serializeValue(gen, value, rootType);
				gen.writeRawValue(")");
			}else {
				super.serializeValue(gen, value, rootType);
			}
		}else {
			super.serializeValue(gen, value, rootType);
		}
	}

	@Override
	public void serializeValue(JsonGenerator gen, Object value, JavaType rootType, JsonSerializer<Object> ser)
			throws IOException {
		if(value instanceof AJsonpSupport) {
			AJsonpSupport o = (AJsonpSupport)value;
			if(StringUtils.isNotBlank(o.getCallback())) {
				gen.writeRawValue(((AJsonpSupport)value).getCallback());
				gen.writeRawValue("(");
				super.serializeValue(gen, value, rootType, ser);
				gen.writeRawValue(")");
			}else {
				super.serializeValue(gen, value, rootType, ser);
			}
		}else {
			super.serializeValue(gen, value, rootType, ser);
		}
		
	}

//	@Override
//	public void serializePolymorphic(JsonGenerator gen, Object value, JavaType rootType,
//			JsonSerializer<Object> valueSer, TypeSerializer typeSer) throws IOException {
//		if(value instanceof AJsonpSupport) {
//			AJsonpSupport o = (AJsonpSupport)value;
//			if(StringUtils.isNotBlank(o.getCallback())) {
//				gen.writeRawValue(((AJsonpSupport)value).getCallback());
//				gen.writeRawValue("(");
//				super.serializePolymorphic(gen, value, rootType, valueSer, typeSer);
//				gen.writeRawValue(")");
//			}else {
//				super.serializePolymorphic(gen, value, rootType, valueSer, typeSer);
//			}
//		}else {
//			super.serializePolymorphic(gen, value, rootType, valueSer, typeSer);
//		}
//	}

	@Override
	public void serializePolymorphic(JsonGenerator gen, Object value, TypeSerializer typeSer) throws IOException {
		if(value instanceof AJsonpSupport) {
			AJsonpSupport o = (AJsonpSupport)value;
			if(StringUtils.isNotBlank(o.getCallback())) {
				gen.writeRawValue(((AJsonpSupport)value).getCallback());
				gen.writeRawValue("(");
				super.serializePolymorphic(gen, value, typeSer);
				gen.writeRawValue(")");
			}else {
				super.serializePolymorphic(gen, value, typeSer);
			}
		}else {
			super.serializePolymorphic(gen, value, typeSer);
		}
		
	}
    
}
