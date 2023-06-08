package jp.vmware.tanzu.pythonfuncconvention.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.kubernetes.client.custom.Quantity;
import io.kubernetes.client.custom.QuantityFormatter;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class QuantitySerializer extends JsonSerializer<Quantity> {

	final QuantityFormatter quantityFormatter = new QuantityFormatter();

	@Override
	public void serialize(Quantity quantity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException {
		final String formatted = this.quantityFormatter.format(quantity);
		jsonGenerator.writeString(formatted);
	}

}