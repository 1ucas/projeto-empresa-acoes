package self.manobray.empresaapi.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Document
public class Acao {

	@Id
	private String id;
	
	private String empresaId;
	
	private String ownerId;
	
	private Date creationDate;
	
	private Date lastTransaction;
	
	private float initialValue;
	
	private float actualValue;
}
